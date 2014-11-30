package grafosConListaDeAdyacenciaAristaConPeso;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaAdyacencia<E> implements Iterable<E> {
	private int cantidadDeElementos;               
    private Nodo<E> primero;    

    public ListaAdyacencia() {
        primero = null;
        cantidadDeElementos = 0;
    }

    public boolean isEmpty() {
        return primero == null;
    }

    public int size() {
        return cantidadDeElementos;
    }
	
    public E getNombreNodo(){
    	return primero.getDato();
    }
    
    public void setNombreNodo(E dato) {
		this.primero.setDato(dato);
	}
    
    public void setMinDistancia(Double minDistancia) {
		this.primero.minDistancia=minDistancia;
	}
    
    public Double getMinDistancia() {
		return this.primero.minDistancia;
	}
    
    public Nodo<E> getNodo(){
    	return primero;
    }

	public void add(E elemento) {
        Nodo<E> anteriorPrimero = primero;
        primero = new Nodo<E>();
        primero.setDato(elemento);
        primero.setSiguiente(anteriorPrimero);
        cantidadDeElementos++;
    }

    public Iterator<E> iterator()  {
        return new ListIterator<E>(primero);  
    }

    
    private class ListIterator<E> implements Iterator<E> {
        private Nodo<E> actual;

        public ListIterator(Nodo<E> primero) {
            actual = primero;
        }

        public boolean hasNext() {
        	return actual != null;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E elemento = actual.getDato();
            actual = actual.getSiguiente(); 
            return elemento;
        }
    }

}
