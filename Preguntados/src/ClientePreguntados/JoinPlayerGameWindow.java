package ClientePreguntados;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

public class JoinPlayerGameWindow extends JDialog {

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JoinPlayerGameWindow dialog = new JoinPlayerGameWindow();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8041209032068911221L;

	/**
	 * Create the dialog.
	 */
	public JoinPlayerGameWindow() {
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblEspereAQue = new JLabel("Espere a que el administrador inicie la partida...");
		lblEspereAQue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEspereAQue.setBounds(23, 113, 382, 28);
		getContentPane().add(lblEspereAQue);
		

	}
}
