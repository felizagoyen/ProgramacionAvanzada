package Server;

import java.net.ServerSocket;
import java.net.Socket;

import Commons.User;

public class AcceptUser extends Thread {
	private ServerSocket serverSocket;
	
	public AcceptUser(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void run() {
		Socket clientSocket;
		UserConnection clientSocketInstance = UserConnection.getInstance();
		int clientId;
		while(true) {
			try {
				clientSocket = serverSocket.accept();
				if((clientId = clientSocketInstance.getFreeIndexClient()) != -1) {
					clientSocketInstance.setClientConnection(new User(clientId, clientSocket));
					Logger.info("Conexion Aceptada. ClientId: " + clientId);
					new ServerThread(clientId).start(); //Crea una conexion nueva de escucha para cada cliente
				} else {
					Logger.warn("No hay más conexiones disponibles");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
