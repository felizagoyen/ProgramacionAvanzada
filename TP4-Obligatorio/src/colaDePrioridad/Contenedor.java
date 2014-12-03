package colaDePrioridad;

public class Contenedor<E> implements Comparable<Contenedor>{
	private E dato;
    private Contenedor<E> siguiente;
    public Double minDistancia = Double.POSITIVE_INFINITY;
    
    public Contenedor(){}
    
    public Contenedor(E dato, Double minDistancia) {
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
	public Contenedor<E> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Contenedor<E> siguiente) {
		this.siguiente = siguiente;
	}
        
	public int compareTo(Contenedor nodo){				
		if(minDistancia > nodo.minDistancia) return 1;
		if(minDistancia < nodo.minDistancia) return -1;
		return 0;
	}
	
	public String toString(){
		return dato + " " + minDistancia;
	}
}
