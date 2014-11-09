package ClientePreguntados;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.LoginRequest;

public class ClientePreguntados {

	public ClientePreguntados() {
		try {
			Socket socket = new Socket("localhost", 10000); 
			ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());

			//Integer idPaquete = 1;
			//outputObject.writeObject(idPaquete);
			
			LoginRequest l = new LoginRequest("pepe", "1234");
			outputObject.writeObject(l);

			outputObject.close();
			inputObject.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ClientePreguntados();
	}

}
