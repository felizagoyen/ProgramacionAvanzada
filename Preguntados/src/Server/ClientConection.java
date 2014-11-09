package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.*;

public class ClientConection extends Thread {

	private static final int LOGINREQUESTID = 1;
	private static final int LOGINRESPONSEID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID= 3;
	private static final int STARTGAMEREQUESTID = 4;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREQUESTID = 9;
	private static final int ENDCONECTIONREQUESTID = 10;
	private Socket socket;
//	private Integer clientID;
	
	public ClientConection(Integer clientID, Socket socket) {
//		this.clientID = clientID;
		this.socket = socket;
	}

	public void run() {
		Boolean endConection = false;
		while (!endConection) {
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Integer idPackageOut = null;
				Object packageOut = null;
				
				switch((Integer) inputStream.readObject()) {
				case LOGINREQUESTID: //Pedido de logeo del cliente
					LoginRequest loginRequest = (LoginRequest) inputStream.readObject();
					Integer clientType = validateClient(loginRequest);
					idPackageOut = LOGINRESPONSEID;
				 	packageOut = new LoginResponse(clientType);
				case CREATEGAMEREQUESTID: //Creacion de partida
					GameRequest gameRequest = (GameRequest) inputStream.readObject();
					Game game = Game.getGame();
					game.setGameName(gameRequest.getGameName());
					game.setMaxPlayers(gameRequest.getMaxPlayers());
					game.setQuestionsID(gameRequest.getQuestionsID());
				case PLAYERJOINREQUESTID: //Jugador uniendose a partida
					Game.getGame().addPlayer(socket);
				case STARTGAMEREQUESTID: //Comenzar partida
					Game.getGame().start();
				case POINTSTABLEREQUESTID:
					//Busco la tabla de puntajes de la base de datos
					//Creo el paquete para enviar la tabla al cliente que la solicito
				case ADDQUESTIONREQUESTID: //Agregar pregunta
					//Question question = (Question) inputStream.readObject();
					//Guardar en la DB
				case ENDCONECTIONREQUESTID: //Fin conexion
					endConection = true;
				}
				
				if(!endConection) {
					outputStream.writeObject(idPackageOut);
					outputStream.writeObject(packageOut);
				}
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
