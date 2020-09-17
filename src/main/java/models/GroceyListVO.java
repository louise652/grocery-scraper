package models;

import java.util.List;
import lombok.Data;

@Data
public class GroceyListVO {
	public List<Result> results;
	public Total total;

}
