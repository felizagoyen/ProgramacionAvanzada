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

import Packages.Question;
import Packages.QuestionsRequest;
import Packages.QuestionsResponse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ChooseQuestionWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2375532637553844038L;
	private final JPanel contentPanel = new JPanel();
	final  DefaultListModel<Question> model = new DefaultListModel<Question>();
	final  JList<Question> questionList = new JList<Question>();
	private CustomListModel customModel = new CustomListModel();
	


 class CustomListModel extends AbstractListModel<Question>{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6483026176611994291L;
	private ArrayList<Question> lista = new ArrayList<Question>();
 
    @Override
    public int getSize() {
        return lista.size();
    }
 
    @Override
    public Question getElementAt(int index) {
        Question q = lista.get(index);
        return q;
    }
    
    public void addQuestion(Question q){
        lista.add(q);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
    public Question getQuestion(int index){
        return lista.get(index);
    }
}
	

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
	public ChooseQuestionWindow(final JComboBox<Question> combo) {
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
			
			final JComboBox<String> categoriaComboBox = new JComboBox<String>();
			categoriaComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.removeAllElements();
					switch(categoriaComboBox.getSelectedItem().toString()){  //Segun que categoria sea, pedir a servidor todas 
																			 //las preguntas que esten en base de datos.
					case "Deportes":
//						model.addElement("¿Cuantas copas libertadores tiene Boca Juniors?");
//						model.addElement("¿En que año descendió River Plate a la Segunda Division del futbol argentino?");
//						model.addElement("¿Quien erró el unico penal en la serie de penales en la semifinal de la Copa Libertadores 2004 entre Boca y River?");
						Connection.sendPackage(new QuestionsRequest(categoriaComboBox.getSelectedItem().toString()));			
						break;
					case "Entretenimiento":
//						model.addElement("¿En que año se estrenó 'Volver al Futuro?'");
						questionList.setModel(model);				
						break;
					case "Ciencia":
//						model.addElement("¿Cuál es el quinto planeta más cercano al Sol?");
						questionList.setModel(model);				
						break;
					case "Historia":
//						model.addElement("¿En que año asumió Raul Alfonsín como presidente?");
						questionList.setModel(model);				
						break;
					case "Arte":
//						model.addElement("¿En que año se terminó de construir la Capilla Sixtina?");
						questionList.setModel(model);				
						break;
					case "Geografía":
//						model.addElement("¿De dónde son originarios los Moai?");
						questionList.setModel(model);				
						break;
						
					}
					
					
					
				}
			});
			categoriaComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Categor\u00EDa", "Deportes", "Ciencia", "Entretenimiento", "Geograf\u00EDa", "Historia", "Arte"}));
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						combo.setSelectedIndex(0);
						setVisible(false);
					}
				});
				cancelButton.setBounds(423, 5, 112, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	


	public void showQuestions (QuestionsResponse qResponse){
		for(Question cadauno : qResponse.getQuestions()){
			customModel.addQuestion(cadauno);							
		}
		questionList.setModel(customModel);
	}
}
