package ClientePreguntados;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Packages.LoginResponse;
import Packages.Package;
import Packages.QuestionsResponse;

public class ClientThread extends Thread {
	
	private LoginScreen loginscreen;
	private static JDialog JDialogScreen;
	private static JFrame JFrameScreen;
	private Package packageOut;
	private static Package packageIn;
	private static final int LOGINRESPONSEID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID = 3;
	private static final int STARTGAMEREQUESTID = 4;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREQUESTID = 9;
	private static final int ENDCONECTIONREQUESTID = 10;
	private static final int QUESTIONSRESPONSEID = 11;
	
	public static boolean ok;
	
	public ClientThread(LoginScreen loginscreen){
		this.loginscreen = loginscreen;
		
	}
	
	public void run() {
		Boolean endConection = false;

		try {


			while (!endConection) {

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
				case STARTGAMEREQUESTID: // Comenzar partida
					
					break;
				case POINTSTABLEREQUESTID:
			
					break;
				case ADDQUESTIONREQUESTID: // Agregar pregunta
		
					break;
				case ENDCONECTIONREQUESTID: // Fin conexion
					endConection = true;
				}

				if(!endConection && packageOut != null) Connection.sendPackage(packageOut);

			}
//			if (Conection. != null) outputStream.close();
//			if (inputStream != null) inputStream.close();
			System.out.println("Conexion Finalizada");
		} catch (Exception e) {
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
