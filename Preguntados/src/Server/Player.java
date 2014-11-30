package Server;

public class Player implements Comparable<Player> {
	
	private Integer id;
	private String name;
	private Integer score;
	private String answer;
	
	public Player(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.score = 0;
		this.answer = null;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void increaseScore() {
		score += 1;
	}

	public int compareTo(Player otherPlayer) {
		if(this.score < otherPlayer.score) return 1;
		if(this.score > otherPlayer.score) return -1;
		return 0;
	}
	
	
	
}
