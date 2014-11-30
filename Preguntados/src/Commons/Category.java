package Commons;

import java.io.Serializable;

public class Category implements Serializable, Package {

	private static final long serialVersionUID = 1322572983931253501L;
	private static final Integer PACKAGEID = 5;
	private Integer id;
	private String category;
	
	public Category(Integer id, String category) {
		this.id = id;
		this.category = category;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getCategory() {
		return category;
	}
	
}