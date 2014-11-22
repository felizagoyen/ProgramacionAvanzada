package Packages;

import java.io.Serializable;

public class GenericPackage implements Serializable, Package {

	private static final long serialVersionUID = 1688812813592203438L;
	private Integer packageId;
		
	public GenericPackage(Integer packageId) {
		this.packageId = packageId;
	}
	
	public Integer getPackageID() {
		return packageId;
	}
	
	

}
