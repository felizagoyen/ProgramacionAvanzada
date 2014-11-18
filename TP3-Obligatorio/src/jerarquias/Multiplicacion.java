package jerarquias;

public class Multiplicacion extends OperadorBinario {

	public Multiplicacion(Expresion expresion1, Expresion expresion2) {
		super(expresion1, expresion2);
	}
	
	public Double resolver() {
		return super.getExpresion1().resolver() * super.getExpresion2().resolver();
	}
		
}