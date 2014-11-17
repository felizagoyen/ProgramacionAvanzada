package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsResponse implements Serializable, Package {
	
	

	private static final long serialVersionUID = 6845559714710805684L;
	private ArrayList<StringQuestion> questions;
	private static final Integer PACKAGEID = 11;
	
	public QuestionsResponse (){
		this.questions = new ArrayList<StringQuestion> ();
		
		questions.add(new StringQuestion("Qué equipo pechea siempre al final del torneo?", 1));
		questions.add(new StringQuestion("Quién descubrió el continente de zona sur?", 2));
		questions.add(new StringQuestion("Quien no va a dejar nunca la falopa", 3));
		questions.add(new StringQuestion("La anterior pregunta tiene como respuesta al diego?", 4));
		
		
	}
	
	
	@Override
	public Integer getPackageID() {

		return PACKAGEID;
	}
	
	public ArrayList<StringQuestion> getQuestions() {
		return questions;
	}
	

}
