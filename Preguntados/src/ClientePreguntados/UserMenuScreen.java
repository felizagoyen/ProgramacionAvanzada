package ClientePreguntados;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JButton;

import Commons.EndClientConnectionPackage;
import Commons.PlayerJoinPackage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class UserMenuScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2554687664946344696L;
	private JPanel contentPane;

	/**
	 * Launch the application.


	/**
	 * Create the frame.
	 */
	public UserMenuScreen() {
		setTitle("Preguntados");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new ClosingListener());
		setBounds(100, 100, 300, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea txtrPreguntados = new JTextArea();
		txtrPreguntados.setEditable(false);
		txtrPreguntados.setBackground(new Color(255, 153, 0));
		txtrPreguntados.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		txtrPreguntados.setText("Preguntados");
		txtrPreguntados.setBounds(45, 31, 202, 51);
		contentPane.add(txtrPreguntados);

		JButton jUnirsePartidaButton = new JButton("Unirse a una partida");
		jUnirsePartidaButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jUnirsePartidaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinPlayerGameWindow joinplayergamewindow = new JoinPlayerGameWindow();
				RoundGameScreen rgs = new RoundGameScreen();
				ClientThread.recieveScreen(rgs);
				ClientThread.recieveScreen(joinplayergamewindow);
				PlayerJoinPackage joinrequest = new PlayerJoinPackage();
				Connection.sendPackage(joinrequest);
				dispose();
		
			}
		});
		jUnirsePartidaButton.setBounds(65, 133, 162, 23);
		contentPane.add(jUnirsePartidaButton);

		JButton btnNewButton = new JButton("Tabla hist\u00F3rica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(65, 182, 162, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientePreguntados.closeClient();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(65, 357, 162, 23);
		contentPane.add(btnNewButton_1);

	}

}
