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
		if(isStarted) {
			for(Player eachPlayer: players)
				if(eachPlayer.getId().equals(playerId)) {
					eachPlayer.setDisconnect();
					Logger.info(eachPlayer.getName() + " ha dejado la partida. (" + (players.size() - 1) + "/" + maxPlayers + ")");
				}
		} else { 
			Player playerToRemove = null;
			for(Player eachPlayer: players)
				if(eachPlayer.getId().equals(playerId))
					playerToRemove = eachPlayer;
			
			if(playerToRemove != null) { 
				Logger.info(playerToRemove.getName() + " ha dejado la partida. (" + (players.size() - 1) + "/" + maxPlayers + ")");
				players.remove(playerToRemove);
			}
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
		Boolean allPlayerDisconnected = false;
		isStarted = true;

		for(Player eachPlayer: players) 
			eachPlayer.setAnswer(null);
		
		for(int roundNumber = 0; roundNumber < MAXROUND && !allPlayerDisconnected; roundNumber++) {
			Question question = getRoundQuestion(roundNumber);
			Long endTimeToAnswer = sendRoundQuestion(roundNumber, question);
			allPlayerDisconnected = waitAllAnswer(endTimeToAnswer);
			if(!allPlayerDisconnected)
				checkAndSendValidAnswers(question);
		}
		
		if(allPlayerDisconnected) {
			Logger.info("La partida finalizo porque se desconectaron todos los jugadores");
		} else {
			Logger.info("La partida finalizo correctamente luego de las " + MAXROUND + " rondas.");
			sendGameResults();
		}

		players = new ArrayList<Player>();
		gameName = null;
		maxPlayers = null;
		questionsId = new ArrayList<Integer>();
		waitingAnswer = false;
		isCreated = false;
		isStarted = false;
	}
	
	private Question getRoundQuestion(Integer roundNumber) {
		DataBaseUtil db = new DataBaseUtil();
		Integer questionId = questionsId.get(roundNumber);

		if(questionId == null) {
			int maxId = db.getMaxQuestionId();
			questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
			while(!questionId.equals(-1) && questionsId.contains(questionId)) {
				questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
			}
			questionsId.set(roundNumber, questionId);
		} 
		return db.getQuestionByID(questionId);
	}
	
	private Long sendRoundQuestion(Integer roundNumber, Question question) {
		ClientConnection clientConnectionInstance = ClientConnection.getInstance();
		
		EndTimePackage timeToAnswer = new EndTimePackage(System.currentTimeMillis() + TIMETOANSWER);
		waitingAnswer = true;
		
		Logger.info("Enviando pregunta de la ronda " + (roundNumber + 1));
		
		for(Player eachPlayer: players) {
			try {
				setAnswer(eachPlayer.getId(), null);
				if(eachPlayer.disconnectedWhilePlaying() == false) {
					clientConnectionInstance.blockSocket(eachPlayer.getId());
					clientConnectionInstance.sendPackage(eachPlayer.getId(), timeToAnswer);
					clientConnectionInstance.sendPackage(eachPlayer.getId(), question);
					clientConnectionInstance.releaseSocket(eachPlayer.getId());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return timeToAnswer.getEndTime();
	}

	private Boolean waitAllAnswer(Long endTimeToAnswer) {
		Boolean finishTime = false;
		Boolean allAnswered = false;
		Boolean allPlayerDisconnected = false;
		
		while(!finishTime && !allAnswered && !allPlayerDisconnected){
			finishTime = System.currentTimeMillis() > endTimeToAnswer;
			allAnswered = true;
			allPlayerDisconnected = true;
			
			for(Player eachPlayer: players) {
				if(eachPlayer.disconnectedWhilePlaying() == false && eachPlayer.getAnswer() == null)
					allAnswered = false;
				if(eachPlayer.disconnectedWhilePlaying() == false)
					allPlayerDisconnected = false;
			}
		}
		
		waitingAnswer = false;
		return allPlayerDisconnected;
	}
	
	private void checkAndSendValidAnswers(Question question) {
		ClientConnection clientConnectionInstance = ClientConnection.getInstance();
		EndTimePackage timeToWaitNewQuestion = new EndTimePackage(System.currentTimeMillis() + TIMETONEXTQUESTION);
		
		Logger.info("Verificando respuestas...");
			
		for(Player eachPlayer: players) {
			AnswerQuestionPackage answerQuestion;
			try {
				if(eachPlayer.disconnectedWhilePlaying() == false) {
					if(question.getCorrectAnswer().equals(eachPlayer.getAnswer())) {
						answerQuestion = new AnswerQuestionPackage(true);
						eachPlayer.increaseScore();
					} else {
						answerQuestion = new AnswerQuestionPackage(false);
					}
					
					clientConnectionInstance.blockSocket(eachPlayer.getId());
					clientConnectionInstance.sendPackage(eachPlayer.getId(), answerQuestion);
					clientConnectionInstance.sendPackage(eachPlayer.getId(), timeToWaitNewQuestion);
					clientConnectionInstance.releaseSocket(eachPlayer.getId());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Logger.info("Respuestas verificadas correctamente");
		while(System.currentTimeMillis() < timeToWaitNewQuestion.getEndTime());
	}

	private void sendGameResults() {
		ClientConnection clientConnectionInstance = ClientConnection.getInstance();
		
		Collections.sort(players);
		Integer maxScore = players.get(0).getScore();
		ArrayList<Player> winners = new ArrayList<Player>();
		
		Logger.info("Tabla de Posiciones:");
		for(Player eachPlayer: players) { 
			if(eachPlayer.getScore().equals(maxScore))
				winners.add(eachPlayer);
			Logger.info("Nombre: " + eachPlayer.getName() + " - Puntuacion: " + eachPlayer.getScore());
			} 
		
		for(Player eachPlayer: players) {
			if(eachPlayer.disconnectedWhilePlaying() == false) {
				ResultsGamePackage resultsGame;
				Integer playerWin = -1;
				
				if(winners.contains(eachPlayer))
					playerWin = (winners.size() == 1) ? 1 : 0;
				
				resultsGame = new ResultsGamePackage(playerWin, winners, players);
				
				try {
					clientConnectionInstance.blockSocket(eachPlayer.getId());
					clientConnectionInstance.sendPackage(eachPlayer.getId(), resultsGame);
					clientConnectionInstance.releaseSocket(eachPlayer.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
}