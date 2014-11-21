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
		while(true) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Conexion Aceptada");
				new ServerThread(1, clientSocket).start(); //Crea una conexion nueva de escucha para cada cliente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
