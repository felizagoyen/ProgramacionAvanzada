package Server;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Packages.Question;

public class Game extends Thread {
//	private static final int TIME = 30000;
	private static final int MAXROUND = 10;
	private static Game game = new Game();
	private String gameName;
	private Integer maxPlayers;
	private ArrayList<Integer> questionsID = new ArrayList<Integer>();
	private ArrayList<Integer> players = new ArrayList<Integer>();
	
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
	
	public void addPlayer(Integer playerID) {
		players.add(playerID);
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public Integer getMaxPlayers() {
		return maxPlayers;
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
			
			for(Integer eachPlayerID: players) {
				try {
					clientConnectionInstance.blockSocket(eachPlayerID);
					System.out.println(question.getQuestion() + " - " + question.getCorrectAnswer() + " - " + question.getCategory());
					clientConnectionInstance.sendPackage(eachPlayerID, question);
					Thread.sleep(500);
					clientConnectionInstance.releaseSocket(eachPlayerID);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		//players = null;
		//gameName = null;
		//maxPlayers = null;
	}
}