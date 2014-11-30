package grafosConListaDeAdyacencia;

public class Nodo<E> {
	private E dato;
    private Nodo<E> siguiente;
    public Double minDistancia = Double.POSITIVE_INFINITY;
    
	public Double getMinDistancia() {
		return minDistancia;
	}
	public void setMinDistancia(Double minDistancia) {
		this.minDistancia = minDistancia;
	}
	public E getDato() {
		return dato;
	}
	public void setDato(E dato) {
		this.dato = dato;
	}
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
        
    
}
