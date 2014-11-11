package ClientePreguntados;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class UserMenuScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// UserMenuScreen frame = new UserMenuScreen();
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
	public UserMenuScreen(final ClientePreguntados cliente) {
		setTitle("Preguntados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
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

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(65, 357, 162, 23);
		contentPane.add(btnNewButton_1);

	}

}
