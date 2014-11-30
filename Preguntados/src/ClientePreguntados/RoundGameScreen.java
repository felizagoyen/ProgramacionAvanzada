package ClientePreguntados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Commons.AnswerQuestionPackage;
import Commons.Question;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import java.awt.Font;

public class RoundGameScreen extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrPregunta;
	private JButton respuesta1Button;
	private JButton respuesta2Button;
	private JButton respuesta3Button;
	private JButton respuesta4Button;
	private JButton clickedButton;
	private JButton correctAnswerButton;
	private boolean questionAnswered;
	private JLabel lblRespuesta;
	private JLabel timerLabel;
	private JLabel lblRonda;
	private JLabel lblTiempoRestante;
	private int roundNumber = 0;
	
	class TimerThread extends Thread{
		public void run() {
			Integer timeRemaining = 30;
			while(timeRemaining > 0 && !questionAnswered){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				timeRemaining = Integer.parseInt(timerLabel.getText()) - 1;
				timerLabel.setText(timeRemaining.toString());	
			}
			respuesta1Button.setEnabled(false);
			respuesta2Button.setEnabled(false);
			respuesta3Button.setEnabled(false);
			respuesta4Button.setEnabled(false);
		
		}
		
}


	public RoundGameScreen() {
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new ClosingListener());
		setBounds(100, 100, 335, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtrPregunta = new JTextArea();
		txtrPregunta.setLineWrap(true);
		txtrPregunta.setEditable(false);
		txtrPregunta.setText("Pregunta");
		txtrPregunta.setBounds(43, 79, 250, 116);
		contentPane.add(txtrPregunta);
		
		respuesta1Button = new JButton("Respuesta 1");
		respuesta1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickedButton = respuesta1Button;
				AnswerQuestionPackage answer = new AnswerQuestionPackage(respuesta1Button.getText());

				questionAnswered = true;
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta1Button.setBounds(86, 237, 175, 23);
		contentPane.add(respuesta1Button);
		
		respuesta2Button = new JButton("Respuesta 2");
		respuesta2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickedButton = respuesta2Button;
				AnswerQuestionPackage answer = new AnswerQuestionPackage(respuesta2Button.getText());

				questionAnswered = true;
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta2Button.setBounds(86, 271, 175, 23);
		contentPane.add(respuesta2Button);
		
		respuesta3Button = new JButton("Respuesta 3");
		respuesta3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickedButton = respuesta3Button;
				AnswerQuestionPackage answer = new AnswerQuestionPackage(respuesta3Button.getText());

				questionAnswered = true;
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta3Button.setBounds(86, 305, 175, 23);
		contentPane.add(respuesta3Button);
		
		respuesta4Button = new JButton("Respuesta 4");
		respuesta4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickedButton = respuesta4Button;
				AnswerQuestionPackage answer = new AnswerQuestionPackage(respuesta4Button.getText());

				questionAnswered = true;
				respuesta1Button.setEnabled(false);
				respuesta2Button.setEnabled(false);
				respuesta3Button.setEnabled(false);
				respuesta4Button.setEnabled(false);
				Connection.sendPackage(answer);
			}
		});
		respuesta4Button.setBounds(86, 339, 175, 23);
		contentPane.add(respuesta4Button);
		
		lblRespuesta = new JLabel("Respuesta correcta/incorrecta ");
		lblRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRespuesta.setBounds(72, 394, 198, 26);
		contentPane.add(lblRespuesta);
		
		timerLabel = new JLabel("30");
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		timerLabel.setBounds(210, 431, 31, 53);
		contentPane.add(timerLabel);
		
		lblRonda = new JLabel("Ronda N");
		lblRonda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		lblRonda.setBounds(102, 26, 125, 26);
		contentPane.add(lblRonda);
		
		lblTiempoRestante = new JLabel("Tiempo restante:");
		lblTiempoRestante.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTiempoRestante.setBounds(43, 431, 159, 53);
		contentPane.add(lblTiempoRestante);
		lblRespuesta.setVisible(false);
		

		

	}
	
	public void setQuestion (Question question){
		txtrPregunta.setText(question.getQuestion());
		ArrayList<String> asnwers = new ArrayList<String>();
		asnwers.add(question.getCorrectAnswer());
		asnwers.add(question.getWrongAnswers().get(0));
		asnwers.add(question.getWrongAnswers().get(1));
		asnwers.add(question.getWrongAnswers().get(2));
		Collections.shuffle(asnwers);
		respuesta1Button.setText(asnwers.get(0));
		respuesta2Button.setText(asnwers.get(1));
		respuesta3Button.setText(asnwers.get(2));
		respuesta4Button.setText(asnwers.get(3));
		if(respuesta1Button.getText().equals(question.getCorrectAnswer()))
			correctAnswerButton = respuesta1Button;
		else
			if(respuesta2Button.getText().equals(question.getCorrectAnswer()))
				correctAnswerButton = respuesta2Button;
			else
				if(respuesta3Button.getText().equals(question.getCorrectAnswer()))
					correctAnswerButton = respuesta3Button;
				else
					correctAnswerButton = respuesta4Button;
		
	}

	public void enableButtonsAndRefreshComponents() {
		questionAnswered = false;
		respuesta1Button.setBackground(null);
		respuesta2Button.setBackground(null);
		respuesta3Button.setBackground(null);
		respuesta4Button.setBackground(null);
		respuesta1Button.setEnabled(true);
		respuesta2Button.setEnabled(true);
		respuesta3Button.setEnabled(true);
		respuesta4Button.setEnabled(true);
		roundNumber++;
		lblRonda.setText("Ronda " + roundNumber);
		lblRespuesta.setVisible(false);
		timerLabel.setText("30");
	}
	
	public void setLabelAnswer (boolean isCorrect){
		if(isCorrect)
			lblRespuesta.setText("Respuesta Correcta!");
		else
			lblRespuesta.setText("Respuesta Incorrecta!");
		
		lblRespuesta.setVisible(true);
			
	}
	
	public void paintButtons(boolean isCorrect){
		if(isCorrect)
			clickedButton.setBackground(java.awt.Color.green);
		else{
			clickedButton.setBackground(java.awt.Color.red);
			correctAnswerButton.setBackground(java.awt.Color.green);
		}
	}
	
	public void startTimer (){
		TimerThread timer = new TimerThread();
		timer.start();
	}
	
}
