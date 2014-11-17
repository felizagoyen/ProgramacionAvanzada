package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

	private static final long serialVersionUID = 946827401315759467L;
		private String question;
		private String category;
		private String correctAnswer;
		private ArrayList<String> wrongAnswers = new ArrayList<String>();
		
		public Question(String question, String category, String correctAnswer, ArrayList<String> wrongAnswers) {
			this.question = question;
			this.category = category;
			this.correctAnswer = correctAnswer;
			this.wrongAnswers = wrongAnswers;
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
		
}
