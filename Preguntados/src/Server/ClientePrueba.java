package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Packages.*;

public class ClientePrueba {

	public static void main(String[] args) {
		Socket socket;
		try{
			socket = new Socket("localhost", 10000);
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
				// Espera el tiempo para el contador
				EndTimeRequest timeToAnswer = (EndTimeRequest) inputObject.readObject();
				System.out.println(timeToAnswer.getEndTime());
				// Espera la pregunta a responder
				Question question = (Question) inputObject.readObject();
				System.out.println(question.getQuestion());
				// Responde la correcta
				outputObject.writeObject(new AnswerQuestion(question.getCorrectAnswer()));
				// Espera ver si es valida la respuesta
				AnswerQuestion answerQuestion = (AnswerQuestion) inputObject.readObject();
				System.out.println(answerQuestion.isCorrect());
				// Espera el tiempo para esperar a la proxima pregunta (puede usarse para ver la tabla parcial)
				EndTimeRequest timeToWait = (EndTimeRequest) inputObject.readObject();
				System.out.println(timeToWait.getEndTime());
			}
			outputObject.writeObject(new GenericPackage(10));
			inputObject.readObject();
			System.out.println("Termine bien!");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
