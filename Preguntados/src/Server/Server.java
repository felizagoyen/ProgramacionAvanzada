package Server;

import java.net.ServerSocket;

public class Server {

	private static final int PORT = 10000;
	private static final int MAXCONNECTIONS = 10;
	
	public Server() {
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Puerto: " + PORT);
			System.out.println("Conexiones maximas: " + MAXCONNECTIONS);
			System.out.println("Esperando conexiones...");
			new AcceptClient(serverSocket).start(); //Hilo que se encarga de aceptar conecciones
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
}
