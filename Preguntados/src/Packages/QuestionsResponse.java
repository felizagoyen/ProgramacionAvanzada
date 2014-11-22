package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsResponse implements Serializable, Package {
	
	

	private static final long serialVersionUID = 6845559714710805684L;
	private ArrayList<Question> questions;
	private static final Integer PACKAGEID = 11;
	
	public QuestionsResponse (){
		this.questions = new ArrayList<Question> ();
		
		questions.add(new Question("Qu� equipo pechea siempre al final del torneo?"));
		questions.add(new Question("Qui�n descubri� el continente de zona sur?"));
		questions.add(new Question("Quien no va a dejar nunca la falopa"));
		questions.add(new Question("La anterior pregunta tiene como respuesta al diego?"));
		
		
	}
	
	
	@Override
	public Integer getPackageID() {

		return PACKAGEID;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	

}
