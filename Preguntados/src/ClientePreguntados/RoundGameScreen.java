package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Packages.AnswerQuestion;
import Packages.Question;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import java.awt.Font;

public class RoundGameScreen extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrPregunta;
	private JButton respuesta1Button;
	private JButton respuesta2Button;
	private JButton respuesta3Button;
	private JButton respuesta4Button;
	private JLabel lblRespuesta;

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
		txtrPregunta.setBounds(10, 29, 232, 116);
		contentPane.add(txtrPregunta);
		
		respuesta1Button = new JButton("Respuesta 1");
		respuesta1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerQuestion answer = new AnswerQuestion(respuesta1Button.getText());
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta1Button.setBounds(44, 169, 175, 23);
		contentPane.add(respuesta1Button);
		
		respuesta2Button = new JButton("Respuesta 2");
		respuesta2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerQuestion answer = new AnswerQuestion(respuesta2Button.getText());
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta2Button.setBounds(44, 216, 175, 23);
		contentPane.add(respuesta2Button);
		
		respuesta3Button = new JButton("Respuesta 3");
		respuesta3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerQuestion answer = new AnswerQuestion(respuesta3Button.getText());
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta3Button.setBounds(44, 264, 175, 23);
		contentPane.add(respuesta3Button);
		
		respuesta4Button = new JButton("Respuesta 4");
		respuesta4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerQuestion answer = new AnswerQuestion(respuesta4Button.getText());
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta4Button.setBounds(44, 318, 175, 23);
		contentPane.add(respuesta4Button);
		
		lblRespuesta = new JLabel("Respuesta correcta/incorrecta ");
		lblRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRespuesta.setBounds(44, 352, 198, 26);
		contentPane.add(lblRespuesta);
		lblRespuesta.setVisible(false);
	}
	
	public void setQuestion (Question question){
		txtrPregunta.setText(question.getQuestion());
		ArrayList<String> questions = new ArrayList<String>();
		questions.add(question.getCorrectAnswer());
		questions.add(question.getWrongAnswers().get(0));
		questions.add(question.getWrongAnswers().get(1));
		questions.add(question.getWrongAnswers().get(2));
		Collections.shuffle(questions);
		respuesta1Button.setText(questions.get(0));
		respuesta2Button.setText(questions.get(1));
		respuesta3Button.setText(questions.get(2));
		respuesta4Button.setText(questions.get(3));
	}

	public void enableButtons() {
		respuesta1Button.setEnabled(true);
		respuesta2Button.setEnabled(true);
		respuesta3Button.setEnabled(true);
		respuesta4Button.setEnabled(true);
		lblRespuesta.setVisible(false);
	}
	
	public void setLabelAnswer (boolean isCorrect){
		if(isCorrect)
			lblRespuesta.setText("Respuesta Correcta!");
		else
			lblRespuesta.setText("Respuesta Incorrecta!");
		
		lblRespuesta.setVisible(true);
			
	}
}
