package ClientePreguntados;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JLabel lblWaitGame;
	private JLabel lblIsJoined;
	private JLabel lblGameFull;
	private JLabel lblGameNotExist;
	private JLabel lblGameStarted;
	private JButton btnOkButton; 

	/**
	 * Create the dialog.
	 */
	public JoinPlayerGameWindow() {
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		lblWaitGame = new JLabel("Espere a que el administrador inicie la partida...");
		lblWaitGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWaitGame.setBounds(10, 125, 618, 20);
		getContentPane().add(lblWaitGame);
		
		lblIsJoined = new JLabel("Se unio correctamente!");
		lblIsJoined.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIsJoined.setBounds(94, 29, 231, 56);
		getContentPane().add(lblIsJoined);
		
		lblGameFull = new JLabel("La partida est\u00E1 llena.");
		lblGameFull.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGameFull.setBounds(64, 175, 231, 56);
		getContentPane().add(lblGameFull);
		
		lblGameNotExist = new JLabel("Imposible unirse. La partida no existe.");
		lblGameNotExist.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGameNotExist.setBounds(40, 76, 344, 56);
		getContentPane().add(lblGameNotExist);
		
		lblGameStarted = new JLabel("No es posible unirse. La partida ya ha comenzado.");
		lblGameStarted.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGameStarted.setBounds(20, 96, 546, 56);
		getContentPane().add(lblGameStarted);
		
		btnOkButton = new JButton("Ok");
		btnOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMenuScreen usermenuscreen = new UserMenuScreen();
				usermenuscreen.setVisible(true);
				dispose();
			}
		});
		btnOkButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOkButton.setBounds(305, 209, 100, 23);
		getContentPane().add(btnOkButton);
		
		lblGameFull.setVisible(false);
		lblGameNotExist.setVisible(false);
		lblGameStarted.setVisible(false);
		lblIsJoined.setVisible(false);
		lblWaitGame.setVisible(false);
		btnOkButton.setVisible(false);

	}
	
	
	public void setLabelAndButton (Integer joinStatus){
		switch(joinStatus){
		case 1:
			lblIsJoined.setVisible(true);
			lblWaitGame.setVisible(true);
			break;
		case 0:
			lblGameFull.setVisible(true);
			btnOkButton.setVisible(true);
			break;
		case -1:
			lblGameNotExist.setVisible(true);
			btnOkButton.setVisible(true);
			break;
		case -3:
			lblGameStarted.setVisible(true);
			btnOkButton.setVisible(true);
		}
	}
}
