package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Packages.*;

public class ClientePrueba {

	private static final int LOGINREQUESTID = 1;
	private static final int CREATEGAMEREQUESTID = 2;
	private static final int PLAYERJOINREQUESTID = 3;
	private static final int STARTGAMEREQUESTID = 4;
	private static final int POINTSTABLEREQUESTID = 8;
	private static final int ADDQUESTIONREQUESTID = 9;
	private static final int ENDCONECTIONREQUESTID = 10;
	private static final int QUESTIONSREQUESTID = 11;

	public static void main(String[] args) {
			
		try{
			Socket socket = new Socket("localhost", 10000);
			ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());
			
			ArrayList<Integer> questionsID = new ArrayList<Integer>();
			questionsID.add(1);
			questionsID.add(2);
			questionsID.add(3);
			questionsID.add(null);
			questionsID.add(null);
			questionsID.add(null);
			questionsID.add(null);
			questionsID.add(null);
			questionsID.add(null);
			questionsID.add(null);
			
			outputObject.writeObject(new GameRequest("pepe", 2, questionsID));
			outputObject.writeObject(new GenericPackage(3));
			outputObject.writeObject(new GenericPackage(4));
			
			for(int x = 0; x < 10; x++) {
				Question question = (Question) inputObject.readObject();
				System.out.println(question.getQuestion() + " - " + question.getCorrectAnswer() + " - " + question.getCategory());
			}
			outputObject.writeObject(new GenericPackage(10));
			inputObject.readObject();
			System.out.println("Termine bien!");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
