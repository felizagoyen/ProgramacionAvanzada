package Packages;

import java.io.Serializable;

public class AnswerQuestion implements Serializable, Package {
		
	private static final long serialVersionUID = 2102908903826759542L;
	private static final Integer PACKAGEID = 13;
	private String answer;
	private Boolean isCorrect;
	
	public AnswerQuestion(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public AnswerQuestion(String answer) {
		this.answer = answer;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}

	public String getAnswer() {
		return answer;
	}
	
	public Boolean isCorrect() {
		return isCorrect;
	}
	
}