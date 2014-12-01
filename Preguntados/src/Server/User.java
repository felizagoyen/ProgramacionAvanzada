package Server;

public class User {
	private String user;
	private String pass;
	private Integer tipo;
	private Puntuation puntuation;
	
	public User(String user, String pass, Integer tipo) {
		this.user=user;
		this.pass=pass;
		this.tipo=tipo;
		loadPuntuation();
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	public Puntuation getPuntuation() {
		return puntuation;
	}

	public String toString(){
		return "Usuario: " + user + " Pass: " + pass;
	}
	
	public void loadPuntuation(){
		DataBaseUtil db = new DataBaseUtil();
		this.puntuation=db.getPuntuationDB(user);
	}
	
	public void updatePuntuation(){
		DataBaseUtil db = new DataBaseUtil();
		db.updatePuntuationDB(user, puntuation);
	}
}
