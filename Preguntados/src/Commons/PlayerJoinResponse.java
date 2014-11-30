package Commons;

import java.io.Serializable;

public class PlayerJoinResponse implements Serializable, Package {
	
	private static final long serialVersionUID = -3026835309278795842L;
	private static final Integer PACKAGEID = 3;
	private Integer joinStatus; 
	
	public PlayerJoinResponse(int joinStatus) {
		this.joinStatus = joinStatus;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Integer getJoinStatus() {
		return joinStatus;
	}

}