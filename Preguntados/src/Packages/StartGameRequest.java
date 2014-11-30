package Packages;

import java.io.Serializable;

public class StartGameRequest implements Serializable, Package {

	private static final long serialVersionUID = -961426379852472252L;
	public static final Integer PACKAGEID = 4;

	public StartGameRequest(){
		
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	
}
