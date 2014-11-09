package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class GameRequest implements Serializable, Package {

	private static final long serialVersionUID = 8640887894955828087L;
	private static final Integer PACKAGEID = 2;
	private String gameName;
	private Integer maxPlayers;
	private ArrayList<Integer> questionsID = new ArrayList<Integer>();

	public GameRequest(String gameName, Integer maxPlayers, ArrayList<Integer> questionsID) {
		this.gameName = gameName;
		this.maxPlayers = maxPlayers;
		this.questionsID = questionsID;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
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
