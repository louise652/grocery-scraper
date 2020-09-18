package com.sainsburys.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * POJO to hold the data of an individual item from the Sainsbury's berry page
 * 
 * @author Louise McCloy
 *
 */
@Data
public class Result {

	public String title;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public Integer kcal_per_100g;
	public double unit_price;
	public String description;

}
