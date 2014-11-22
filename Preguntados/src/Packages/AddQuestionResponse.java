package Packages;

import java.io.Serializable;

public class AddQuestionResponse implements Serializable, Package {
		
	private static final long serialVersionUID = -5236502747742644840L;
	private static final Integer PACKAGEID = 9;
	private Boolean valid;
	
	public AddQuestionResponse(Boolean valid) {
		this.valid = valid;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}

	public Boolean getValid() {
		return valid;
	}
	
}
