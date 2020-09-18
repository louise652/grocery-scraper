package com.sainsburys.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO to hold the data of an individual item from the Sainsbury's berry page
 * 
 * @author Louise McCloy
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GroceryVO {

	public String title;

	
	public Integer kcal_per_100g;
	public double unit_price;
	public String description;

}
