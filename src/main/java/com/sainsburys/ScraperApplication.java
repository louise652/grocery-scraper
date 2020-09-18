package com.sainsburys;

import com.sainsburys.controller.ScraperController;
import com.sainsburys.model.GroceryListVO;
import com.sainsburys.view.ScraperView;

/**
 * Launcher to retrieve a JSON representation of items of the Sainsbury's berry
 * page
 * 
 * @author Louise McCloy
 *
 */

public class ScraperApplication {

	private static final String URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

	public static void main(String[] args) {

		ScraperController controller = new ScraperController(URL);

		// retrieve grocery list from the controller
		GroceryListVO groceryList = controller.retrieveGroceryList();

		// print out results
		ScraperView view = new ScraperView();
		view.displayItems(groceryList);
	}

}
