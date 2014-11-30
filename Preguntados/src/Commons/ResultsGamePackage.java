package Commons;

import java.io.Serializable;

public class ResultsGamePackage implements Serializable, Package{
	
	private static final long serialVersionUID = 7026662954512938663L;

	private static final Integer PACKAGEID = 14;
	private Integer winnerstatus;
	
	public ResultsGamePackage(Integer winnerstatus){
		this.winnerstatus = winnerstatus;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Integer getStatus(){
		return winnerstatus;
	}

}
