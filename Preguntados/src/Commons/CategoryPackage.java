package Commons;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryPackage implements Serializable, Package {

	private static final long serialVersionUID = 1322572983931253501L;
	private static final Integer PACKAGEID = 5;
	private ArrayList<Category> categories; 
	
	public CategoryPackage(ArrayList<Category> categories) {
		this.categories = categories;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}

}