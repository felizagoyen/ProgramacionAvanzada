package jerarquias;

public class Division extends OperadorBinario {

	public Division(Expresion expresion1, Expresion expresion2) {
		super(expresion1, expresion2);
	}
	
	public Double resolver() {
		return super.getExpresion(1).resolver() / super.getExpresion(0).resolver();
	}
		
}