package Packages;

import java.io.Serializable;

public class LoginRequest implements Serializable, Package {

	private static final long serialVersionUID = 664747009248310784L;
	private static final Integer PACKAGEID = 1;
	private String user;
	private String password;
	
	public LoginRequest(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
}
