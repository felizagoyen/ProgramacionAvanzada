package Server;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Packages.Question;

public class Game extends Thread {
//	private static final int TIME = 30000;
	private static final int MAXROUND = 10;
	private static final Integer QUESTIONREQUESTID = 6;
	private static Game game = new Game();
	private String gameName;
	private Integer maxPlayers;
	private ArrayList<Integer> questionsID = new ArrayList<Integer>();
	private ArrayList<Integer> players = new ArrayList<Integer>();
	private Integer roundNumber;
	
	private Game() {
		
	}
	
	public static Game getGame() {
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
	
	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
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
	
	public Integer getQuestionID() {
		return questionsID.get(roundNumber);
	}
	
	public Integer getRoundNumber() {
		return roundNumber;
	}
	
	public void run() {
		DataBaseUtil db = new DataBaseUtil();
		ClientSocket clientSocketInstance = ClientSocket.getInstance();

		for(int round = 0; round < MAXROUND; round++) {
			Integer questionId = questionsID.get(roundNumber);
			Question question = null;

			if(questionId == null) {
				int maxId = db.getMaxQuestionId();
				while(!questionId.equals(-1) && !questionsID.contains(questionId)) {
					questionId = (int) Math.ceil((Math.random() * (maxId - 1)) + 1);
				}
			} 
			question = db.getQuestionByID(questionId);
			
			for(Integer eachPlayerID: players) {
				try {
					clientSocketInstance.blockSocket(eachPlayerID);
					ObjectOutputStream outputStream = new ObjectOutputStream(clientSocketInstance.getClientSocket(eachPlayerID).getOutputStream());
					outputStream.writeObject(question);
					clientSocketInstance.releaseSocket(eachPlayerID);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}