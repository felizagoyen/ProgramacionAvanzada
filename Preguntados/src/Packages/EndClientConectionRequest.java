package Packages;

import java.io.Serializable;

public class EndClientConectionRequest implements Serializable, Package {
	
	private static final long serialVersionUID = -7923496294983108689L;
	private static final Integer PACKAGEID = 10;
	
	public EndClientConectionRequest() {
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
}
