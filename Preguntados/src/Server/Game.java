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
	private ArrayList<Socket> players = new ArrayList<Socket>();
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
	
	public void addPlayer(Socket player) {
		players.add(player);
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
		for(int round = 0; round < MAXROUND; round++) {
			Integer id = questionsID.get(roundNumber);
			Question question = null;
			if(id == null) {
				//Busco pregunta random
			} else {
				//Busco pregunta por id
			}
			
			for(Socket eachPlayerSocket: players) {
				try {
					ObjectOutputStream outputStream = new ObjectOutputStream(eachPlayerSocket.getOutputStream());
					outputStream.writeObject(QUESTIONREQUESTID);
					outputStream.writeObject(question);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
}
