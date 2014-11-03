package jerarquias;

public class Suma extends OperadorBinario {

	public Suma(Expresion expresion1, Expresion expresion2) {
		super(expresion1, expresion2);
	}
	
	public Double resolver() {
		return super.getExpresion(0).resolver() + super.getExpresion(1).resolver();
	}
		
}
