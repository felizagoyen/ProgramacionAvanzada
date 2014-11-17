package Packages;

import java.io.Serializable;

public class QuestionsRequest implements Serializable, Package {
	
	
	private static final long serialVersionUID = -5236502747742644840L;
	private static final Integer PACKAGEID = 11;
	private String categoria;
	
	public QuestionsRequest(String categoria ) {
		this.categoria = categoria;
	}

	@Override
	public Integer getPackageID() {
		return PACKAGEID;
	}

}
