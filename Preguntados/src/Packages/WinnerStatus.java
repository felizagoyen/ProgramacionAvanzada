package Packages;

import java.io.Serializable;

public class WinnerStatus implements Serializable, Package{
	


	private static final long serialVersionUID = 7026662954512938663L;

	private static final Integer PACKAGEID = 14;
	private Integer winnerstatus;
	
	public WinnerStatus(Integer winnerstatus){
		this.winnerstatus = winnerstatus;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Integer getStatus(){
		return winnerstatus;
	}

}
