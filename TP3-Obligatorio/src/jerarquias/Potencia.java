package jerarquias;

public class Potencia extends OperadorBinario {

	public Potencia(Expresion expresion1, Expresion expresion2) {
		super(expresion1, expresion2);
	}
	
	public Double resolver() {
		return Math.pow(super.getExpresion(1).resolver(), super.getExpresion(0).resolver());
	}
		
}