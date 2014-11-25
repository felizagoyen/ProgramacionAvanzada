package Server;

import java.util.ArrayList;

import Packages.*;

public class Game extends Thread {
//	private static final int TIME = 30000;
	private static final int MAXROUND = 10;
	private static Game game = new Game();
	private String gameName;
	private Integer maxPlayers;
	private ArrayList<Integer> questionsID = new ArrayList<Integer>();
	private ArrayList<Integer> players = new ArrayList<Integer>();
	private ArrayList<String> answers = new ArrayList<String>();
	private Boolean waitingAnswer = false;
	
	private Game() {
		
	}
	
	public static Game getGameInstance() {
		return game;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName; 
	}
	
	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public void setQuestionsID(ArrayList<Integer> questionsID) {
		this.questionsID = questionsID;
	}
	
	public void addPlayer(Integer playerID) {
		players.add(playerID);
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public Integer getMaxPlayers() {
		return maxPlayers;
	}
	
	public Boolean getWaitingAnswer() {
		return waitingAnswer;
	}
	
	public void setAnswer(int playerId, String answer) {
		answers.add(players.indexOf(playerId), answer);
	}
	
	public void run() {
		DataBaseUtil db = new DataBaseUtil();
		ClientConnection clientConnectionInstance = ClientConnection.getInstance();

		for(int roundNumber = 0; roundNumber < MAXROUND; roundNumber++) {
			Integer questionId = questionsID.get(roundNumber);
			Question question = null;

			if(questionId == null) {
				int maxId = db.getMaxQuestionId();
				questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
				while(!questionId.equals(-1) && questionsID.contains(questionId)) {
					questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
				}
				questionsID.set(roundNumber, questionId);
			} 
			question = db.getQuestionByID(questionId);
			EndTimeRequest timeToAnswer = new EndTimeRequest(System.currentTimeMillis() + 30000);
			waitingAnswer = true;
			
			for(Integer eachPlayerID: players) {
				try {
					clientConnectionInstance.blockSocket(eachPlayerID);
					clientConnectionInstance.sendPackage(eachPlayerID, timeToAnswer);
					clientConnectionInstance.sendPackage(eachPlayerID, question);
					clientConnectionInstance.releaseSocket(eachPlayerID);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			while(System.currentTimeMillis() < timeToAnswer.getEndTime());
			waitingAnswer = false;
			EndTimeRequest timeToWaitNewQuestion = new EndTimeRequest(System.currentTimeMillis() + 5000);
			
			for(Integer eachPlayerID: players) {
				AnswerQuestion answerQuestion;
				try {
					if(question.getCorrectAnswer().equals(answers.get(eachPlayerID)))
						answerQuestion = new AnswerQuestion(true);
					else
						answerQuestion = new AnswerQuestion(false);
					clientConnectionInstance.blockSocket(eachPlayerID);
					clientConnectionInstance.sendPackage(eachPlayerID, answerQuestion);
					clientConnectionInstance.sendPackage(eachPlayerID, timeToWaitNewQuestion);
					clientConnectionInstance.releaseSocket(eachPlayerID);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			while(System.currentTimeMillis() < timeToWaitNewQuestion.getEndTime());

		}
		//players = null;
		//gameName = null;
		//maxPlayers = null;
	}
}