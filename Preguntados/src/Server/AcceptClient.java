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
		ClientSocket clientSocketInstance = ClientSocket.getInstance();
		int clientID;
		while(true) {
			try {
				if((clientID = clientSocketInstance.getFreeIndexSocket()) != -1) {
					clientSocket = serverSocket.accept();
					clientSocketInstance.setClientSocket(clientID, clientSocket);
					System.out.println("Conexion Aceptada");
					new ServerThread(clientID).start(); //Crea una conexion nueva de escucha para cada cliente
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
