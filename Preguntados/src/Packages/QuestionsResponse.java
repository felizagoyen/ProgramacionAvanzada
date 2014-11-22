package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsResponse implements Serializable, Package {
	
	

	private static final long serialVersionUID = 6845559714710805684L;
	private ArrayList<Question> questions;
	private static final Integer PACKAGEID = 11;
	
	public QuestionsResponse (){
		this.questions = new ArrayList<Question> ();
		

		questions.add(new Question(1,"Qué equipo pechea siempre al final del torneo?"));
		questions.add(new Question(2,"Quién descubrió el continente de zona sur?"));
		questions.add(new Question(3,"Quien no va a dejar nunca la falopa"));
		questions.add(new Question(4,"La anterior pregunta tiene como respuesta al diego?"));


		
	}
	
	
	@Override
	public Integer getPackageID() {

		return PACKAGEID;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	

}
