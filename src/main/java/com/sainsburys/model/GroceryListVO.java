package com.sainsburys.model;

import java.util.List;

import lombok.Data;

/**
 * POJO to hold the overall results from a screenscrape of the Sainsburys
 * Berries page
 * 
 * @author Louise McCloy
 *
 */
@Data
public class GroceryListVO {
	public List<GroceryVO> groceryVOs;
	public Total total;

}
