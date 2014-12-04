package Server;

import java.util.ArrayList;

import Commons.*;
import Commons.Package;

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
		Boolean gameStarted = false;
		Game game = null;
		
		try {
			UserConnection clientConnectionInstance = UserConnection.getInstance();
			
			while (!endConection) {
				Package packageOut = null;
				Package packageIn = clientConnectionInstance.readPackage(clientId);
				clientConnectionInstance.blockSocket(clientId);
				
				switch (packageIn.getPackageID()) {
				case LOGINREQUESTID: // Pedido de logeo del cliente
					UserLoginPackage userLogin = (UserLoginPackage) packageIn;
					int loginStatus; // 0: Usuario Valido, -1: Usuario Invalido, -2: Usuario ya estaba loggeado
					String clientLoginName = userLogin.getUser();
					Logger.info("Solicitud de loggeo recibida.");
					
					loginStatus = validateClient(userLogin);
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
					
					userLogin.setUserType(loginStatus);
					packageOut = userLogin; 

					break;
				case QUESTIONSREQUESTID: // Devuelve las preguntas por categoria  
					QuestionsByCategoryPackage questionsByCategory = (QuestionsByCategoryPackage) packageIn;

					ArrayList<Question> questions = getQuestionByCategory(questionsByCategory.getCategory());
					questionsByCategory.setQuestions(questions);
					packageOut = questionsByCategory;
					
					break;
				case CREATEGAMEREQUESTID: // Creacion de partida
					CreateGamePackage gameRequest = (CreateGamePackage) packageIn;
					game = Game.getGameInstance();
					Logger.info("Creando partida...");
					
					if(!game.isCreated()) {
						game.createGame(gameRequest.getGameName(), gameRequest.getMaxPlayers(), gameRequest.getQuestionsID());
						Logger.info("Partida creada correctamente.");
						game.addPlayer(clientId, clientName); //Al crear la partida el administrador se une.
						packageOut = new CreateGamePackage(true);
					} else {
						packageOut = new CreateGamePackage(false);
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
					}
					
					packageOut = new PlayerJoinPackage(joinStatus);
					break;
					
				case STARTGAMEREQUESTID: // Comenzar partida
					Boolean canStartGame = false; 
					game = Game.getGameInstance();
					Logger.info("Iniciando partida...");
					
					if(game.isCreated() && game.canStartGame()) {
						canStartGame = true;
						gameStarted = true;
						game.startGame();
						Logger.info("La partida se ha iniciado correctamente.");
					} else if(!game.isCreated()) {
						canStartGame = false;
						Logger.warn("Se intenta comenzar una partida que no fue creada");
					} else {
						canStartGame = false;
						Logger.warn("No se puede iniciar la partida por falta de jugadores");
					}
					
					packageOut = new StartGamePackage(canStartGame);
					break;
				case CATEGORYREQUESTID:
					Logger.info("El usuario " + clientName + " ha solicitado las categorias.");
					ArrayList<Category> categories = getAllCategories(); 
					packageOut = new CategoryPackage(categories);
					break;
				case POINTSTABLEREQUESTID:
					Logger.info("El usuario " + clientName + " ha solicitado la tabla de puntuacion historica.");
					ArrayList<User> topTen = getTopTenUsers();
					packageOut = new TopTenUserPackage(topTen);
					break;
				case ADDQUESTIONREQUESTID: // Agregar pregunta
					Question question = (Question) packageIn;
					Logger.info("Insertando pregunta a la base de datos...");

					addQuestionToDB(question);
					packageOut = new AddQuestionConfirmationPackage(true);
					
					Logger.info("Pregunta insertada correctamente.");
					break;
				case ANSWERQUESTIONREQUESTID: // Respuesta del jugador ante una pregunta 
					game = Game.getGameInstance();
					Logger.info(clientName + " ha enviado su respuesta.");
					
					if(game.isCreated() && game.getWaitingAnswer()) {
						AnswerQuestionPackage answerQuestion = (AnswerQuestionPackage) packageIn;
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
						game.removePlayer(clientId);
					
					packageOut = new EndClientConnectionPackage();
					endConection = true;
				}

				if(packageOut != null) clientConnectionInstance.sendPackage(clientId, packageOut);
				if(gameStarted == true && game.isCreated() == false) {
					gameStarted = false;
				}
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

	private Integer validateClient(UserLoginPackage client) {
		DataBaseUtil db = new DataBaseUtil();
		UserLoginPackage user = db.getUserDB(client.getUser());
		if(user != null && user.getPassword().equals(client.getPassword()))
			return user.getUserType();
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
	
	private ArrayList<Category> getAllCategories() {
		DataBaseUtil db = new DataBaseUtil();
		return db.getCategoryDB();
	}
	
	private ArrayList<User> getTopTenUsers() {
		DataBaseUtil db = new DataBaseUtil();
		return db.getTopTenUsersDB();
	}

}
