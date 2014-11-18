package jerarquias;

abstract class OperadorBinario extends Expresion{
	
	private Expresion expresion1;
	private Expresion expresion2;
	
	public OperadorBinario(Expresion expresion1, Expresion expresion2){
		this.expresion1 = expresion1;
		this.expresion2 = expresion2;
	}
	
	public Expresion getExpresion1() {
		return expresion1;
	}
	
	public Expresion getExpresion2(){
		return expresion2;
	}
	
}
