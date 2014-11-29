package ClientePreguntados;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Packages.AddQuestionResponse;
import Packages.AnswerQuestion;
import Packages.LoginResponse;
import Packages.Package;
import Packages.Question;
import Packages.QuestionsResponse;

public class ClientThread extends Thread {
	
	private LoginScreen loginscreen;
	private static JDialog JDialogScreen;
	private static JFrame JFrameScreen;
	private static Package packageIn;
	private static final int LOGINRESPONSEID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID = 3;
	private static final int STARTGAMERESPONSEID = 4;
	private static final int GAMERUNNINGID = 6;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREESPONSEID = 9;
	private static final int ENDCONNECTIONRESPONSEID = 10;
	private static final int QUESTIONSRESPONSEID = 11;
	private static final int ANSWERQUESTIONRESPONSEID = 13;
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
				case PLAYERJOINREQUESTID: // Jugador uniendose a partida
					
					break;
				case STARTGAMERESPONSEID: // Comenzar partida
					
					break;
				case POINTSTABLEREQUESTID:
			
					break;
					
				case GAMERUNNINGID:
					
					Question question = (Question) packageIn;
					((JoinPlayerGameWindow)JDialogScreen).setVisible(false);
					((RoundGameScreen)JFrameScreen).setVisible(false);
					((RoundGameScreen)JFrameScreen).enableButtons();
					((RoundGameScreen)JFrameScreen).setQuestion(question);
					((RoundGameScreen)JFrameScreen).setVisible(true);
					
					break;
				case ADDQUESTIONREESPONSEID: // Agregar pregunta
					AddQuestionResponse addResponse = (AddQuestionResponse) packageIn;
					if(addResponse.getValid() == true)
							((AddQuestionScreen)JFrameScreen).clearScreen();
					break;
					
				case ANSWERQUESTIONRESPONSEID:
					AnswerQuestion answer = (AnswerQuestion) packageIn;
					((RoundGameScreen)JFrameScreen).setLabelAnswer(answer.isCorrect());
//					if(answer.isCorrect())
//						
//					else
//						System.out.println("Respuesta incorrecta.");
					
					
					break;
				
				case ENDCONNECTIONRESPONSEID: // Fin conexion
					endConnection = true;
					Connection.endConnection();
				}

//					if(!endConnection && packageOut != null) Connection.sendPackage(packageOut);
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
