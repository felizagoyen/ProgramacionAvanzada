package Server;

import java.net.Socket;

public class Client {
	
	private Integer id;
	private String name;
	private Socket socket;
	
	public Client(Integer id, Socket socket) {
		this.id = id;
		this.socket = socket;
		this.name = "";
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
}
