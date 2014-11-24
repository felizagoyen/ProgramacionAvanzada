package ClientePreguntados;

import Packages.EndClientConectionRequest;

public class ClientePreguntados {


	public ClientePreguntados() {
		try {
			Connection.getInstance();
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

	
	public static void closeClient (){
		EndClientConectionRequest er = new EndClientConectionRequest();
		Connection.sendPackage(er);
		System.exit(0);
	}
	
}
