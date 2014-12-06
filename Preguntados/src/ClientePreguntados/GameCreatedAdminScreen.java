package ClientePreguntados;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import Commons.PlayerDisconnectPackage;
import Commons.StartGamePackage;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JLabel;

public class GameCreatedAdminScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6472066543008643691L;
	private JPanel contentPane;
	private JList<String> playersList;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JLabel playersInGameLabel;
	private Connection connection = Connection.getInstance();
	private JButton btnNewButton;


	public GameCreatedAdminScreen(Integer maxPlayersInGame, String adminName) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new ClosingListener());
		setBounds(100, 100, 356, 401);
		setTitle(LoginScreen.getTitleGame());
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
				connection.sendPackage(start);
				
			}
		});
		btnIniciarPartida.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnIniciarPartida.setBounds(53, 260, 217, 59);
		contentPane.add(btnIniciarPartida);
		
		playersList = new JList<String>();
		playersList.setBounds(72, 58, 182, 191);
		contentPane.add(playersList);
		
		JLabel lblJugadoresDentroDe = new JLabel("Jugadores dentro de la partida: ");
		lblJugadoresDentroDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJugadoresDentroDe.setBounds(39, 11, 248, 46);
		contentPane.add(lblJugadoresDentroDe);
		
		JLabel lblXxmax = new JLabel("/xMax");
		lblXxmax.setText("/" + maxPlayersInGame);
		lblXxmax.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblXxmax.setBounds(254, 27, 73, 14);
		contentPane.add(lblXxmax);
		
		playersInGameLabel = new JLabel("1");
		playersInGameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		playersInGameLabel.setBounds(241, 27, 25, 14);
		contentPane.add(playersInGameLabel);
		
		listModel.addElement(adminName);
		playersList.setModel(listModel);
		
		btnNewButton = new JButton("Cancelar partida y volver al menu principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenuScreen adminmenuscreen = new AdminMenuScreen();
				adminmenuscreen.setVisible(true);
				connection.sendPackage(new PlayerDisconnectPackage(null, true));
				dispose();
			}
		});
		btnNewButton.setBounds(23, 339, 317, 23);
		contentPane.add(btnNewButton);
	}


	public void playerHasJoined(String userName) {
		Integer players = Integer.parseInt(playersInGameLabel.getText());
		players++;
		playersInGameLabel.setText(players.toString());
		listModel.addElement(userName);
		playersList.setModel(listModel);
	}


	public void playerHasLeft(String userName) {
		Integer players = Integer.parseInt(playersInGameLabel.getText());
		players--;
		playersInGameLabel.setText(players.toString());
		listModel.removeElement(userName);		
		playersList.setModel(listModel);
	}
}
