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
		private final JTextArea jUsuarioInexistenteTextArea;
		private final ClientePreguntados cliente;
		private final JTextArea jCamposVaciosTextArea;

		public ActionLogin(JTextArea jUsuarioInexistenteTextArea,
				ClientePreguntados cliente, JTextArea jCamposVaciosTextArea) {
			this.jUsuarioInexistenteTextArea = jUsuarioInexistenteTextArea;
			this.cliente = cliente;
			this.jCamposVaciosTextArea = jCamposVaciosTextArea;
		}

		public void actionPerformed(ActionEvent arg0) {

			if (jUserTextField.getText().isEmpty() || jPasswordField.getPassword().toString().isEmpty()){
				jUsuarioInexistenteTextArea.setVisible(false);
				jCamposVaciosTextArea.setVisible(true);					
			}
			else {
				LoginRequest loginrequest = new LoginRequest(jUserTextField.getText(), jPasswordField.getPassword().toString());
				// ClientePreguntados cliente = new ClientePreguntados();
				cliente.enviarPaquete(loginrequest);
				LoginResponse loginresponse = (LoginResponse) cliente.recibirPaquete();
				setVisible(false);
				if (loginresponse.getUserType() == 0) {
					AdminMenuScreen adminscreen = new AdminMenuScreen(cliente);
					adminscreen.setVisible(true);
				} else {
					if(loginresponse.getUserType() == 1){
						UserMenuScreen userscreen = new UserMenuScreen(cliente);
						userscreen.setVisible(true);
					}else
					{
						jCamposVaciosTextArea.setVisible(false);
						jUsuarioInexistenteTextArea.setVisible(true);
					}
				}

			}
		}
	}

	private static final long serialVersionUID = -560582234414629430L;
	private JPanel contentPane;
	private JTextField jUserTextField;
	private JPasswordField jPasswordField;

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
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Usuario");
		lblUsername.setBounds(31, 108, 46, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(31, 160, 74, 14);
		contentPane.add(lblPassword);

		jUserTextField = new JTextField();
		jUserTextField.setBounds(136, 105, 86, 23);
		contentPane.add(jUserTextField);
		jUserTextField.setColumns(10);

		final JTextArea jCamposVaciosTextArea = new JTextArea();
		jCamposVaciosTextArea.setEditable(false);
		jCamposVaciosTextArea.setBackground(new Color(255, 153, 0));
		jCamposVaciosTextArea.setLineWrap(true);
		jCamposVaciosTextArea.setText("Alguno de los dos campos est\u00E1 vac\u00EDo, por favor completelos.");
		jCamposVaciosTextArea.setBounds(31, 329, 208, 67);
		jCamposVaciosTextArea.setVisible(false);
		contentPane.add(jCamposVaciosTextArea);
		
		final JTextArea jUsuarioInexistenteTextArea = new JTextArea();
		jUsuarioInexistenteTextArea.setBackground(new Color(255, 153, 0));
		jUsuarioInexistenteTextArea.setLineWrap(true);
		jUsuarioInexistenteTextArea.setEditable(false);
		jUsuarioInexistenteTextArea.setVisible(false);
		jUsuarioInexistenteTextArea.setText("Los datos ingresados no se encuentran en la base de datos.");
		jUsuarioInexistenteTextArea.setBounds(31, 329, 240, 74);
		contentPane.add(jUsuarioInexistenteTextArea);

		JButton jLoginButton = new JButton("Entrar");
		

		jLoginButton.addActionListener(new ActionLogin(jUsuarioInexistenteTextArea, cliente, jCamposVaciosTextArea));
		jLoginButton.setBounds(84, 209, 89, 23);
		contentPane.add(jLoginButton);

		jPasswordField = new JPasswordField();
		
		jPasswordField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					new ActionLogin (jUsuarioInexistenteTextArea, cliente, jCamposVaciosTextArea).actionPerformed(null);
			}
		});
		jPasswordField.setBounds(136, 157, 86, 20);
		contentPane.add(jPasswordField);

		JButton jSalirButton = new JButton("Salir");
		jSalirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EndClientConectionRequest er = new EndClientConectionRequest();
				cliente.enviarPaquete(er);
				cliente.endConection();
				System.exit(NORMAL);
			}
		});
		jSalirButton.setBounds(84, 255, 89, 23);
		contentPane.add(jSalirButton);
		

	}
}
