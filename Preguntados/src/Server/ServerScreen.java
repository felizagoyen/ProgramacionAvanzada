package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerScreen extends JFrame {

	private JPanel contentPane;
	private static JTextArea textArea = new JTextArea();
	private JLabel lblElServidorEsta;;



	public ServerScreen() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 48, 430, 381);
		contentPane.add(scrollPane);
		textArea.setEditable(false);
	
		scrollPane.setViewportView(textArea);
		
		lblElServidorEsta = new JLabel("El servidor esta activo.");
		lblElServidorEsta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblElServidorEsta.setBounds(23, 22, 184, 15);
		contentPane.add(lblElServidorEsta);
		
		JButton btnDesconectarse = new JButton("Desconectarse");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDesconectarse.setBounds(326, 442, 127, 23);
		contentPane.add(btnDesconectarse);
	}
	
	
	public static void printLog (String log){
		textArea.append(log + "\n");
	}
}
