package Packages;

import java.io.Serializable;

public class PlayerJoinRequest implements Serializable, Package {
	
	private static final long serialVersionUID = 989024739531282591L;
	private static final Integer PACKAGEID = 3;

	public PlayerJoinRequest(){
		
	}
	
	public Integer getPackageID() {
		
		return PACKAGEID;
	}

}
