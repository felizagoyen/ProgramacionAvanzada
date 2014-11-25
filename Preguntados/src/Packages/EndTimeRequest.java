package Packages;

import java.io.Serializable;

public class EndTimeRequest implements Serializable, Package {
		
	private static final long serialVersionUID = 4747635692769497358L;
	private static final Integer PACKAGEID = 12;
	private long endTime;
	
	public EndTimeRequest(long endTime) {
		this.endTime = endTime;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}

	public long getEndTime() {
		return endTime;
	}
	
}
