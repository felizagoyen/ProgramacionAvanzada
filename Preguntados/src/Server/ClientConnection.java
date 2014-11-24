package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import Packages.*;
import Packages.Package;

public class ClientConnection {
	private static final ClientConnection instance = new ClientConnection();
	private static final int MAXCONNECTIONS = 10;
	private ArrayList<Socket> clientSockets = new ArrayList<Socket>();;
	private ArrayList<ObjectOutputStream> outputStream = new ArrayList<ObjectOutputStream>();;
	private ArrayList<ObjectInputStream> inputStream = new ArrayList<ObjectInputStream>();;
	private ArrayList<Semaphore> semaphores = new ArrayList<Semaphore>();
	
	private ClientConnection() {
		for(int x = 0; x < MAXCONNECTIONS; x++) {
			clientSockets.add(null);
			outputStream.add(null);
			inputStream.add(null);
			semaphores.add(new Semaphore(1));
		}
	}
	
	public static ClientConnection getInstance() {
		return instance;
	}
	
	public Socket getClientSocket(int index) {
		return clientSockets.get(index);
	}

	public void sendPackage(int index, Package pack) throws Exception {
		outputStream.get(index).writeObject(pack);
	}

	public Package readPackage(int index) throws Exception {
		return ((Package) inputStream.get(index).readObject());
	}
	
	public void setClientConnection(int index, Socket socket) {
		try {
		clientSockets.set(index, socket);
		outputStream.set(index, new ObjectOutputStream(socket.getOutputStream()));
		inputStream.set(index, new ObjectInputStream(socket.getInputStream()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeOutputStream(int index) throws Exception {
		if(outputStream.get(index) != null) {
			outputStream.get(index).close();
			outputStream.set(index, null);
		}
	}
	
	public void closeInputStream(int index) throws Exception {
		if(inputStream.get(index) != null) {
			inputStream.get(index).close();
			inputStream.set(index, null);
		}
	}
	
	public int getFreeIndexSocket() {
		for(int x = 0; x < MAXCONNECTIONS; x++)
			if(clientSockets.get(x) == null) return x; 
		return -1;
	}
	
	public void blockSocket(int index) {
		try {
			semaphores.get(index).acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void releaseSocket(int index) {
		try {
			semaphores.get(index).release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeSocket(int index) {
		clientSockets.set(index, null);
	}
	
	
}
