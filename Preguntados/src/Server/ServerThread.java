package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.*;
import Packages.Package;

public class ServerThread extends Thread {

	private static final int LOGINREQUESTID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID = 3;
	private static final int STARTGAMEREQUESTID = 4;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREQUESTID = 9;
	private static final int ENDCONECTIONREQUESTID = 10;
	private static final int QUESTIONSREQUESTID = 11;
	private Integer clientID;

	public ServerThread(Integer clientID) {
		this.clientID = clientID;
	}

	public void run() {
		Boolean endConection = false;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;

		try {
			ClientSocket clientSocketInstance = ClientSocket.getInstance();
			Socket socket = clientSocketInstance.getClientSocket(clientID);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			while (!endConection) {
				Package packageOut = null;
				Package packageIn = (Package) inputStream.readObject();
				clientSocketInstance.blockSocket(clientID);
				
				switch (packageIn.getPackageID()) {
				case LOGINREQUESTID: // Pedido de logeo del cliente
					LoginRequest loginRequest = (LoginRequest) packageIn;
					packageOut = new LoginResponse(validateClient(loginRequest));
					break;
				case QUESTIONSREQUESTID:  // Lo agregamos para probar si como cliente recibiamos correcamente las preguntas 
										  //que nos mandaría la base da datos para hacer la elección al crear la partida.
					QuestionsRequest questionsRequest = (QuestionsRequest) packageIn;
					packageOut = new QuestionsResponse ();
					break;
				case CREATEGAMEREQUESTID: // Creacion de partida
					GameRequest gameRequest = (GameRequest) packageIn;
					Game game = Game.getGame();
					game.setGameName(gameRequest.getGameName());
					game.setMaxPlayers(gameRequest.getMaxPlayers());
					game.setQuestionsID(gameRequest.getQuestionsID());
					break;
				case PLAYERJOINREQUESTID: // Jugador uniendose a partida
					Game.getGame().addPlayer(clientID);
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
					Question question = (Question) packageIn;
					addQuestionToDB(question);
					packageOut = new AddQuestionResponse(true);
					break;
				case ENDCONECTIONREQUESTID: // Fin conexion
					packageOut = new EndClientConnectionResponse();
					endConection = true;
				}

				if(packageOut != null) outputStream.writeObject(packageOut);
				clientSocketInstance.releaseSocket(clientID);
			}
			if (outputStream != null) outputStream.close();
			if (inputStream != null) inputStream.close();
			System.out.println("Conexion Finalizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Integer validateClient(LoginRequest client) {
		DataBaseUtil db = new DataBaseUtil();
		User user = db.getUserDB(client.getUser());
		if(user != null && user.getPass().equals(client.getPassword()))
			return user.getTipo();
		return -1;
	}
	
	private void addQuestionToDB(Question question) {
		DataBaseUtil db = new DataBaseUtil();
		db.setQuestionDB(question);
	}

}