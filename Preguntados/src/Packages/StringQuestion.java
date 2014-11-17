package Packages;

import java.io.Serializable;

public class StringQuestion implements Serializable{
	
	private static final long serialVersionUID = -7300685691596122691L;
	private String question;
	private Integer questionID;
	
	
	public StringQuestion(String question, Integer id){
		this.question = question;
		questionID = id;
	}

	public String getQuestion (){
		return question;
	}
	
	
}
