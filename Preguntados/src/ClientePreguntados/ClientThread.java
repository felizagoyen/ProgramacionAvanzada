package ClientePreguntados;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import Commons.AddQuestionResponse;
import Commons.AnswerQuestion;
import Commons.LoginResponse;
import Commons.Package;
import Commons.PlayerJoinResponse;
import Commons.Question;
import Commons.QuestionsResponse;
import Commons.StartGameResponse;
import Commons.WinnerStatus;

public class ClientThread extends Thread {
	
	private LoginScreen loginscreen;
	private static JDialog JDialogScreen;
	private static JFrame JFrameScreen;
	private static Package packageIn;
	private static final int LOGINRESPONSEID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINRESPONSEID = 3;
	private static final int STARTGAMERESPONSEID = 4;
	private static final int GAMERUNNINGID = 6;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREESPONSEID = 9;
	private static final int ENDCONNECTIONRESPONSEID = 10;
	private static final int QUESTIONSRESPONSEID = 11;
	private static final int ENDTIMEID = 12;
	private static final int ANSWERQUESTIONRESPONSEID = 13;
	private static final int WINNERSTATUSID = 14;
	private Boolean endConnection = false;
	
	public ClientThread(LoginScreen loginscreen){
		this.loginscreen = loginscreen;
		
	}
	
	public void run() {
		

		try {


			while (!endConnection) {

				packageIn = (Package) Connection.recievePackage();

				switch (packageIn.getPackageID()) {
				case LOGINRESPONSEID: // Respuesta de logeo del servidor.

					LoginResponse loginresponse = (LoginResponse) packageIn;
					loginscreen.actionLogin(loginresponse);

					break;
				case QUESTIONSRESPONSEID:  
					
					QuestionsResponse questionsresponse = (QuestionsResponse) packageIn;
					((ChooseQuestionWindow) JDialogScreen).showQuestions(questionsresponse);
					
	
					break;
				case CREATEGAMEREQUESTID: // Creacion de partida
			
					break;
				case PLAYERJOINRESPONSEID: // Se pudo unir a la partida?
					PlayerJoinResponse playerjoinresponse = (PlayerJoinResponse) packageIn;
					((JoinPlayerGameWindow)JDialogScreen).setLabelAndButton(playerjoinresponse.getJoinStatus());
					((JoinPlayerGameWindow)JDialogScreen).setVisible(true);
					
					break;
				case STARTGAMERESPONSEID: // Se pudo comenzar la partida?
					StartGameResponse startgameresponse = (StartGameResponse) packageIn;
					if(!startgameresponse.getStartGame()){
						CantStartGameWindow cantStart = new CantStartGameWindow();
						cantStart.setVisible(true);
					}
					
					
					break;
				case POINTSTABLEREQUESTID:
			
					break;
					
				case GAMERUNNINGID:
					
					Question question = (Question) packageIn;
					

					
					((JoinPlayerGameWindow)JDialogScreen).setVisible(false);
					((RoundGameScreen)JFrameScreen).setVisible(false);
					((RoundGameScreen)JFrameScreen).enableButtonsAndRefreshComponents();
					((RoundGameScreen)JFrameScreen).setQuestion(question);
					((RoundGameScreen)JFrameScreen).setVisible(true);
					((RoundGameScreen)JFrameScreen).startTimer();
					
					break;
					
				case WINNERSTATUSID:
					WinnerStatus winnerstatus = (WinnerStatus) packageIn;
					if(winnerstatus.getStatus() == 1){
						//Ganaste!
					}if(winnerstatus.getStatus() == 0){
						//Saliste empatado en el primer puesto con otro user!
					}
					else
						//perdiste!
					
					break;
				case ADDQUESTIONREESPONSEID: // Agregar pregunta
					AddQuestionResponse addResponse = (AddQuestionResponse) packageIn;
					if(addResponse.getValid() == true)
							((AddQuestionScreen)JFrameScreen).clearScreen();
					break;
					
				case ENDTIMEID:
					break;
					
					
				case ANSWERQUESTIONRESPONSEID:
					AnswerQuestion answer = (AnswerQuestion) packageIn;
					((RoundGameScreen)JFrameScreen).setLabelAnswer(answer.isCorrect());
					
					
					break;
				
				case ENDCONNECTIONRESPONSEID: // Fin conexion
					endConnection = true;
					Connection.endConnection();
				}

			}
			System.out.println("Conexion Finalizada");
		} catch (Exception 
				e) {
			e.printStackTrace();
		}
	}
	
	public static Object recievePackage(){
		return packageIn;
	}


	public static void recieveScreen(JDialog screen) {
		JDialogScreen = screen;
		
	}
	
	public static void recieveScreen(JFrame screen){
		JFrameScreen = screen;
	}
	


}
