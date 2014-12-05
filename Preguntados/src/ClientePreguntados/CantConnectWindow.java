package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class CantConnectWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();


	public CantConnectWindow() {
		setTitle("Preguntados - ERROR");
		setBounds(100, 100, 470, 178);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("No se ha podido establecer la conexi\u00F3n con el servidor.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(21, 43, 417, 60);
		contentPanel.add(lblNewLabel);
	}
}
