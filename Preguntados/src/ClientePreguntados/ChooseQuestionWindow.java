package ClientePreguntados;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Packages.QuestionsRequest;
import Packages.QuestionsResponse;
import Packages.StringQuestion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseQuestionWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2375532637553844038L;
	private final JPanel contentPanel = new JPanel();
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ChooseQuestionWindow dialog = new ChooseQuestionWindow();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ChooseQuestionWindow(final JComboBox combo, final ClientePreguntados cliente) {
		setBounds(100, 100, 571, 411);
		setTitle("Preguntados");
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 545, 339);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 340, 545, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			final JList<String> questionList = new JList<String>();
			questionList.setBackground(Color.ORANGE);
			questionList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			questionList.setModel(new AbstractListModel() {

				String[] values = new String[] {};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			questionList.setBounds(41, 0, 407, 265);
			contentPanel.add(questionList);
			JScrollPane barraDesplazamiento = new JScrollPane(questionList);
			barraDesplazamiento.setBounds(37, 56, 465, 261);
			contentPanel.add(barraDesplazamiento);
			final DefaultListModel<String> modelo = new DefaultListModel<String>();
			
			final JComboBox<String> categoriaComboBox = new JComboBox<String>();
			categoriaComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelo.removeAllElements();
					switch(categoriaComboBox.getSelectedItem().toString()){  //Segun que categoria sea, pedir a servidor todas 
																			 //las preguntas que esten en base de datos.
					case "Deportes":
						modelo.addElement("�Cuantas copas libertadores tiene Boca Juniors?");
						modelo.addElement("�En que a�o descendi� River Plate a la Segunda Division del futbol argentino?");
						modelo.addElement("�Quien err� el unico penal en la serie de penales en la semifinal de la Copa Libertadores 2004 entre Boca y River?");
						cliente.enviarPaquete(new QuestionsRequest(categoriaComboBox.getSelectedItem().toString()));
						QuestionsResponse qResponse = (QuestionsResponse) cliente.recibirPaquete();
						for(StringQuestion cadauno : qResponse.getQuestions()){
							modelo.addElement(cadauno.getQuestion());							
						}
						questionList.setModel(modelo);				
						break;
					case "Entretenimiento":
						modelo.addElement("�En que a�o se estren� 'Volver al Futuro?'");
						questionList.setModel(modelo);				
						break;
					case "Ciencia":
						modelo.addElement("�Cu�l es el quinto planeta m�s cercano al Sol?");
						questionList.setModel(modelo);				
						break;
					case "Historia":
						modelo.addElement("�En que a�o asumi� Raul Alfons�n como presidente?");
						questionList.setModel(modelo);				
						break;
					case "Arte":
						modelo.addElement("�En que a�o se termin� de construir la Capilla Sixtina?");
						questionList.setModel(modelo);				
						break;
					case "Geograf�a":
						modelo.addElement("�De d�nde son originarios los Moai?");
						questionList.setModel(modelo);				
						break;
						
					}
					
					
					
				}
			});
			categoriaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Categor\u00EDa", "Deportes", "Ciencia", "Entretenimiento", "Geograf\u00EDa", "Historia", "Arte"}));
			categoriaComboBox.setBounds(159, 25, 195, 20);
			contentPanel.add(categoriaComboBox);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						combo.addItem(questionList.getSelectedValue());
						combo.setSelectedItem(questionList.getSelectedValue());
						setVisible(false);
						
					}


				});
				okButton.setBounds(322, 5, 91, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBounds(423, 5, 112, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
}
