package Packages;

import java.io.Serializable;

public class EndClientConnectionResponse implements Serializable, Package {
	

	private static final long serialVersionUID = 1014897091896088388L;
	private static final Integer PACKAGEID = 10;
	
	public EndClientConnectionResponse(){
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}

}
