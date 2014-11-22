package ClientePreguntados;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
	
	
	private ObjectOutputStream outputObject;
	private ObjectInputStream inputObject;
	private Socket socket;
	private static Connection connection;
	
	private Connection (){
		try{
			socket = new Socket("localhost", 10000);
			outputObject = new ObjectOutputStream(socket.getOutputStream());
			inputObject = new ObjectInputStream(socket.getInputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static Connection getInstance(){
		
		if(connection == null){
			connection = new Connection();
		}
		return connection;
	}
	
	public static void sendPackage (Object paquete){
		try{
			connection.outputObject.writeObject(paquete);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Object recievePackage (){
		Object response = -1;
		try{
			response = connection.inputObject.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	public static void endConnection() {
		try {
			connection.inputObject.close();
			connection.outputObject.close();
			connection.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



}
