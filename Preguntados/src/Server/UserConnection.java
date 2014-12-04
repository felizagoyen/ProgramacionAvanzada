package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Commons.Package;
import Commons.User;

public class UserConnection {
	
	private static final UserConnection instance = new UserConnection();
	private static final int MAXCONNECTIONS = 50;
	private ArrayList<User> clients = new ArrayList<User>();
	private ArrayList<ObjectOutputStream> outputStream = new ArrayList<ObjectOutputStream>();;
	private ArrayList<ObjectInputStream> inputStream = new ArrayList<ObjectInputStream>();;
	private ArrayList<Semaphore> semaphores = new ArrayList<Semaphore>();
	
	private UserConnection() {
		for(int x = 0; x < MAXCONNECTIONS; x++) {
			clients.add(null);
			outputStream.add(null);
			inputStream.add(null);
			semaphores.add(new Semaphore(1));
		}
	}
	
	public static UserConnection getInstance() {
		return instance;
	}
	
	public void sendPackage(int index, Package pack) throws Exception {
		outputStream.get(index).writeObject(pack);
	}

	public Package readPackage(int index) throws Exception {
		return ((Package) inputStream.get(index).readObject());
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
	
	public void setClientConnection(User client) {
		try {
			clients.set(client.getId(), client);
			outputStream.set(client.getId(), new ObjectOutputStream(client.getSocket().getOutputStream()));
			inputStream.set(client.getId(), new ObjectInputStream(client.getSocket().getInputStream()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public User getClient(int index) {
		return clients.get(index);
	}
	
	public int getFreeIndexClient() {
		for(int x = 0; x < MAXCONNECTIONS; x++) 
			if(clients.get(x) == null) return x; 
		return -1;
	}
	
	public void freeClient(int index) {
		clients.set(index, null);
	}
	
	public Boolean clientIsLogged(String clientName) {
		for(User eachClient: clients)
			if(eachClient != null && eachClient.getName().equals(clientName))
				return true;
		return false;
	}
	
}
