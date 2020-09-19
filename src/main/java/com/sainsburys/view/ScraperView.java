package com.sainsburys.view;

import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sainsburys.model.GroceryListVO;

/**
 * Outputs the screenscraper result in JSON format
 * 
 * @author Louise McCloy
 *
 */
public class ScraperView {

	/**
	 * Outputs grocery POJO as Json
	 * 
	 * @param groceryList POJO of items
	 */
	public String displayItems(GroceryListVO groceryList) {

		ObjectMapper mapper = new ObjectMapper(); // for printing to console
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter()); // for writing to file
		String json = "";
		try {
			json = (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groceryList));
			writer.writeValue(Paths.get("result.json").toFile(), groceryList);
		} catch (IOException e) {
			System.out.println("Exception parsing Json: " + e.getMessage());
		}
		return json;
	}

}
