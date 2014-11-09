package Packages;

import java.util.ArrayList;

public class GameRequest {
	private String gameName;
	private Integer maxPlayers;
	private ArrayList<Integer> questionsID = new ArrayList<Integer>();

	public GameRequest(String gameName, Integer maxPlayers, ArrayList<Integer> questionsID) {
		this.gameName = gameName;
		this.maxPlayers = maxPlayers;
		this.questionsID = questionsID;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public Integer getMaxPlayers() {
		return maxPlayers;
	}
	
	public ArrayList<Integer> getQuestionsID() {
		return questionsID;
	}
}
