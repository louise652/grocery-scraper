package com.sainsburys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sainsburys.model.GroceryListVO;
import com.sainsburys.model.Result;
import com.sainsburys.model.Total;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

/**
 * Controller to scrape a webpage and add elements to a POJO
 * @author Louise McCloy
 *
 */
public class ScraperController {

	@Getter
	@Setter
	private String url;

	private static final String DESCRIPTION_SELECTOR = "h3:contains(Description)";
	private static final String DOUBLE_REGEX = "[\\s+a-zA-Z : £ /]";
	private static final String PRICE_PER_UNIT_SELECTOR = "p.pricePerUnit";
	private static final String NUTRITION_ROW_SELECTOR = "tr.tableRow0>td";
	private static final String TABLE_DATA_ELEMENT = "td";
	private static final String ENERGY_KCAL_SELECTOR = "Energy kcal";
	private static final String JSOUP_CLIENT = "Jsoup client";
	private static final String HREF_SELECTOR = "href";
	private static final String PRODUCT_LIST_SELECTOR = "#productLister > ul > li > div > div.productInfo > div > h3 > a";

	/**
	 * Uses JSoup to scrape the given webpage and assign results to a POJO
	 * 
	 * @return list of items on page
	 */
	public GroceryListVO retrieveGroceryList() {

		GroceryListVO groceryList = new GroceryListVO();

		List<Result> resList = new ArrayList<>();
		try {
			Document pageDoc = Jsoup.connect(this.getUrl()).get();
			pageDoc.select(PRODUCT_LIST_SELECTOR).forEach(item -> {
				getIndividualItemPage(resList, item);

			});

			groceryList.setResults(resList); // add the individual item to the overall list
			setGroceryTotals(groceryList); // set overall totals
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groceryList;

	}

	/**
	 * Scrape the individual item page
	 * 
	 * @param resList list of items
	 * @param item    current item
	 */

	private void getIndividualItemPage(List<Result> resList, Element item) {
		Result result = new Result();
		result.setTitle(item.text());
		getIndividualItemDetails(item.absUrl(HREF_SELECTOR), result);
		resList.add(result);
	}

	/**
	 * GEts the individal item data from its page
	 * 
	 * @param url    item url
	 * @param result item details
	 */
	public static void getIndividualItemDetails(String url, Result result) {

		Document doc;

		try {

			// connecting to item page using JSoup
			doc = Jsoup.connect(url).userAgent(JSOUP_CLIENT).timeout(5000).get();

			result.setDescription(getDescription(doc));
			result.setUnit_price(getPricePerUnit(doc));
			result.setKcal_per_100g(getCalories(doc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Getting overall gross and vat totals for all items
	 * 
	 * @param groceryList
	 */
	private void setGroceryTotals(GroceryListVO groceryList) {
		Total groceryTotal = new Total();

		// summing the unit price of each item
		double gross = groceryList.getResults().stream().filter(x -> x != null).mapToDouble(x -> x.getUnit_price())
				.sum();

		// since vat is 20%, divide gross by 1.2 to get the value before vat
		// then subtract from gross to get vat amount
		double vat = (gross - (gross / 1.2));

		// formatting to 2dp as per requirements
		groceryTotal.setGross(String.format("%.2f", gross));
		groceryTotal.setVat(String.format("%.2f", vat));

		groceryList.setTotal(groceryTotal);

	}

	/**
	 * Retireve the calorie per 100g info per item
	 * 
	 * @param doc item page
	 * @return calorie value
	 */
	private static Integer getCalories(Document doc) {
		Integer calories = null;

		// There are two different calorie tables used (200g cherries seems to be
		// formatted differently).
		// Check both methods for info
		Element kcalA = doc.selectFirst(NUTRITION_ROW_SELECTOR);
		Element kcalB = doc.getElementsContainingText(ENERGY_KCAL_SELECTOR).select(TABLE_DATA_ELEMENT).first();

		String resultStr = "";
		if (kcalA != null) {
			resultStr = kcalA.text();
		} else if (kcalB != null) {
			resultStr = kcalB.text();
		}

		String cals = extractNumberFromString(resultStr);
		if (!cals.isEmpty()) {
			calories = Integer.parseInt(cals);
		}
		return calories;
	}

	/**
	 * Get the item description
	 * 
	 * @param doc item url
	 * @return first line of description
	 */
	private static String getDescription(Document doc) {
		return doc.select(DESCRIPTION_SELECTOR).first().parent().children().get(1).text();
	}

	/**
	 * Get the price per unit
	 * 
	 * @param doc item url
	 * @return item price
	 */
	private static double getPricePerUnit(Document doc) {
		String pricePerUnit = doc.selectFirst(PRICE_PER_UNIT_SELECTOR).text();
		return Double.parseDouble(extractNumberFromString(pricePerUnit));
	}

	/**
	 * Helper to extract a double from a String
	 * 
	 * @param input String to strip
	 * @return stripped output
	 */
	private static String extractNumberFromString(String input) {
		return input.replaceAll(DOUBLE_REGEX, "");
	}

}
