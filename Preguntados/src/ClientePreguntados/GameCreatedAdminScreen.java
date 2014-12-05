package ClientePreguntados;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

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
	private Connection connection = Connection.getInstance();


	public GameCreatedAdminScreen(Integer maxPlayersInGame) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new ClosingListener());
		setBounds(100, 100, 389, 401);
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
				dispose();
			}
		});
		btnIniciarPartida.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnIniciarPartida.setBounds(68, 303, 217, 59);
		contentPane.add(btnIniciarPartida);
		
		JList list = new JList();
		list.setBounds(84, 90, 182, 191);
		contentPane.add(list);
		
		JLabel lblJugadoresDentroDe = new JLabel("Jugadores dentro de la partida: ");
		lblJugadoresDentroDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJugadoresDentroDe.setBounds(39, 33, 248, 46);
		contentPane.add(lblJugadoresDentroDe);
		
		JLabel lblXxmax = new JLabel("/xMax");
		lblXxmax.setText("/" + maxPlayersInGame);
		lblXxmax.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblXxmax.setBounds(252, 49, 73, 14);
		contentPane.add(lblXxmax);
		
		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblX.setBounds(242, 49, 25, 14);
		contentPane.add(lblX);
	}
}
