package Packages;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsResponse implements Serializable, Package {
	
	

	private static final long serialVersionUID = 6845559714710805684L;
	private ArrayList<Question> questions;
	private static final Integer PACKAGEID = 11;
	
	public QuestionsResponse (){
		this.questions = new ArrayList<Question> ();
		
<<<<<<< HEAD
		questions.add(new Question("Qué equipo pechea siempre al final del torneo?"));
		questions.add(new Question("Quién descubrió el continente de zona sur?"));
		questions.add(new Question("Quien no va a dejar nunca la falopa"));
		questions.add(new Question("La anterior pregunta tiene como respuesta al diego?"));
=======
		questions.add(new StringQuestion("Quï¿½ equipo pechea siempre al final del torneo?", 1));
		questions.add(new StringQuestion("Quiï¿½n descubriï¿½ el continente de zona sur?", 2));
		questions.add(new StringQuestion("Quien no va a dejar nunca la falopa", 3));
		questions.add(new StringQuestion("La anterior pregunta tiene como respuesta al diego?", 4));
>>>>>>> origin/master
		
	}
	
	
	@Override
	public Integer getPackageID() {

		return PACKAGEID;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	

}
