package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsResponse implements Serializable, Package {
	
	private static final long serialVersionUID = 6845559714710805684L;
	private ArrayList<Question> questions;
	private static final Integer PACKAGEID = 11;
	
	public QuestionsResponse(ArrayList<Question> questions){
		this.questions = questions;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	

}
