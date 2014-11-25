package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Packages.Question;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoundGameScreen extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrPregunta;
	private JButton respuesta1Button;
	private JButton respuesta2Button;
	private JButton respuesta3Button;
	private JButton respuesta4Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoundGameScreen frame = new RoundGameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RoundGameScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 268, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtrPregunta = new JTextArea();
		txtrPregunta.setLineWrap(true);
		txtrPregunta.setEditable(false);
		txtrPregunta.setText("Pregunta");
		txtrPregunta.setBounds(24, 21, 206, 121);
		contentPane.add(txtrPregunta);
		
		respuesta1Button = new JButton("Respuesta 1");
		respuesta1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		respuesta1Button.setBounds(67, 168, 120, 23);
		contentPane.add(respuesta1Button);
		
		respuesta2Button = new JButton("Respuesta 2");
		respuesta2Button.setBounds(67, 216, 120, 23);
		contentPane.add(respuesta2Button);
		
		respuesta3Button = new JButton("Respuesta 3");
		respuesta3Button.setBounds(67, 264, 120, 23);
		contentPane.add(respuesta3Button);
		
		respuesta4Button = new JButton("Respuesta 4");
		respuesta4Button.setBounds(67, 318, 120, 23);
		contentPane.add(respuesta4Button);
	}
	
	public void setQuestion (Question question){
		txtrPregunta.setText(question.getQuestion());
		respuesta1Button.setText(question.getCorrectAnswer());
		respuesta2Button.setText(question.getWrongAnswers().get(0));
		respuesta3Button.setText(question.getWrongAnswers().get(1));
		respuesta4Button.setText(question.getWrongAnswers().get(2));
	}
}
