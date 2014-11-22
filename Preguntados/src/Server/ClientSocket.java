package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ClientSocket {
	private static final ClientSocket instance = new ClientSocket();
	private static final int MAXCONNECTIONS = 10;
	private ArrayList<Socket> clientSockets;
	private ArrayList<Semaphore> semaphores = new ArrayList<Semaphore>();
	
	private ClientSocket() {
		clientSockets = new ArrayList<Socket>(MAXCONNECTIONS);
		for(int x = 0; x < MAXCONNECTIONS; x++)
			clientSockets.add(null);
		for(int x = 0; x < MAXCONNECTIONS; x++)
			semaphores.add(new Semaphore(1));
	}
	
	public static ClientSocket getInstance() {
		return instance;
	}
	
	public Socket getClientSocket(int index) {
		return clientSockets.get(index);
	}
	
	public void setClientSocket(int index, Socket socket) {
		clientSockets.add(index, socket);
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
	
	
}
