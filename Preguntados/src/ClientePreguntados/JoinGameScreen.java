package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;

public class JoinGameScreen extends JFrame {


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinGameScreen frame = new JoinGameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JoinGameScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jugadores/Tama\u00F1oDePartida");
		lblNewLabel.setBounds(21, 148, 148, 14);
		contentPane.add(lblNewLabel);
		
		JButton jNombrePartidaButton = new JButton("Nombre Partida");
		jNombrePartidaButton.setBounds(21, 88, 115, 23);
		contentPane.add(jNombrePartidaButton);
		
		JLabel lblNewLabel_1 = new JLabel("Estado de la partida");
		lblNewLabel_1.setBounds(21, 196, 104, 14);
		contentPane.add(lblNewLabel_1);
	}

}
