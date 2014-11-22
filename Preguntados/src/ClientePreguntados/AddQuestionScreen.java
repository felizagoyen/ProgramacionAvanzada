package ClientePreguntados;


import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Packages.Question;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddQuestionScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4009979497918877231L;
	private JPanel contentPane;
	private JTextArea jPreguntaTextArea;
	private JTextField jRespuestaCorrectaTextField;
	private JTextField jRespuestaInc1tf;
	private JTextField jRespuestaInc2tf;
	private JTextField jRespuestaInc3tf;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddQuestionScreen frame = new AddQuestionScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddQuestionScreen() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		setTitle("Preguntados");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPregunta = new JLabel("Pregunta:");
		lblPregunta.setBounds(21, 36, 71, 14);
		contentPane.add(lblPregunta);
		
		jPreguntaTextArea = new JTextArea();
		jPreguntaTextArea.setBounds(33, 61, 233, 69);
		contentPane.add(jPreguntaTextArea);
		
		JLabel lblRespuestaCorrecta = new JLabel("Respuesta Correcta");
		lblRespuestaCorrecta.setBounds(23, 145, 116, 14);
		contentPane.add(lblRespuestaCorrecta);
		
		jRespuestaCorrectaTextField = new JTextField();
		jRespuestaCorrectaTextField.setBounds(23, 170, 243, 20);
		contentPane.add(jRespuestaCorrectaTextField);
		jRespuestaCorrectaTextField.setColumns(10);
		
		JLabel lblRespuestaInorrecta = new JLabel("Respuesta Incorrecta 1");
		lblRespuestaInorrecta.setBounds(23, 201, 116, 14);
		contentPane.add(lblRespuestaInorrecta);
		
		jRespuestaInc1tf = new JTextField();
		jRespuestaInc1tf.setColumns(10);
		jRespuestaInc1tf.setBounds(23, 226, 243, 20);
		contentPane.add(jRespuestaInc1tf);
		
		JLabel lblRespuestaIncorrecta = new JLabel("Respuesta Incorrecta 2");
		lblRespuestaIncorrecta.setBounds(23, 257, 116, 14);
		contentPane.add(lblRespuestaIncorrecta);
		
		jRespuestaInc2tf = new JTextField();
		jRespuestaInc2tf.setColumns(10);
		jRespuestaInc2tf.setBounds(23, 282, 243, 20);
		contentPane.add(jRespuestaInc2tf);
		
		JLabel lblRespuestaIncorrecta_1 = new JLabel("Respuesta Incorrecta 3");
		lblRespuestaIncorrecta_1.setBounds(23, 313, 116, 14);
		contentPane.add(lblRespuestaIncorrecta_1);
		
		jRespuestaInc3tf = new JTextField();
		jRespuestaInc3tf.setColumns(10);
		jRespuestaInc3tf.setBounds(23, 338, 243, 20);
		contentPane.add(jRespuestaInc3tf);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(23, 369, 57, 14);
		contentPane.add(lblCategora);
		
		final JComboBox<String> jCategoriaComboBox = new JComboBox<String>();
		jCategoriaComboBox.setBounds(90, 369, 176, 20);
		contentPane.add(jCategoriaComboBox);
		jCategoriaComboBox.addItem("Deportes");
		jCategoriaComboBox.addItem("Entretenimiento");
		jCategoriaComboBox.addItem("Geograf�a");
		jCategoriaComboBox.addItem("Historia");
		jCategoriaComboBox.addItem("Arte");
		jCategoriaComboBox.addItem("Ciencia");
		
		
		
		JButton jAgregarPreguntaButton = new JButton("Agregar Pregunta");
		jAgregarPreguntaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <String> incorrectas = new ArrayList <String> ();
				incorrectas.add(jRespuestaInc1tf.getText());
				incorrectas.add(jRespuestaInc2tf.getText());
				incorrectas.add(jRespuestaInc3tf.getText());
				String categoria = new String ();
				categoria = (String)jCategoriaComboBox.getSelectedItem();
				Question question = new Question (null, jPreguntaTextArea.getText(), categoria, jRespuestaCorrectaTextField.getText(), incorrectas);
				Connection.sendPackage(question);
			}
		});
		jAgregarPreguntaButton.setBounds(23, 416, 243, 23);
		contentPane.add(jAgregarPreguntaButton);
		
		JButton jAtrasButton = new JButton("Atr\u00E1s");
		jAtrasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminMenuScreen ams = new AdminMenuScreen();
				ams.setVisible(true);
				setVisible(false);
				
			}
		});
		jAtrasButton.setBounds(177, 11, 89, 23);
		contentPane.add(jAtrasButton);
	}
	
	public void clearScreen(){
		jPreguntaTextArea.setText("");
		jRespuestaCorrectaTextField.setText("");
		jRespuestaInc1tf.setText("");
		jRespuestaInc2tf.setText("");
		jRespuestaInc3tf.setText("");
	}
}
