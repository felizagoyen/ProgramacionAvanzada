package ClientePreguntados;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.*;

public class ClientePreguntados {

	public ClientePreguntados() {
		try {
			Socket socket = new Socket("localhost", 10000); 
			ObjectOutputStream outputObject = null;
			ObjectInputStream inputObject = null;

			Boolean end = false;
			outputObject = new ObjectOutputStream(socket.getOutputStream());
			inputObject = new ObjectInputStream(socket.getInputStream());
			while(!end) {
				outputObject.writeObject(new LoginRequest("pepe", "1234"));
				LoginResponse lo = (LoginResponse) inputObject.readObject();
				if(lo.getUserType().equals(-1)) {
					outputObject.writeObject(new EndClientConectionRequest());
					end = true; 
				}
			}
			inputObject.close();
			outputObject.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ClientePreguntados();
	}

}
