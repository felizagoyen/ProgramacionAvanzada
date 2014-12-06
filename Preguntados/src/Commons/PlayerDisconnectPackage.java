package Commons;

import java.io.Serializable;

public class PlayerDisconnectPackage implements Serializable, Package {

	private static final long serialVersionUID = -3719603863382808234L;
	private static final Integer PACKAGEID = 16;
	private String userName;
	
	public PlayerDisconnectPackage(String userName) {
		this.userName = userName;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public String getUserName() {
		return userName;
	}

}
