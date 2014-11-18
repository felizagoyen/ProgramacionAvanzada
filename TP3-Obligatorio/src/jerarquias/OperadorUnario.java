package jerarquias;

abstract class OperadorUnario extends Expresion {
	
	private Expresion expresion;
	
	public OperadorUnario(Expresion expresion) {
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}

}
