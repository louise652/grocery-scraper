package com.sainsburys.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sainsburys.ScraperApplication;
import com.sainsburys.controller.ScraperController;
import com.sainsburys.model.GroceryListVO;

/**
 * JUnits for Scraper view
 * 
 * @author Louise McCloy
 *
 */
public class ScraperViewTest {

	ScraperView view;
	GroceryListVO groceryList;

	@Before
	public void setup() {
		groceryList = new ScraperController(ScraperApplication.URL).retrieveGroceryList();
		view = new ScraperView();

	}

	@Test

	public void testPrintsJSON() {
		assertNotNull("should return json representation of pojo ", view.displayItems(groceryList));
	}

	@Test
	public void testWritesJSONFile() {
		File file = new File("result.json");
		view.displayItems(groceryList);
		assertTrue(file.exists());
	}

}
