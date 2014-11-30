package Commons;

import java.io.Serializable;

public class StartGameResponse implements Serializable, Package {

	private static final long serialVersionUID = -1246049570327015223L;
	public static final Integer PACKAGEID = 4;
	public Boolean startGame;
	
	public StartGameResponse(Boolean startGame){
		this.startGame = startGame;
	}
	
	public Integer getPackageID() {
		return PACKAGEID;
	}
	
	public Boolean getStartGame() {
		return startGame;
	}
	
}