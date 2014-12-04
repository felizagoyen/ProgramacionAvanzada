package Server;

import java.net.ServerSocket;

public class Server {

	private static final int PORT = 10000;
	private static final int MAXCONNECTIONS = 50;
	
	public Server() {
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(PORT);
			Logger.info("Puerto: " + PORT);
			Logger.info("Conexiones maximas: " + MAXCONNECTIONS);
			Logger.info("Esperando conexiones...");
			new AcceptUser(serverSocket).start(); //Hilo que se encarga de aceptar conecciones
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
}
