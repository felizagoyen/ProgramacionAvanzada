package Packages;

import java.io.Serializable;

public class LoginResponse implements Serializable, Package {

	private static final long serialVersionUID = 3832731611489649571L;
	private static final Integer PACKAGEID = 1;
	private Integer userType;
	
	public LoginResponse(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Integer getUserType() {
		return userType;
	}
	
}
