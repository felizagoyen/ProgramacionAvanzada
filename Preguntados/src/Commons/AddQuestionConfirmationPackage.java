package Commons;

import java.io.Serializable;

public class AddQuestionConfirmationPackage implements Serializable, Package {
		
	private static final long serialVersionUID = -5236502747742644840L;
	private static final Integer PACKAGEID = 9;
	private Boolean valid;
	
	public AddQuestionConfirmationPackage(Boolean valid) {
		this.valid = valid;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}

	public Boolean getValid() {
		return valid;
	}
	
}
