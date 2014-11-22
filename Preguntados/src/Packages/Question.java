package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable, Package {

	private static final long serialVersionUID = 946827401315759467L;
	private static final Integer PACKAGEID = 9;
	private Integer id;
	private String question;
	private String category;
	private String correctAnswer;
	private ArrayList<String> wrongAnswers = new ArrayList<String>();

	public Question(String question, String category, String correctAnswer,
			ArrayList<String> wrongAnswers) {
		this.id = 0;
		this.question = question;
		this.category = category;
		this.correctAnswer = correctAnswer;
		this.wrongAnswers = wrongAnswers;
	}

	public Question(Integer id, String question) {
		this.id = id;
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public String getCategory() {
		return category;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public ArrayList<String> getWrongAnswers() {
		return wrongAnswers;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}
}
