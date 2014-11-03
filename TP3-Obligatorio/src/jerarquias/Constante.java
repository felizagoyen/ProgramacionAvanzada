package jerarquias;

public class Constante extends Expresion  {

	private Double valor;
	
	public Constante(Double valor) {
		this.valor = valor;
	}
	
	public Double resolver() {
		return valor;
	}
}
