package grafoConMatrizDeAdyacencia;

import grafosConListaDeAdyacenciaAristaConPeso.Arista;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import colaDePrioridad.ColaDePrioridades;
import matriz.MatrizDeAdyacencia;

public class Grafo {
	private Integer numeroDeNodos;
	private Integer[] nodos;
	private MatrizDeAdyacencia matrizAdyacencia;
	
	public Grafo(String nombre) {
		LeerArchivo archivoIn = new LeerArchivo(nombre);
		String [] linea;
		try {
			numeroDeNodos = Integer.parseInt(archivoIn.readLine());
			nodos = new Integer[numeroDeNodos];
			matrizAdyacencia = new MatrizDeAdyacencia(numeroDeNodos);
			for(int i=0; i<numeroDeNodos; i++){
				linea = archivoIn.readLine().split(" ");
				Integer nombreDeNodo = Integer.parseInt(linea[0]);
				Integer numeroAristas = Integer.parseInt(linea[1]);
				nodos[i]=nombreDeNodo;
				for(int j=0; j<numeroAristas; j++){
					linea = archivoIn.readLine().split(" ");
					Integer arista = Integer.parseInt(linea[0]) - 1;
					Integer peso = Integer.parseInt(linea[1]);
					matrizAdyacencia.getMatriz()[i][arista]=peso;
				}
				
			}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarAristas(){
		for(int i=0; i<numeroDeNodos; i++){
			System.out.print("nodo " + nodos[i] + ": ");
			for(int j=0; j<numeroDeNodos; j++){
				if(matrizAdyacencia.getMatriz()[i][j]>0)
				System.out.print((j+1) + " ");
			}
			System.out.println();
		}
	}

	public ArrayList<Integer> dijkstra(Integer origen){
		ColaDePrioridades<Double> cola = new ColaDePrioridades<Double>(numeroDeNodos);
		ArrayList<Integer> caminoMasCorto = new ArrayList<Integer>(); 
		Double [] minDistancia = new Double[numeroDeNodos];
		for(int i=0; i<numeroDeNodos; i++)
			minDistancia[i]=Double.POSITIVE_INFINITY;
			
		minDistancia[origen]=0.0;
	    cola.insert(origen, 0.0);
		
	    while( !cola.isEmpty() ){   
	    	Integer actual = cola.delMin();
	    	System.out.print(actual+1 + " ");
	    	caminoMasCorto.add(actual);
	    	for(int j=0; j<numeroDeNodos; j++){
	    		if(matrizAdyacencia.getMatriz()[actual][j]>=0){
	    			if(minDistancia[j] > minDistancia[actual] + matrizAdyacencia.getMatriz()[actual][j]){
	    				minDistancia[j] = minDistancia[actual] + matrizAdyacencia.getMatriz()[actual][j];
	    				if(cola.contains(j)){
	    					cola.change(j, minDistancia[j]);
	    				}
	    				else
	    					cola.insert(j, minDistancia[j]);
	    			}
	    		}
	    	}
	    }
	    return caminoMasCorto;
	}
	
	
	public void busquedaEnProfundidad(Integer nodo) { //dfs
		Stack<Integer> pila = new Stack<Integer>();
		boolean [] fueVisitado = new boolean[numeroDeNodos]; 
		fueVisitado[nodo] = true; 
		System.out.print((nodo+1) + " ");
		pila.push(nodo); 
		while(!pila.isEmpty()) {
			int verticeAdyacente =-1;
			for(int j=0; j<numeroDeNodos; j++)
				if(matrizAdyacencia.getMatriz()[pila.peek()][j]>0 && fueVisitado[j]==false){
					verticeAdyacente=j;
					break;
				}
			if(verticeAdyacente == -1) //no tiene 
				pila.pop(); 
			else {
				fueVisitado[verticeAdyacente] = true; 
				System.out.print((verticeAdyacente+1) + " ");
				pila.push(verticeAdyacente); 
			}
		} 
	} 
	
	public void busquedaEnAnchura(Integer nodo){ // bfs
		Queue<Integer> cola = new LinkedList<Integer>();
		boolean [] fueVisitado = new boolean[numeroDeNodos]; 
		fueVisitado[nodo] = true; 
		System.out.print((nodo+1) + " ");
		cola.add(nodo); 
		int v2;
		while( !cola.isEmpty() ){
			int verticeAdyacente =-1;
			for(int j=0; j<numeroDeNodos; j++)
				if(matrizAdyacencia.getMatriz()[cola.peek()][j]>0 && fueVisitado[j]==false){
					verticeAdyacente=j;
					break;
				}
			if(verticeAdyacente == -1) //no tiene 
				cola.remove(); 
			else {
				fueVisitado[verticeAdyacente] = true; 
				System.out.print((verticeAdyacente+1) + " ");
				cola.add(verticeAdyacente); 
			}
		} 
	}
	
	public String toString(){
		return matrizAdyacencia.toString();
	}
	
	public static void main(String[] args) {
		Grafo grafo = new Grafo("grafo.in");
		grafo.mostrarAristas();
		grafo.busquedaEnAnchura(0);
		System.out.println();
		grafo.busquedaEnProfundidad(0);
		System.out.println();
		grafo.dijkstra(0);
	}
	
	
}
