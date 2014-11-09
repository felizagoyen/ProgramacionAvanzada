package Packages;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 3832731611489649571L;
	private Integer userType;
	
	public LoginResponse(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getUserType() {
		return userType;
	}
	
}
