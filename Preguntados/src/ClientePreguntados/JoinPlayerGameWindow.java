package ClientePreguntados;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import Commons.PlayerDisconnectPackage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinPlayerGameWindow extends JDialog {

	/**
	 * Launch the application.

	 * 
	 */
	private static final long serialVersionUID = -8041209032068911221L;
	private JLabel lblWaitGame;
	private JLabel lblIsJoined;
	private JLabel lblGameFull;
	private JLabel lblGameNotExist;
	private JLabel lblGameStarted;
	private JButton btnOkButton; 
	private JButton btnLeaveGame; 
	private Connection connection = Connection.getInstance();

	/**
	 * Create the dialog.
	 * @param userType 
	 */
	public JoinPlayerGameWindow(final Integer userType) {
		
		
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
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
		lblGameFull.setBounds(104, 96, 231, 56);
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
				if(userType == 1){
					
					UserMenuScreen usermenuscreen = new UserMenuScreen();
					usermenuscreen.setVisible(true);
				}
				else{
					AdminMenuScreen adminmenuscreen = new AdminMenuScreen();
					adminmenuscreen.setVisible(true);
				}
				dispose();
				connection.sendPackage(new PlayerDisconnectPackage(null));
			}
		});
		btnOkButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOkButton.setBounds(284, 221, 100, 23);
		getContentPane().add(btnOkButton);
		
		btnLeaveGame = new JButton("Salir de la partida y volver al menu principal");
		btnLeaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(userType == 1){
					UserMenuScreen usermenuscreen = new UserMenuScreen();
					usermenuscreen.setVisible(true);
				}
				else{
					AdminMenuScreen adminmenuscreen = new AdminMenuScreen();
					adminmenuscreen.setVisible(true);
				}
				dispose();
				connection.sendPackage(new PlayerDisconnectPackage(null));
			}
		});
		btnLeaveGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLeaveGame.setBounds(40, 221, 350, 23);
		getContentPane().add(btnLeaveGame);
		
		lblGameFull.setVisible(false);
		lblGameNotExist.setVisible(false);
		lblGameStarted.setVisible(false);
		lblIsJoined.setVisible(false);
		lblWaitGame.setVisible(false);
		btnOkButton.setVisible(false);
		btnLeaveGame.setVisible(false);

	}
	
	
	public void setLabelAndButton (Integer joinStatus){
		switch(joinStatus){
		case 1:
			lblIsJoined.setVisible(true);
			lblWaitGame.setVisible(true);
			btnLeaveGame.setVisible(true);
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
