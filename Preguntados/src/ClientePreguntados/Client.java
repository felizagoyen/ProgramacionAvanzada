package ClientePreguntados;

public class Client {


	public Client() {
		Connection.getInstance();
		LoginScreen loginscreen = new LoginScreen();
		new ClientThread(loginscreen).start();
		loginscreen.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Client();
	}

}
