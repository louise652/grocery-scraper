package com.sainsburys.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * POJO to hold the overall results from a screenscrape of the Sainsburys
 * Berries page
 * 
 * @author Louise McCloy
 *
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GroceryListVO {
	public List<GroceryVO> results;
	public Total total;

}
