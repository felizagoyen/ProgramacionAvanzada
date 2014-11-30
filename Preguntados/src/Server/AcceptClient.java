package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class AcceptClient extends Thread {
	private ServerSocket serverSocket;
	
	public AcceptClient(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void run() {
		Socket clientSocket;
		ClientConnection clientSocketInstance = ClientConnection.getInstance();
		int clientId;
		while(true) {
			try {
				clientSocket = serverSocket.accept();
				if((clientId = clientSocketInstance.getFreeIndexClient()) != -1) {
					clientSocketInstance.setClientConnection(new Client(clientId, clientSocket));
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
