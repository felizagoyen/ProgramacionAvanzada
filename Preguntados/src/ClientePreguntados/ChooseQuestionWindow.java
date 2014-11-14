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
	public ChooseQuestionWindow(final JComboBox combo) {
		setBounds(100, 100, 571, 411);
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
			questionList.setBounds(1, 1, 242, 133);
			contentPanel.add(questionList);
			JScrollPane barraDesplazamiento = new JScrollPane(questionList);
			barraDesplazamiento.setBounds(28, 61, 507, 267);
			contentPanel.add(barraDesplazamiento);
			final DefaultListModel<String> modelo = new DefaultListModel<String>();
			
			final JComboBox categoriaComboBox = new JComboBox();
			categoriaComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelo.removeAllElements();
					switch(categoriaComboBox.getSelectedItem().toString()){  //Segun que categoria sea, pedir a servidor todas 
																			 //las preguntas que esten en base de datos.
					case "Deportes":
						modelo.addElement("¿Cuantas copas libertadores tiene Boca Juniors?");
						modelo.addElement("¿En que año descendió River Plate a la Segunda Division del futbol argentino?");
						questionList.setModel(modelo);				
						break;
					case "Entretenimiento":
						modelo.addElement("¿En que año se estrenó 'Volver al Futuro?'");
						questionList.setModel(modelo);				
						break;
					case "Ciencia":
						modelo.addElement("¿Cuál es el quinto planeta más cercano al Sol?");
						questionList.setModel(modelo);				
						break;
					case "Historia":
						modelo.addElement("¿En que año asumió Raul Alfonsín como presidente?");
						questionList.setModel(modelo);				
						break;
					case "Arte":
						modelo.addElement("¿En que año se termino de construir la Capilla Sixtina?");
						questionList.setModel(modelo);				
						break;
					case "Geografía":
						modelo.addElement("¿De donde son originarios los Moai?");
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

//					private void sendQuestion(String selectedValue) {
//						// TODO Auto-generated method stub
//						
//					}
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
