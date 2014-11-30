package ClientePreguntados;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Commons.CreateGamePackage;
import Commons.PlayerJoinPackage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;

public class CreateGameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365162775440150670L;
	private JPanel contentPane;
	private JTextField nombrePartidaTextField;
	private JTextField cantMaxTextField;
	private ArrayList<Integer> questionsID = new ArrayList<Integer>();

	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateGameScreen frame = new CreateGameScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CreateGameScreen() {
		for(int i = 0; i < 10; i++)
			questionsID.add(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la partida");
		lblNombreDeLa.setBounds(10, 62, 113, 25);
		contentPane.add(lblNombreDeLa);
		
		nombrePartidaTextField = new JTextField();
		nombrePartidaTextField.setBounds(188, 64, 98, 20);
		contentPane.add(nombrePartidaTextField);
		nombrePartidaTextField.setColumns(10);
		
		JLabel lblCantidadMaximaDe = new JLabel("Cantidad m\u00E1xima de jugadores");
		lblCantidadMaximaDe.setBounds(10, 111, 163, 20);
		contentPane.add(lblCantidadMaximaDe);
		
		cantMaxTextField = new JTextField();
		cantMaxTextField.setBounds(188, 111, 98, 20);
		contentPane.add(cantMaxTextField);
		cantMaxTextField.setColumns(10);
		
		JButton btnElegirPreguntas = new JButton("Elegir preguntas");
		btnElegirPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseQuestionsScreen choosequestionsscreen = new ChooseQuestionsScreen();
				choosequestionsscreen.setVisible(true);
				setVisible(false);
			}
		});
		btnElegirPreguntas.setBounds(23, 164, 131, 20);
		contentPane.add(btnElegirPreguntas);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateGamePackage gamerequest = new CreateGamePackage(nombrePartidaTextField.getText(),  Integer.parseInt(cantMaxTextField.getText()), questionsID);
				Connection.sendPackage(gamerequest);
				JoinPlayerGameWindow joinadmingamewindow = new JoinPlayerGameWindow();
				RoundGameScreen roundgamescreen = new RoundGameScreen();
				ClientThread.recieveScreen(joinadmingamewindow);
				ClientThread.recieveScreen(roundgamescreen);
				GameCreatedAdminScreen gamecreated = new GameCreatedAdminScreen();
				setVisible(false);
				gamecreated.setVisible(true);
			}
		});
		btnNewButton.setBounds(277, 211, 131, 21);
		contentPane.add(btnNewButton);
	}
	
	
	public void receiveQuestionsID(ArrayList<Integer> qID){
		questionsID = qID;
	}
}
