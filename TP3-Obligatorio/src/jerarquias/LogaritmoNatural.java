package jerarquias;

public class LogaritmoNatural extends OperadorUnario {

	public LogaritmoNatural(Expresion expresion) {
		super(expresion);
	}

	public Double resolver() {
		return Math.log(super.getExpresiones().resolver());
	}
	
}
