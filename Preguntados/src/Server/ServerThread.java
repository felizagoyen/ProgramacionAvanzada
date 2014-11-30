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
	private String clientName = null;
	
	public ServerThread(Integer clientId) {
		this.clientId = clientId;
	}

	public void run() {
		Boolean endConection = false;
		Game game = null;
		try {
			ClientConnection clientConnectionInstance = ClientConnection.getInstance();
			
			while (!endConection) {
				Package packageOut = null;
				Package packageIn = clientConnectionInstance.readPackage(clientId);
				clientConnectionInstance.blockSocket(clientId);
				
				switch (packageIn.getPackageID()) {
				case LOGINREQUESTID: // Pedido de logeo del cliente
					LoginRequest loginRequest = (LoginRequest) packageIn;
					int loginStatus; // 0: Usuario Valido, -1: Usuario Invalido, -2: Usuario ya estaba loggeado
					String clientLoginName = loginRequest.getUser();
					Logger.info("Solicitud de loggeo recibida.");
					
					loginStatus = validateClient(loginRequest);
					if(loginStatus != -1) { 
						if(!clientConnectionInstance.clientIsLogged(clientLoginName)) {
							clientName = clientLoginName;
							clientConnectionInstance.getClient(clientId).setName(clientName);
							Logger.info("Se ha conectado el usuario " + clientName);
						} else {
							loginStatus = -2;
							Logger.warn("Se esta intentando ingresar con un usuario que ya esta loggeado. Nombre de usuario: " + clientLoginName);
						}
					} else {
						Logger.info("Los datos ingresados por el cliente " + clientId + " no se encuentra en la base de datos. Nombre de usuario: " + clientLoginName);
					}
					
					packageOut = new LoginResponse(loginStatus);
					break;
				case QUESTIONSREQUESTID: // Devuelve las preguntas por categoria  
					QuestionsRequest questionsRequest = (QuestionsRequest) packageIn;
					packageOut = new QuestionsResponse(getQuestionByCategory(questionsRequest.getCategory()));
					break;
				case CREATEGAMEREQUESTID: // Creacion de partida
					GameRequest gameRequest = (GameRequest) packageIn;
					game = Game.getGameInstance();
					Logger.info("Creando partida...");
					
					if(!game.isCreated()) {
						game.createGame(gameRequest.getGameName(), gameRequest.getMaxPlayers(), gameRequest.getQuestionsID());
						Logger.info("Partida creada correctamente.");
					} else {
						Logger.warn("La partida ya estaba creada");
					}
					
					break;
				case PLAYERJOINREQUESTID: // Jugador uniendose a partida
					int joinStatus; //-2: Ya estaba dentro el jugador, -1: No existe la partida
									// 0: La partida está llena. 1: El jugador se puede unir
									//-3: Partida ya iniciada.
					game = Game.getGameInstance();
					Logger.info(clientName + " solicita ingresar a la partida.");
					
					if(!game.isCreated()) {
						joinStatus = -1;
						Logger.warn("La partida no existe.");
					} else if(game.playerInGame(clientId)) {
						joinStatus = -2;
						Logger.warn("El jugador ya se encuentra dentro de la partida.");
					} else if(game.isStarted()) {
						joinStatus = -3;
						Logger.info("La partida ya comenzo. El jugador " + clientName + " no se pudo unir.");
					} else if(game.gameIsFull()) {
						joinStatus = 0;
						Logger.info("La partida está llena. El jugador " + clientName + " no se pudo unir.");
					} else {
						game.addPlayer(clientId, clientName);
						joinStatus = 1;
						Logger.info(clientName + " se ha unido a la partida.");
					}
					
					packageOut = new PlayerJoinResponse(joinStatus);
					break;
				case STARTGAMEREQUESTID: // Comenzar partida
					Boolean startGame = false; 
					game = Game.getGameInstance();
					Logger.info("Iniciando partida...");
					
					if(game.isCreated() && game.canStartGame()) {
						game.start();
						startGame = true;
						Logger.info("La partida se ha iniciado correctamente.");
					} else if(!game.isCreated()) {
						startGame = false;
						Logger.warn("Se intenta comenzar una partida que no fue creada");
					} else {
						startGame = false;
						Logger.warn("No se puede iniciar la partida por falta de jugadores");
					}
					
					packageOut = new StartGameResponse(startGame);
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
					Question question = (Question) packageIn;
					Logger.info("Insertando pregunta a la base de datos...");

					addQuestionToDB(question);
					packageOut = new AddQuestionResponse(true);
					
					Logger.info("Pregunta insertada correctamente.");
					break;
				case ANSWERQUESTIONREQUESTID: // Respuesta del jugador ante una pregunta 
					game = Game.getGameInstance();
					Logger.info(clientName + " ha enviado su respuesta.");
					
					if(game.isCreated() && game.getWaitingAnswer()) {
						AnswerQuestion answerQuestion = (AnswerQuestion) packageIn;
						game.setAnswer(clientId, answerQuestion.getAnswer());
						Logger.info("Respuesta recibida correctamente del cliente " + clientName);
					} else if(!game.isCreated()) {
						Logger.warn("El cliente " + clientName + " envio una respuesta cuando no existe la partida. ClientId: " + clientId);
					} else {
						Logger.warn("Respuesta recibida fuera del tiempo permitido del cliente " + clientName);
					}
					
					break;
				case ENDCONECTIONREQUESTID: // Fin conexion
					game = Game.getGameInstance();
					Logger.info("Finalizando conexion con el cliente " + clientId);

					if(game.isCreated())
						game.removePlayers(clientId);
					
					packageOut = new EndClientConnectionResponse();
					endConection = true;
				}

				if(packageOut != null) clientConnectionInstance.sendPackage(clientId, packageOut);
				clientConnectionInstance.releaseSocket(clientId);
			}
			if(clientName != null)
				Logger.info("El usuario " + clientName + " se ha desconectado.");

			clientConnectionInstance.closeOutputStream(clientId);
			clientConnectionInstance.closeInputStream(clientId);
			clientConnectionInstance.freeClient(clientId);
			
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
