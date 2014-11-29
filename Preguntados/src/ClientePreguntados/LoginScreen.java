package ClientePreguntados;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import Packages.EndClientConectionRequest;
import Packages.LoginRequest;
import Packages.LoginResponse;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class LoginScreen extends JFrame {

	public final class ActionLogin implements ActionListener {
		private  final JTextArea jUsuarioInexistenteTextArea;
		private final JTextArea jCamposVaciosTextArea;

		public ActionLogin(JTextArea jUsuarioInexistenteTextArea, JTextArea jCamposVaciosTextArea) {
			this.jUsuarioInexistenteTextArea = jUsuarioInexistenteTextArea;
			this.jCamposVaciosTextArea = jCamposVaciosTextArea;
		}

		public void actionPerformed(ActionEvent arg0) {

			if (jUserTextField.getText().isEmpty() || jPasswordField.getPassword().toString().isEmpty()){
				jUsuarioInexistenteTextArea.setVisible(false);
				jCamposVaciosTextArea.setVisible(true);					
			}
			else {
				LoginRequest loginrequest = new LoginRequest(jUserTextField.getText(), new String (jPasswordField.getPassword()));
				Connection.sendPackage(loginrequest);
			}
		}
	}

	private static final long serialVersionUID = -560582234414629430L;
	private JPanel contentPane;
	private JTextField jUserTextField;
	private JPasswordField jPasswordField;
	public static JTextArea jUsuarioInexistenteTextArea;
	public static JTextArea jCamposVaciosTextArea;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// LoginScreen frame = new LoginScreen();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public LoginScreen(final ClientePreguntados cliente) {
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new ClosingListener());
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Usuario");
		lblUsername.setBounds(31, 129, 46, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(31, 185, 74, 14);
		contentPane.add(lblPassword);

		jUserTextField = new JTextField();
		jUserTextField.setBounds(136, 125, 86, 23);
		contentPane.add(jUserTextField);
		jUserTextField.setColumns(10);

		/*final JTextArea*/ jCamposVaciosTextArea = new JTextArea();
		jCamposVaciosTextArea.setEditable(false);
		jCamposVaciosTextArea.setBackground(new Color(255, 153, 0));
		jCamposVaciosTextArea.setLineWrap(true);
		jCamposVaciosTextArea.setText("Alguno de los dos campos est\u00E1 vac\u00EDo, por favor completelos.");
		jCamposVaciosTextArea.setBounds(32, 367, 208, 67);
		jCamposVaciosTextArea.setVisible(false);
		contentPane.add(jCamposVaciosTextArea);
		
		/*final JTextArea*/ jUsuarioInexistenteTextArea = new JTextArea();
		jUsuarioInexistenteTextArea.setBackground(new Color(255, 153, 0));
		jUsuarioInexistenteTextArea.setLineWrap(true);
		jUsuarioInexistenteTextArea.setEditable(false);
		jUsuarioInexistenteTextArea.setVisible(false);
		jUsuarioInexistenteTextArea.setText("Los datos ingresados no se encuentran en la base de datos.");
		jUsuarioInexistenteTextArea.setBounds(31, 367, 240, 74);
		contentPane.add(jUsuarioInexistenteTextArea);

		JButton jLoginButton = new JButton("Entrar");
		
		

		jLoginButton.addActionListener(new ActionLogin(jUsuarioInexistenteTextArea, jCamposVaciosTextArea));
		jLoginButton.setBounds(84, 262, 89, 23);
		contentPane.add(jLoginButton);

		jPasswordField = new JPasswordField();
		
		jPasswordField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					new ActionLogin (jUsuarioInexistenteTextArea, jCamposVaciosTextArea).actionPerformed(null);
			}
		});
		jPasswordField.setBounds(136, 182, 86, 20);
		contentPane.add(jPasswordField);

		JButton jSalirButton = new JButton("Salir");
		jSalirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientePreguntados.closeClient();
//				EndClientConectionRequest er = new EndClientConectionRequest();
//				Connection.sendPackage(er);
//				System.exit(NORMAL);
			}
		});
		jSalirButton.setBounds(84, 310, 89, 23);
		contentPane.add(jSalirButton);
		

	}
	
	
	public void actionLogin(LoginResponse loginresponse){
		if (loginresponse.getUserType() == 0) {
			AdminMenuScreen adminscreen = new AdminMenuScreen();
			adminscreen.setVisible(true);
			setVisible(false);
			
		} else {
			if(loginresponse.getUserType() == 1){
				UserMenuScreen userscreen = new UserMenuScreen();
				userscreen.setVisible(true);
				setVisible(false);
				
			}else
			{
				jCamposVaciosTextArea.setVisible(false);
				jUsuarioInexistenteTextArea.setVisible(true);
			}
		}
		
		
	}
}
