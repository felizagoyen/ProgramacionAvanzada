package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextArea;

import Commons.Player;

import java.awt.SystemColor;
import java.util.ArrayList;

public class GameResultsWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea tiedManyUsersLabel;
	private JTextArea tiedWith1Label;
	private JLabel winnerLabel;
	private JLabel loserLabel;
	private ArrayList<Player> scoreTable;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public GameResultsWindow() {
		setBounds(100, 100, 444, 235);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 229);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		winnerLabel = new JLabel("Ganaste la partida!");
		winnerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		winnerLabel.setBounds(70, 69, 292, 43);
		contentPanel.add(winnerLabel);
		{
			JButton okButton = new JButton("Ver tabla de puntuaciones");
			okButton.setBounds(178, 164, 229, 22);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScoreGameTableScreen scoreTableScreen = new ScoreGameTableScreen(scoreTable);
					scoreTableScreen.setVisible(true);
			
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		loserLabel = new JLabel("Perdiste la partida!");
		loserLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		loserLabel.setBounds(70, 49, 292, 43);
		contentPanel.add(loserLabel);
		
		tiedWith1Label = new JTextArea();
		tiedWith1Label.setEditable(false);
		tiedWith1Label.setBackground(SystemColor.control);
		tiedWith1Label.setLineWrap(true);
		tiedWith1Label.setFont(new Font("Tahoma", Font.BOLD, 16));
		tiedWith1Label.setText("Saliste empatado en el primer puesto con otro usuario!");
		tiedWith1Label.setBounds(23, 49, 351, 97);
		contentPanel.add(tiedWith1Label);
		
		tiedManyUsersLabel = new JTextArea();
		tiedManyUsersLabel.setEditable(false);
		tiedManyUsersLabel.setText("Saliste empatado en el primer puesto con otros usuarios!");
		tiedManyUsersLabel.setLineWrap(true);
		tiedManyUsersLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		tiedManyUsersLabel.setBackground(SystemColor.menu);
		tiedManyUsersLabel.setBounds(23, 49, 351, 97);
		contentPanel.add(tiedManyUsersLabel);
		
		winnerLabel.setVisible(false);
		loserLabel.setVisible(false);
		tiedManyUsersLabel.setVisible(false);
		tiedWith1Label.setVisible(false);
	}
	
	public void setLabelWinnerStatus(Integer winnerstatus, Integer numberofwinners){
		if(winnerstatus == 1)
			winnerLabel.setVisible(true);
		else
			if(winnerstatus == -1)
				loserLabel.setVisible(true);
			else
				if(numberofwinners == 2)
					tiedWith1Label.setVisible(true);
				else
					tiedManyUsersLabel.setVisible(true);
	}

	public void setScoreTable(ArrayList<Player> scoreTable) {
		this.scoreTable = scoreTable;
	}
}
