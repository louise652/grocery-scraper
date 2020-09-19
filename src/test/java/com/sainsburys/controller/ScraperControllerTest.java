package com.sainsburys.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sainsburys.ScraperApplication;
import com.sainsburys.model.GroceryListVO;
import com.sainsburys.model.GroceryVO;

/**
 * JUnits for ScraperController.java
 * 
 * @author Louise McCloy
 *
 */

public class ScraperControllerTest {
	ScraperController controller;
	GroceryListVO list;

	@Before
	public void setup() {
		String url = ScraperApplication.URL;
		controller = new ScraperController(url);

	}

	@Test
	public void testHappyPath() {

		list = controller.retrieveGroceryList();
		assertNotNull("Grocery List should populate", list);
	}

	@Test
	public void testListContainsItems() {

		list = controller.retrieveGroceryList();
		List<GroceryVO> results = list.getResults();
		assertTrue("Individual item should populate", results.size() > 0);
	}

	@Test
	public void testTotals() {
		list = getMockList();
		controller.setGroceryTotals(list);

		assertEquals("5.00", list.getTotal().getGross());
		assertEquals("0.83", list.getTotal().getVat());
	}

	@Test
	public void testExtractNumberFromInput() {
		assertEquals("3.00", controller.extractNumberFromString("£3.00/unit"));
		assertEquals("35", controller.extractNumberFromString("35kcal"));
	}

	@Test
	public void testWrongURL() {
		controller = new ScraperController("https://www.google.com/");
		list = controller.retrieveGroceryList();
		assertTrue("List should not populate", list.getResults().isEmpty());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidURL() {
		controller = new ScraperController("invalid");
		controller.retrieveGroceryList();

	}

	private GroceryListVO getMockList() {

		GroceryListVO listVO = new GroceryListVO();
		List<GroceryVO> groceryVOs = new ArrayList<>();

		groceryVOs.add(new GroceryVO("Sainsbury's Strawberries 400g", 22, "1.75", "by Sainsbury's strawberries"));
		groceryVOs.add(new GroceryVO("Sainsbury's Blueberries 200g", 45, "3.25", "by Sainsbury's blueberries"));
		listVO.setResults(groceryVOs);

		return listVO;

	}

}
