package colaDePrioridad;

public class Nodo<E> implements Comparable<Nodo>{
	private E dato;
    private Nodo<E> siguiente;
    public Double minDistancia = Double.POSITIVE_INFINITY;
    
    public Nodo(){}
    
    public Nodo(E dato, Double minDistancia) {
		this.dato=dato;
		this.minDistancia=minDistancia;
	}
    
	public Double getMinDistance() {
		return minDistancia;
	}
	public void setMinDistance(Double minDistancia) {
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
        
	public int compareTo(Nodo nodo){				
		if(minDistancia > nodo.minDistancia) return 1;
		if(minDistancia < nodo.minDistancia) return -1;
		return 0;
	}
	
	public String toString(){
		return dato + " " + minDistancia;
	}
}
