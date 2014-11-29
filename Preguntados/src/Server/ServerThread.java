package Server;

import java.util.ArrayList;

import Packages.*;
import Packages.Package;

public class ServerThread extends Thread {

	private static final int LOGINREQUESTID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID = 3;
	private static final int STARTGAMEREQUESTID = 4;
	private static final int CATEGORYREQUESTID = 5;
	private static final int ADDQUESTIONREQUESTID = 6;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ENDCONECTIONREQUESTID = 10;
	private static final int QUESTIONSREQUESTID = 11;
	private static final int ANSWERQUESTIONREQUESTID = 13;
	private Integer clientId;
	private String clientName;
	public ServerThread(Integer clientId) {
		this.clientId = clientId;
	}

	public void run() {
		Boolean endConection = false;
		Game game = Game.getGameInstance();
		try {
			ClientConnection clientConnectionInstance = ClientConnection.getInstance();
			
			while (!endConection) {
				Package packageOut = null;
				Package packageIn = clientConnectionInstance.readPackage(clientId);
				clientConnectionInstance.blockSocket(clientId);
				
				switch (packageIn.getPackageID()) {
				case LOGINREQUESTID: // Pedido de logeo del cliente
					LoginRequest loginRequest = (LoginRequest) packageIn;
					int loginStatus = validateClient(loginRequest);
					if(loginStatus != -1) { 
						clientName = loginRequest.getUser();
						clientConnectionInstance.getClient(clientId).setName(clientName);
						Logger.info("Se ha conectado el usuario " + clientName);
					}
					packageOut = new LoginResponse(loginStatus);
					break;
				case QUESTIONSREQUESTID: // Devuelve las preguntas por categoria  
					QuestionsRequest questionsRequest = (QuestionsRequest) packageIn;
					packageOut = new QuestionsResponse(getQuestionByCategory(questionsRequest.getCategory()));
					break;
				case CREATEGAMEREQUESTID: // Creacion de partida
					Logger.info("Creando partida...");
					GameRequest gameRequest = (GameRequest) packageIn;
//					game = Game.getGameInstance();
					game.setGameName(gameRequest.getGameName());
					game.setMaxPlayers(gameRequest.getMaxPlayers());
					game.setQuestionsID(gameRequest.getQuestionsID());
					Logger.info("Partida creada correctamente.");
					break;
				case PLAYERJOINREQUESTID: // Jugador uniendose a partida
					Logger.info(clientName + " solicita ingresar a la partida.");
					game.addPlayer(clientId);
					Logger.info(clientName + " se ha unido a la partida.");
					break;
				case STARTGAMEREQUESTID: // Comenzar partida
					Logger.info("Iniciando partida...");
					game.start();
					Logger.info("La partida se ha iniciado correctamente.");
					break;
				case CATEGORYREQUESTID:
					//Category category = new
					break;
				case POINTSTABLEREQUESTID:
					// Busco la tabla de puntajes de la base de datos
					// Creo el paquete para enviar la tabla al cliente que la
					// solicito
					break;
				case ADDQUESTIONREQUESTID: // Agregar pregunta
					Logger.info("Insertando pregunta a la base de datos...");
					Question question = (Question) packageIn;
					addQuestionToDB(question);
					packageOut = new AddQuestionResponse(true);
					Logger.info("Pregunta insertada correctamente.");
					break;
				case ANSWERQUESTIONREQUESTID: // Respuesta del jugador ante una pregunta 
					if(game.getWaitingAnswer() == true) {
						Logger.info(clientName + " ha enviado su respuesta");
						AnswerQuestion answerQuestion = (AnswerQuestion) packageIn;
						game.setAnswer(clientId, answerQuestion.getAnswer());
					} else {
						Logger.warn("Respuesta recibida fuera del tiempo permitido del cliente " + clientName);
					}
					break;
				case ENDCONECTIONREQUESTID: // Fin conexion
					Logger.info("Finalizando conexion con el cliente " + clientId);
					packageOut = new EndClientConnectionResponse();
					endConection = true;
				}

				if(packageOut != null) clientConnectionInstance.sendPackage(clientId, packageOut);
				clientConnectionInstance.releaseSocket(clientId);
			}
			if(clientConnectionInstance.getClient(clientId).getName() != null)
				Logger.info("El usuario " + clientName + " se ha desconectado.");
			//clientConnectionInstance.closeOutputStream(clientId);
			//clientConnectionInstance.closeInputStream(clientId);
			//clientConnectionInstance.freeClient(clientId);
			Logger.info("Conexion finalizada correctamente con el cliente: " + clientId);
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
	
	private ArrayList<Question> getQuestionByCategory(String category) {
		DataBaseUtil db = new DataBaseUtil();
		return db.getQuestionByCategoryDB(category);
	}

}
