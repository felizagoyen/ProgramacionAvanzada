package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Packages.*;

public class ClientConection extends Thread {

	private Socket socket;
	private Integer clientID;

	public ClientConection(Integer clientID, Socket socket) {
		this.clientID = clientID;
		this.socket = socket;
	}

	public void run() {
		//while (true) {
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Integer idPackageOut = null;
				Object packageOut = null;
				
				switch((Integer) inputStream.readObject()) {
				case 1:
					LoginRequest login = (LoginRequest) inputStream.readObject();
					Integer clientType = validateClient(login);
					idPackageOut = 1;
					Boolean isValid = clientType.equals(-1) ? false : true;
					packageOut = new LoginResponse(isValid, clientType); 
				case 2:
				
				}

				outputStream.writeObject(idPackageOut);
				outputStream.writeObject(packageOut);
				
				outputStream.close();
				inputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		//}
	}
	
	private Integer validateClient(LoginRequest client) {
		
		return -1;
	}
	

}
