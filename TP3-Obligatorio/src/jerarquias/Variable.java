package jerarquias;

public class Variable extends Expresion{

	private String variable;
	private Punto punto = Punto.getPunto();
	
	public Variable(String variable) {
		this.variable = variable; 
	}
	
	public Double resolver() {
		if(variable.toLowerCase().equals("y")) return punto.getY();
		if(variable.toLowerCase().equals("z")) return punto.getZ();
		return punto.getX();
	}
}
