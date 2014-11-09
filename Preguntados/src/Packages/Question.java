package Packages;

import java.util.ArrayList;

public class Question {

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
