package Server;

import java.net.Socket;

public class Client {
	
	private Integer id;
	private String name;
	private Socket socket;
	
	public Client(Integer id, String name, Socket socket) {
		this.id = id;
		this.name = name;
		this.socket = socket;
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
