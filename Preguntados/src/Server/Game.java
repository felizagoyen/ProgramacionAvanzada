package Server;

import java.util.ArrayList;

import Packages.*;

public class Game extends Thread {
	private static final int TIMETOANSWER = 30000;
	private static final int TIMETONEXTQUESTION = 3000;
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
		answers.set(players.indexOf(playerId), answer);
	}

	public void removePlayers(int playerId) {
		if(players.contains(playerId))
			players.remove(players.indexOf(playerId));
	}
	
	public Boolean gameIsFull() {
		if(players.size() == maxPlayers)
			return true;
		return false;
	}
	
	public Boolean canStartGame() {
		if(players.size() >= 2)
			return true;
		return false;
	}
	
	public void run() {
		DataBaseUtil db = new DataBaseUtil();
		ClientConnection clientConnectionInstance = ClientConnection.getInstance();
		for(int i = 0; i < players.size(); i++)
			answers.add(null);
		
		for(int roundNumber = 0; roundNumber < MAXROUND && !players.isEmpty(); roundNumber++) {
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
			EndTimeRequest timeToAnswer = new EndTimeRequest(System.currentTimeMillis() + TIMETOANSWER);
			waitingAnswer = true;
			
			Logger.info("Enviando pregunta de la ronda " + (roundNumber + 1));
			
			for(Integer eachPlayerID: players) {
				try {
					clientConnectionInstance.blockSocket(eachPlayerID);
					setAnswer(eachPlayerID, null);
					clientConnectionInstance.sendPackage(eachPlayerID, timeToAnswer);
					clientConnectionInstance.sendPackage(eachPlayerID, question);
					clientConnectionInstance.releaseSocket(eachPlayerID);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			Boolean finishTime = false;
			Boolean allAnswered = false;
			
			while(finishTime == false && allAnswered == false && !players.isEmpty()){
				finishTime = System.currentTimeMillis() > timeToAnswer.getEndTime();
				if(!answers.contains(null))
					allAnswered = true;
			}
			
			waitingAnswer = false;
			EndTimeRequest timeToWaitNewQuestion = new EndTimeRequest(System.currentTimeMillis() + TIMETONEXTQUESTION);
			
			if(!players.isEmpty())
				Logger.info("Verificando respuestas...");
				
			for(Integer eachPlayerID: players) {
				AnswerQuestion answerQuestion;
				try {
					if(question.getCorrectAnswer().equals(answers.get(players.indexOf(eachPlayerID))))
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
			
			if(!players.isEmpty()) {
				Logger.info("Respuestas verificadas correctamente");
				while(System.currentTimeMillis() < timeToWaitNewQuestion.getEndTime());
			}

		}
		
		if(players.isEmpty())
			Logger.info("La partida finalizo porque se desconectaron todos los jugadores");
		
		players = new ArrayList<Integer>();
		gameName = null;
		maxPlayers = null;
		questionsID = new ArrayList<Integer>();
		answers = new ArrayList<String>();
		waitingAnswer = false;
	}
}