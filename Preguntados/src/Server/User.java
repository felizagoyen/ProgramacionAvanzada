package Server;

public class User {
	public class User {
	private String user;
	private String pass;
	private Integer tipo;
	
	public User(String user, String pass, Integer tipo) {
		this.user=user;
		this.pass=pass;
		this.tipo=tipo;
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

	public String toString(){
		return "Usuario: " + user + " Pass: " + pass;
	}
}
