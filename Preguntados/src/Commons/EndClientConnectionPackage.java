package Commons;

import java.io.Serializable;

public class EndClientConnectionPackage implements Serializable, Package {
	
	private static final long serialVersionUID = -7923496294983108689L;
	private static final Integer PACKAGEID = 10;
	
	public EndClientConnectionPackage() {
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
}
