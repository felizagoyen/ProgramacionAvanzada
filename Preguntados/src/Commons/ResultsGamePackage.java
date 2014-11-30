package Commons;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultsGamePackage implements Serializable, Package {
	
	private static final long serialVersionUID = 7026662954512938663L;

	private static final Integer PACKAGEID = 14;
	private ArrayList<Player> winners;
	private ArrayList<Player> scoreTable;
	private Integer playerWin;
	
	public ResultsGamePackage(Integer playerWin, ArrayList<Player> winners, ArrayList<Player> scoreTable) {
		this.playerWin = playerWin;
		this.winners = winners;
		this.scoreTable = scoreTable;
	}

	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Integer getPlayerWin(){
		return playerWin;
	}

	public ArrayList<Player> getWinners() {
		return winners;
	}
	
	public ArrayList<Player> getScoreTable() {
		return scoreTable;
	}
}
