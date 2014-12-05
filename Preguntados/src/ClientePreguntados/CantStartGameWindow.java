package ClientePreguntados;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CantStartGameWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3318299029244473079L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CantStartGameWindow dialog = new CantStartGameWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CantStartGameWindow() {
		setBounds(100, 100, 378, 263);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBounds(0, 0, 434, 229);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JTextArea txtrNoEsPosible = new JTextArea();
		txtrNoEsPosible.setBackground(SystemColor.control);
		txtrNoEsPosible.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtrNoEsPosible.setLineWrap(true);
		txtrNoEsPosible.setText("No es posible iniciar la partida,                                         se necesitan al menos dos jugadores.");
		txtrNoEsPosible.setBounds(31, 76, 403, 109);
		contentPanel.add(txtrNoEsPosible);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameCreatedAdminScreen gamecreated = new GameCreatedAdminScreen();
					gamecreated.setVisible(true);
					dispose();		
				}
			});
			okButton.setBounds(251, 196, 97, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
	}
}
