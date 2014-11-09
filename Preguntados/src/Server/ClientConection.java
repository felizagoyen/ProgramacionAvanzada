package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.*;
import Packages.Package;

public class ClientConection extends Thread {

	private static final int LOGINREQUESTID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID = 3;
	private static final int STARTGAMEREQUESTID = 4;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREQUESTID = 9;
	private static final int ENDCONECTIONREQUESTID = 10;
	private Socket socket;

	// private Integer clientID;

	public ClientConection(Integer clientID, Socket socket) {
		// this.clientID = clientID;
		this.socket = socket;
	}

	public void run() {
		Boolean endConection = false;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;

		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());

			while (!endConection) {
				Package packageOut = null;

				Package packageIn = (Package) inputStream.readObject();

				switch (packageIn.getPackageID()) {
				case LOGINREQUESTID: // Pedido de logeo del cliente
					LoginRequest loginRequest = (LoginRequest) packageIn;
					packageOut = new LoginResponse(validateClient(loginRequest));
					break;
				case CREATEGAMEREQUESTID: // Creacion de partida
					GameRequest gameRequest = (GameRequest) packageIn;
					Game game = Game.getGame();
					game.setGameName(gameRequest.getGameName());
					game.setMaxPlayers(gameRequest.getMaxPlayers());
					game.setQuestionsID(gameRequest.getQuestionsID());
					break;
				case PLAYERJOINREQUESTID: // Jugador uniendose a partida
					Game.getGame().addPlayer(socket);
					break;
				case STARTGAMEREQUESTID: // Comenzar partida
					Game.getGame().start();
					break;
				case POINTSTABLEREQUESTID:
					// Busco la tabla de puntajes de la base de datos
					// Creo el paquete para enviar la tabla al cliente que la
					// solicito
					break;
				case ADDQUESTIONREQUESTID: // Agregar pregunta
					// Question question = (Question) inputStream.readObject();
					// Guardar en la DB
					break;
				case ENDCONECTIONREQUESTID: // Fin conexion
					endConection = true;
				}

				if(!endConection && packageOut != null) outputStream.writeObject(packageOut);

			}
			if (outputStream != null) outputStream.close();
			if (inputStream != null) inputStream.close();
			System.out.println("Conexion Finalizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Integer validateClient(LoginRequest client) {
		return -1;
	}

}
