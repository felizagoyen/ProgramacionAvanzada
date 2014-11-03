package jerarquias;

public class Resta extends OperadorBinario {

	public Resta(Expresion expresion1, Expresion expresion2) {
		super(expresion1, expresion2);
	}
	
	public Double resolver() {
		return super.getExpresion(1).resolver() - super.getExpresion(0).resolver();
	}
		
}