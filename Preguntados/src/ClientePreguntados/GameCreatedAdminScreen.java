package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import Commons.StartGamePackage;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameCreatedAdminScreen extends JFrame {

	private JPanel contentPane;


	public GameCreatedAdminScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIniciarPartida = new JButton("INICIAR PARTIDA");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartGamePackage start = new StartGamePackage();
				Connection.sendPackage(start);
				dispose();
			}
		});
		btnIniciarPartida.setFont(new Font("Tahoma", Font.PLAIN, 43));
		btnIniciarPartida.setBounds(10, 52, 419, 152);
		contentPane.add(btnIniciarPartida);
	}
}
