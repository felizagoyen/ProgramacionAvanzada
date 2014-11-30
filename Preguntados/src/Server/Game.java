package Server;

import java.util.ArrayList;
import java.util.Collections;

import Commons.*;

public class Game extends Thread {
	
	private static final int TIMETOANSWER = 30000;
	private static final int TIMETONEXTQUESTION = 3000;
	private static final int MAXROUND = 10;
	private static Game game = new Game();
	private String gameName;
	private Integer maxPlayers;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Integer> questionsId = new ArrayList<Integer>();
	private Boolean waitingAnswer = false;
	private Boolean isCreated = false;
	private Boolean isStarted = false;
	private Game() {

	}
	
	public static Game getGameInstance() {
		return game;
	}
	
	public void createGame(String gameName, Integer maxPlayers, ArrayList<Integer> questionsId) {
		this.gameName = gameName;
		this.maxPlayers = maxPlayers;
		this.questionsId = questionsId;
		this.isCreated = true;
	}
	
	public Boolean isCreated() {
		return isCreated;
	}
	
	public void addPlayer(Integer playerId, String playerName) {
		players.add(new Player(playerId, playerName));
		Logger.info(playerName + " se ha unido a la partida. (" + players.size() + "/" + maxPlayers + ")");
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

	public void setAnswer(Integer playerId, String answer) {
		for(Player eachPlayer: players)
			if(eachPlayer.getId().equals(playerId))
				eachPlayer.setAnswer(answer);
	}

	public void removePlayer(Integer playerId) {
		Player playerToRemove = null;
		for(Player eachPlayer: players)
			if(eachPlayer.getId().equals(playerId))
				playerToRemove = eachPlayer;

		if(playerToRemove != null) { 
			Logger.info(playerToRemove.getName() + " ha dejado la partida. (" + (players.size() - 1) + "/" + maxPlayers + ")");
			players.remove(playerToRemove); 
		}
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

	public Boolean playerInGame(Integer playerId) {
		for(Player eachPlayer: players) 
			if(eachPlayer.getId().equals(playerId))
				return true;
		return false;
	}
	
	public Boolean isStarted() {
		return isStarted;
	}
	
	
	public void run() {
		isStarted = true;
		DataBaseUtil db = new DataBaseUtil();
		ClientConnection clientConnectionInstance = ClientConnection.getInstance();
		for(Player eachPlayer: players) 
			eachPlayer.setAnswer(null);
		
		for(int roundNumber = 0; roundNumber < MAXROUND && !players.isEmpty(); roundNumber++) {
			Integer questionId = questionsId.get(roundNumber);
			Question question = null;

			if(questionId == null) {
				int maxId = db.getMaxQuestionId();
				questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
				while(!questionId.equals(-1) && questionsId.contains(questionId)) {
					questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
				}
				questionsId.set(roundNumber, questionId);
			} 
			question = db.getQuestionByID(questionId);
			EndTimeRequest timeToAnswer = new EndTimeRequest(System.currentTimeMillis() + TIMETOANSWER);
			waitingAnswer = true;
			
			Logger.info("Enviando pregunta de la ronda " + (roundNumber + 1));
			
			for(Player eachPlayer: players) {
				try {
					clientConnectionInstance.blockSocket(eachPlayer.getId());
					setAnswer(eachPlayer.getId(), null);
					clientConnectionInstance.sendPackage(eachPlayer.getId(), timeToAnswer);
					clientConnectionInstance.sendPackage(eachPlayer.getId(), question);
					clientConnectionInstance.releaseSocket(eachPlayer.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			Boolean finishTime = false;
			Boolean allAnswered = false;
			
			while(finishTime == false && allAnswered == false && !players.isEmpty()){
				finishTime = System.currentTimeMillis() > timeToAnswer.getEndTime();
				allAnswered = true;
				for(Player eachPlayer: players)
					if(eachPlayer.getAnswer() == null)
						allAnswered = false;
			}
			
			waitingAnswer = false;
			EndTimeRequest timeToWaitNewQuestion = new EndTimeRequest(System.currentTimeMillis() + TIMETONEXTQUESTION);
			
			if(!players.isEmpty())
				Logger.info("Verificando respuestas...");
				
			for(Player eachPlayer: players) {
				AnswerQuestion answerQuestion;
				try {
					if(question.getCorrectAnswer().equals(eachPlayer.getAnswer())) {
						answerQuestion = new AnswerQuestion(true);
						eachPlayer.increaseScore();
					} else {
						answerQuestion = new AnswerQuestion(false);
					}
					
					clientConnectionInstance.blockSocket(eachPlayer.getId());
					clientConnectionInstance.sendPackage(eachPlayer.getId(), answerQuestion);
					clientConnectionInstance.sendPackage(eachPlayer.getId(), timeToWaitNewQuestion);
					clientConnectionInstance.releaseSocket(eachPlayer.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(!players.isEmpty()) {
				Logger.info("Respuestas verificadas correctamente");
				while(System.currentTimeMillis() < timeToWaitNewQuestion.getEndTime());
			}

		}
		
		if(players.isEmpty()) {
			Logger.info("La partida finalizo porque se desconectaron todos los jugadores");
		} else {
			Logger.info("La partida finalizo correctamente luego de las " + MAXROUND + " rondas.");
			Collections.sort(players);
			Integer maxScore = players.get(0).getScore();
			
			for(Player eachPlayer: players) 
				if(eachPlayer.getScore().equals(maxScore)) {
					clientConnectionInstance.blockSocket(eachPlayer.getId());
	//				clientConnectionInstance.sendPackage(eachPlayer.getId(), answerQuestion);
		//			clientConnectionInstance.sendPackage(eachPlayer.getId(), timeToWaitNewQuestion);
					clientConnectionInstance.releaseSocket(eachPlayer.getId());
					Logger.info("Ganador -> Nombre: " + eachPlayer.getName() + " - Puntuacion: " + eachPlayer.getScore());
				}
		}

		players = new ArrayList<Player>();
		gameName = null;
		maxPlayers = null;
		questionsId = new ArrayList<Integer>();
		waitingAnswer = false;
		isCreated = false;
		isStarted = false;
	}
}