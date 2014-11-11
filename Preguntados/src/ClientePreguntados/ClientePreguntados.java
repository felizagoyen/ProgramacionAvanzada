package ClientePreguntados;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Packages.*;

public class ClientePreguntados {

	private ObjectOutputStream outputObject;
	private ObjectInputStream inputObject;
	private Socket socket;

	public ClientePreguntados() {
		try {
			socket = new Socket("localhost", 10000);
			outputObject = null;
			inputObject = null;

			// Boolean end = false;
			outputObject = new ObjectOutputStream(socket.getOutputStream());
			inputObject = new ObjectInputStream(socket.getInputStream());
			LoginScreen loginscreen = new LoginScreen(this);
			loginscreen.setVisible(true);
			// while(!end) {
			// // outputObject.writeObject(new LoginRequest("pepe", "1234"));
			// // LoginResponse lo = (LoginResponse) inputObject.readObject();
			// // if(lo.getUserType().equals(-1)) {
			// // outputObject.writeObject(new EndClientConectionRequest());
			// // end = true;
			// // }
			// }
			// inputObject.close();
			// outputObject.close();
			// socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviarPaquete(Object paquete) {
		try {

			outputObject.writeObject(paquete);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object recibirPaquete() {
		Object response = -1;
		try {
			response = inputObject.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public void endConection() {
		try {
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
