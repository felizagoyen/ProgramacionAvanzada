package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.*;

public class ClientConection extends Thread {

	private Socket socket;
	private Integer clientID;

	public ClientConection(Integer clientID, Socket socket) {
		this.clientID = clientID;
		this.socket = socket;
	}

	public void run() {
		Boolean end = false;
		while (!end) {
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Integer idPackageOut = null;
				Object packageOut = null;
				
				switch((Integer) inputStream.readObject()) {
				case 1: //Pedido de logeo del cliente
					LoginRequest login = (LoginRequest) inputStream.readObject();
					Integer clientType = validateClient(login);
					idPackageOut = 1;
					if(clientType.equals(-1)) end = true;
					packageOut = new LoginResponse(clientType); 
				case 2: //Creacion de partida
					
				case 3: //Jugador uniendose a paritda
					
				}

				outputStream.writeObject(idPackageOut);
				outputStream.writeObject(packageOut);
				
				outputStream.close();
				inputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Integer validateClient(LoginRequest client) {
		
		return -1;
	}
	

}
