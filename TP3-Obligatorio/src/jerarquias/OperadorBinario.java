package jerarquias;

import java.util.ArrayList;

abstract class OperadorBinario extends Expresion{
	
	private ArrayList<Expresion> expresiones = new ArrayList<Expresion>();
	
	public OperadorBinario(Expresion expresion1, Expresion expresion2){
		expresiones.add(expresion1);
		expresiones.add(expresion2);
	}
	
	public Expresion getExpresion(int indice) {
		return expresiones.get(indice);
	}
	
}
