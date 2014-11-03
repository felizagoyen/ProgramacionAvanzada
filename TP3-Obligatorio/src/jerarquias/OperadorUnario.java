package jerarquias;

import java.util.ArrayList;

abstract class OperadorUnario extends Expresion {
	
	private ArrayList<Expresion> expresiones = new ArrayList<Expresion>();
	
	public OperadorUnario(Expresion expresion) {
		expresiones.add(expresion);
	}

	public Expresion getExpresiones() {
		return expresiones.get(0);
	}

}
