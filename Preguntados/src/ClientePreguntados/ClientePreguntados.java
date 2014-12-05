package ClientePreguntados;

public class ClientePreguntados {


	public ClientePreguntados() {
		Connection.getInstance();
		LoginScreen loginscreen = new LoginScreen();
		new ClientThread(loginscreen).start();
		loginscreen.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClientePreguntados();
	}

	

	
}
