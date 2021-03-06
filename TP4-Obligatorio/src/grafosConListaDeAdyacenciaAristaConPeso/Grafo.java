package grafosConListaDeAdyacenciaAristaConPeso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import colaDePrioridad.ColaDePrioridades;
import colaDePrioridad.Contenedor;
import fileIO.LeerArchivo;

public class Grafo {
	private Integer numeroDeNodos; 
	private Integer numeroDeAristas;
	private ListaAdyacencia<Arista> [] adj;
	
	public Grafo (String nombre){
		LeerArchivo archivoIn = new LeerArchivo(nombre);
		String [] linea;
		try {
			numeroDeNodos = Integer.parseInt(archivoIn.readLine());
			this.numeroDeNodos = numeroDeNodos; 
			this.numeroDeAristas = 0;
			adj = (ListaAdyacencia<Arista>[]) new ListaAdyacencia[numeroDeNodos]; 
			for(int i=0; i<numeroDeNodos; i++){ 
				adj[i] = new ListaAdyacencia<Arista>(); 
			}
			
			for(int i=0; i<numeroDeNodos; i++){
				linea = archivoIn.readLine().split(" ");
				Integer nombreDeNodo = Integer.parseInt(linea[0]);
				Integer numeroAristas = Integer.parseInt(linea[1]);
				
				for(int j=0; j<numeroAristas; j++){
					linea = archivoIn.readLine().split(" ");
					addArista(new Arista(i, Integer.parseInt(linea[0]) - 1, Integer.parseInt(linea[1])));
				}
				
			}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getNumeroDeNodos(){
		return numeroDeNodos;
	}
	
	public Integer getNumeroDeAristas() { 
		return numeroDeAristas; 
	}
	
	public void addArista(Arista arista){
		adj[arista.getOrigen()].add(arista); 
		numeroDeAristas++;
	}
	
	public Iterable<Arista> adj(Integer v){ 
		return adj[v]; 
	}
	
	public void mostrarAristas(){
		for(int i=0; i<numeroDeNodos; i++){
			System.out.print("nodo " + (i+1) + ": ");
			for(Arista arista: adj[i]){
				System.out.print((arista.getDestino()+1) + " ");
			}
			System.out.println();
		}
	}
	
	public Iterable<Arista> edges(){
		ListaAdyacencia<Arista> lista = new ListaAdyacencia<Arista>();
		for (int v = 0; v < numeroDeNodos; v++)
			for (Arista arista : adj[v])
				if (arista.elOtroExtremo(v) > v) 
					lista.add(arista);
		return lista;
	}
	
	public Iterable<Arista> Kruskal() {
		double pesoDelArbolRecubridor=0;  
	    Queue<Arista> arbolRecubridorMinimo = new LinkedList<Arista>();
        PriorityQueue<Arista> cola = new PriorityQueue<Arista>();
        for (Arista e : this.edges()) {
            cola.add(e);
        }
        UniónBuscar ub = new UniónBuscar(numeroDeNodos);
        while (!cola.isEmpty() && arbolRecubridorMinimo.size() < numeroDeNodos - 1) {
            Arista e = cola.remove();
            int v = e.getOrigen();
            int w = e.elOtroExtremo(v);
            if (ub.Find(v)!=ub.Find(w)) { 
                ub.Union(v, w); 
                arbolRecubridorMinimo.add(e);  
                pesoDelArbolRecubridor += e.getPeso();
            }
        }
        return arbolRecubridorMinimo;
    }
	
	public Iterable<Arista> prim(){
		Queue<Arista> arbolRecubridorMinimo = new LinkedList<Arista>();
		Arista[] edgeTo = new Arista[numeroDeNodos];        
	    double[] minDistancia = new double[numeroDeNodos];      
	    boolean [] fueVisitado = new boolean[numeroDeNodos];
	    ColaDePrioridades<Double> cola = new ColaDePrioridades<Double>();
	    
	    for(int i=0; i<numeroDeNodos; i++){
	    	minDistancia[i]=Double.POSITIVE_INFINITY;
	    }
	    
	    for (int i = 0; i < numeroDeNodos; i++){      
            if (!fueVisitado[i]){
            	minDistancia[i] = 0.0;
                cola.add(new Contenedor(i, minDistancia[i]));
                while (!cola.isEmpty()) {
                    int v = cola.remove().getDato();
                    fueVisitado[v] = true;
                    for (Arista cadaArista : adj(v)) {
                        int w = cadaArista.elOtroExtremo(v);
                        if (fueVisitado[w]) 
                        	continue;
                        if (cadaArista.getPeso() < minDistancia[w]) {
                        	minDistancia[w] = cadaArista.getPeso();
                            edgeTo[w] = cadaArista;
                            if (cola.contains(w)) 
                            	cola.change(w, minDistancia[w]);
                            else                
                            	cola.add(new Contenedor(w, minDistancia[w]));
                        }
                    }
                }
            }
	    }
	    for (int v = 0; v < edgeTo.length; v++) {
            Arista arista = edgeTo[v];
            if (arista != null) {
            	arbolRecubridorMinimo.add(arista);
            }
        }
	    return arbolRecubridorMinimo;
	}
	
	public ArrayList<Integer> dijkstra(Integer origen){
		ColaDePrioridades<Double> cola = new ColaDePrioridades<Double>();
		ArrayList<Integer> caminoMasCorto = new ArrayList<Integer>(); 
		
		adj[origen].setMinDistancia(0.0); 
	    cola.add(new Contenedor(origen, 0.0));
		
	    while( !cola.isEmpty() ){   
	    	Integer actual = cola.remove().getDato();
	    	System.out.print(actual+1 + " ");
	    	caminoMasCorto.add(actual);
	    	for(Arista arista : adj[actual]){
				int destino = arista.getDestino();
				if (adj[destino].getMinDistancia() > adj[actual].getMinDistancia() + arista.getPeso()){
					adj[destino].setMinDistancia(adj[actual].getMinDistancia() + arista.getPeso()); 
					if (cola.contains(destino)){ 
						cola.change(destino, adj[destino].getMinDistancia());
					}
					else 
						cola.add(new Contenedor(destino, adj[destino].getMinDistancia()));
				}
			}
	    }
	    return caminoMasCorto;
	}
	
	
	public void busquedaEnAnchura(Integer nodo){
		Queue<Integer> cola = new LinkedList<Integer>();
		boolean [] fueVisitado = new boolean[numeroDeNodos];
		cola.add(nodo);
		fueVisitado[nodo]=true;
		System.out.print((nodo+1) + " ");
		while(!cola.isEmpty()){
			Integer n1 = cola.remove();
			for(Arista j:adj[n1]){
				if(!fueVisitado[j.getDestino()]){
					fueVisitado[j.getDestino()]=true;
					System.out.print((j.getDestino()+1) + " ");
					cola.add(j.getDestino());
				}
			}
		}
	}
	
	public void busquedaEnProfundidad(Integer nodo){
		Stack<Integer> pila = new Stack<Integer>();
		boolean [] fueVisitado = new boolean[numeroDeNodos];
		pila.push(nodo);
		fueVisitado[nodo]=true;
		System.out.print((nodo+1) + " ");
		while(!pila.isEmpty()){
			Integer n1= pila.peek();
			boolean visitoArista = false;
			for(Arista j: adj[n1]){
				if(!fueVisitado[j.getDestino()]){
					System.out.print((j.getDestino()+1) + " ");
					visitoArista=true;
					fueVisitado[j.getDestino()]=true;
					pila.push(j.getDestino());
					break;
				}
			}
			if(!visitoArista){
				pila.pop();
			}
		}
	}
	
	public static void main(String[] args) {
		Grafo grafo = new Grafo("grafo.in");
		grafo.mostrarAristas();
		System.out.println("GrafoConMatrizDeAdyacencia");
		grafo.mostrarAristas();
		System.out.println("busquedaEnAnchura");
		grafo.busquedaEnAnchura(0);
		System.out.println();
		System.out.println("busquedaEnProfundidad");
		grafo.busquedaEnProfundidad(0);
		System.out.println();
		System.out.println("dijkstra");
		grafo.dijkstra(0);
		System.out.println();
		System.out.println("prim");
		Iterable<Arista> bordesDelArbol = grafo.prim();
		for(Arista cadaArista: bordesDelArbol){
			System.out.println(cadaArista);
		}
		
		System.out.println("Kruskal");
		Iterable<Arista> arbolRecubridorMinimo2 = grafo.Kruskal();
		for(Arista cadaArista: arbolRecubridorMinimo2){
			System.out.println(cadaArista);
		}
	}
}
