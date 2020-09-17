package models;

import lombok.Data;

@Data
public class Result {

	public String title;
	public int kcal_per_100g;
	public double unit_price;
	public String description;
}
