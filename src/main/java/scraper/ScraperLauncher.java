package scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperLauncher {
	static String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

	public static void main(String[] args) {
		try {
			Document pageToScrape = Jsoup.connect(url).get();
			Elements section = pageToScrape.select("#productLister > ul > li > div > div.productInfo > div > h3 > a");
			for (Element link : section) {
				String name = link.text();
				System.out.println("\n\n" + name);
				getIndividualItemDetails(link.absUrl("href"));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getIndividualItemDetails(String url) {

		Document doc;
		try {
			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(5000).get();
			getDescription(doc);
			getPricePerUnit(doc);

			getCalories(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getCalories(Document doc) {

		Element links = doc.selectFirst("tr.tableRow0>td");
		String res = "";
		if (links == null) {

			// some items (eg 200g cherries) have a different table format and require
			// different mapping
			Elements stuff = doc.getElementsContainingText("Energy kcal");

			if (!stuff.isEmpty()) {
				Element txt = stuff.select("td").first();
				res = txt.text();
			}

		} else {
			res = links.text();
		}
		System.out.println(res);
	}

	private static void getDescription(Document doc) {

		String s2 = doc.select("h3:contains(Description)").first().parent().children().get(1).text();
		System.out.println(s2);

	}

	private static void getPricePerUnit(Document doc) {
		String s = doc.select("p.pricePerUnit").text();
		System.out.println(s);
	}
}
