package grafosConListaDeAdyacencia;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Grafo {
	private Integer numeroDeNodos; 
	private Integer numeroDeAristas;
	private ListaAdyacencia<Integer> [] adj;
	
	public Grafo (String nombre){
		LeerArchivo archivoIn = new LeerArchivo(nombre);
		String [] linea;
		try {
			numeroDeNodos = Integer.parseInt(archivoIn.readLine());
			this.numeroDeNodos = numeroDeNodos; 
			this.numeroDeAristas = 0;
			adj = (ListaAdyacencia<Integer>[]) new ListaAdyacencia[numeroDeNodos]; 
			for(int i=0; i<numeroDeNodos; i++){ 
				adj[i] = new ListaAdyacencia<Integer>(); 
			}
			
			for(int i=0; i<numeroDeNodos; i++){
				linea = archivoIn.readLine().split(" ");
				Integer nombreDeNodo = Integer.parseInt(linea[0]);
				Integer numeroAristas = Integer.parseInt(linea[1]);
				
				for(int j=0; j<numeroAristas; j++){
					linea = archivoIn.readLine().split(" ");
					Integer arista = Integer.parseInt(linea[0]) - 1;
					
					addArista(i,arista);
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
	
	public void addArista(Integer origen, Integer destino){
		adj[origen].add(destino); 
		numeroDeAristas++;
	}
	
	public Iterable<Integer> adj(Integer v){ 
		return adj[v]; 
	}
	
	public void mostrarAristas(){
		for(int i=0; i<numeroDeNodos; i++){
			System.out.print("nodo " + (i+1) + ": ");
			for(Integer j: adj[i]){
				
				System.out.print((j+1) + " ");
			}
			System.out.println();
		}
	}
	
	public void busquedaEnAnchura(Integer nodo){
		Queue<Integer> cola = new LinkedList<Integer>();
		boolean [] fueVisitado = new boolean[numeroDeNodos];
		cola.add(nodo);
		fueVisitado[nodo]=true;
		System.out.print((nodo+1) + " ");
		while(!cola.isEmpty()){
			Integer n1 = cola.remove();
			for(Integer j:adj[n1]){
				if(!fueVisitado[j]){
					fueVisitado[j]=true;
					System.out.print((j+1) + " ");
					cola.add(j);
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
			for(Integer j: adj[n1]){
				if(!fueVisitado[j]){
					System.out.print((j+1) + " ");
					visitoArista=true;
					fueVisitado[j]=true;
					pila.push(j);
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
		grafo.busquedaEnAnchura(0);
		System.out.println();
		grafo.busquedaEnProfundidad(0);
		
	}
}
