package ClientePreguntados;

import Commons.EndClientConnectionPackage;

public class ClientePreguntados {
	private Connection connection;


	public ClientePreguntados() {
		try {
			connection = Connection.getInstance();
			LoginScreen loginscreen = new LoginScreen(this);
			new ClientThread(loginscreen).start();
			loginscreen.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientePreguntados();
	}

	

	
}
