package ClientePreguntados;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import Commons.Question;

public class ChooseQuestionsScreen extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 2812926432775478079L;
	private JPanel contentPane;

	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChooseQuestionsScreen frame = new ChooseQuestionsScreen();
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
	public ChooseQuestionsScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new ClosingListener());
		setBounds(100, 100, 548, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pregunta 1");
		lblNewLabel.setBounds(24, 39, 120, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPregunta = new JLabel("Pregunta 2");
		lblPregunta.setBounds(24, 64, 120, 14);
		contentPane.add(lblPregunta);
		
		JLabel lblPregunta_1 = new JLabel("Pregunta 3");
		lblPregunta_1.setBounds(24, 89, 120, 14);
		contentPane.add(lblPregunta_1);
		
		JLabel lblPregunta_2 = new JLabel("Pregunta 4");
		lblPregunta_2.setBounds(24, 114, 120, 14);
		contentPane.add(lblPregunta_2);
		
		JLabel lblPregunta_3 = new JLabel("Pregunta 5");
		lblPregunta_3.setBounds(24, 139, 120, 14);
		contentPane.add(lblPregunta_3);
		
		JLabel lblPregunta_4 = new JLabel("Pregunta 6");
		lblPregunta_4.setBounds(24, 164, 120, 14);
		contentPane.add(lblPregunta_4);
		
		JLabel lblPregunta_5 = new JLabel("Pregunta 7");
		lblPregunta_5.setBounds(24, 189, 120, 14);
		contentPane.add(lblPregunta_5);
		
		JLabel lblPregunta_6 = new JLabel("Pregunta 8");
		lblPregunta_6.setBounds(24, 214, 120, 14);
		contentPane.add(lblPregunta_6);
		
		JLabel lblPregunta_7 = new JLabel("Pregunta 9");
		lblPregunta_7.setBounds(24, 239, 120, 14);
		contentPane.add(lblPregunta_7);
		
		JLabel lblPregunta_8 = new JLabel("Pregunta 10");
		lblPregunta_8.setBounds(24, 264, 120, 14);
		contentPane.add(lblPregunta_8);
		
		final JComboBox<Question> combo1 = new JComboBox<Question>();
		combo1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});


		combo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo1.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo1);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo1.addItem(new Question("Al azar"));
		combo1.addItem(new Question("Seleccionar pregunta..."));
		combo1.setBounds(141, 36, 338, 20);
		contentPane.add(combo1);
		
		final JComboBox<Question> combo2 = new JComboBox<Question>();
		combo2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo2.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo2);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo2.addItem(new Question("Al azar"));
		combo2.addItem(new Question("Seleccionar pregunta..."));
		combo2.setBounds(141, 64, 338, 20);
		contentPane.add(combo2);
		
		final JComboBox<Question> combo3 = new JComboBox<Question>();
		combo3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo3.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo3);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo3.addItem(new Question("Al azar"));
		combo3.addItem(new Question("Seleccionar pregunta..."));
		combo3.setBounds(141, 86, 338, 20);
		contentPane.add(combo3);
		
		final JComboBox<Question> combo4 = new JComboBox<Question>();
		combo4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo4.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo4);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo4.addItem(new Question("Al azar"));
		combo4.addItem(new Question("Seleccionar pregunta..."));
		combo4.setBounds(141, 111, 338, 20);
		contentPane.add(combo4);
		
		final JComboBox<Question> combo5 = new JComboBox<Question>();
		combo5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo5.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo5);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo5.addItem(new Question("Al azar"));
		combo5.addItem(new Question("Seleccionar pregunta..."));
		combo5.setBounds(141, 136, 338, 20);
		contentPane.add(combo5);
		
		final JComboBox<Question> combo6 = new JComboBox<Question>();
		combo6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo6.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo6);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo6.addItem(new Question("Al azar"));
		combo6.addItem(new Question("Seleccionar pregunta..."));
		combo6.setBounds(141, 161, 338, 20);
		contentPane.add(combo6);
		
		final JComboBox<Question> combo7 = new JComboBox<Question>();
		combo7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo7.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo7);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo7.addItem(new Question("Al azar"));
		combo7.addItem(new Question("Seleccionar pregunta..."));
		combo7.setBounds(141, 186, 338, 20);
		contentPane.add(combo7);
		
		final JComboBox<Question> combo8 = new JComboBox<Question>();
		combo8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo8.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo8);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo8.addItem(new Question("Al azar"));
		combo8.addItem(new Question("Seleccionar pregunta..."));
		combo8.setBounds(141, 211, 338, 20);
		contentPane.add(combo8);
		
		final JComboBox<Question> combo9 = new JComboBox<Question>();
		combo9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo9.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo9);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo9.addItem(new Question("Al azar"));
		combo9.addItem(new Question("Seleccionar pregunta..."));
		combo9.setBounds(141, 236, 338, 20);
		contentPane.add(combo9);
		
		final JComboBox<Question> combo10 = new JComboBox<Question>();
		combo10.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setEnabled(true);
			}
		});
		combo10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo10.getSelectedItem().toString().equals("Seleccionar pregunta..."))
				{
					ChooseQuestionWindow choosequestionwindow = new ChooseQuestionWindow(combo10);
					ClientThread.recieveScreen(choosequestionwindow);
					setEnabled(false);
					choosequestionwindow.setVisible(true);
				}
			}
		});
		combo10.addItem(new Question("Al azar"));
		combo10.addItem(new Question("Seleccionar pregunta..."));
		combo10.setBounds(141, 261, 338, 20);
		contentPane.add(combo10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> questionsID = new ArrayList<Integer> ();
				questionsID.add(((Question)combo1.getSelectedItem()).getID());
				questionsID.add(((Question)combo2.getSelectedItem()).getID());
				questionsID.add(((Question)combo3.getSelectedItem()).getID());
				questionsID.add(((Question)combo4.getSelectedItem()).getID());
				questionsID.add(((Question)combo5.getSelectedItem()).getID());
				questionsID.add(((Question)combo6.getSelectedItem()).getID());
				questionsID.add(((Question)combo7.getSelectedItem()).getID());
				questionsID.add(((Question)combo8.getSelectedItem()).getID());
				questionsID.add(((Question)combo9.getSelectedItem()).getID());
				questionsID.add(((Question)combo10.getSelectedItem()).getID());
				CreateGameScreen creategamescreen = new CreateGameScreen();
				creategamescreen.receiveQuestionsID(questionsID);
				creategamescreen.setVisible(true);
				setVisible(false);
			}
		});
		btnAceptar.setBounds(390, 302, 89, 23);
		contentPane.add(btnAceptar);
	}
}
