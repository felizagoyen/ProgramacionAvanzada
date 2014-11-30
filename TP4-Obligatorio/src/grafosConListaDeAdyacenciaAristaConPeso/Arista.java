package grafosConListaDeAdyacenciaAristaConPeso;

public class Arista implements Comparable<Arista> { 
	private final int origen;
	private final int destino;
	private final double peso;

	    
	public Arista(int origen, int destino, double peso) {
		if (origen < 0) throw new IndexOutOfBoundsException("El nombre del nodo debe ser un entero no negativo");
	    if (destino < 0) throw new IndexOutOfBoundsException("El nombre del nodo debe ser un entero no negativo");
	    if (Double.isNaN(peso)) throw new IllegalArgumentException("El peso es NaN");
	    this.origen = origen;
	    this.destino = destino;
	    this.peso = peso;
	}

	public double getPeso() {
	    return peso;
	}

    public int getOrigen() {
        return origen;
    }
    
    public int getDestino() {
        return destino;
    }
    
    public int elOtroExtremo(int nodo) {
        if(nodo == origen) 
        	return destino;
        else if (nodo == destino) 
        	return origen;
        else throw new IllegalArgumentException("Illegal endpoint");
	}

	public int compareTo(Arista that) {
		if(this.getPeso() < that.getPeso()) 
			return -1;
	    else if (this.getPeso() > that.getPeso()) 
	    	return +1;
	    else
	    	return  0;
	}
    
	
	
	public String toString() {
		return String.format("%d-%d %.5f", origen, destino, peso);
	}
 
}
