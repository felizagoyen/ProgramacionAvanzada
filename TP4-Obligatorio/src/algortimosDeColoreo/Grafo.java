package algortimosDeColoreo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;

import colaDePrioridad.ColaDePrioridades;
import fileIO.LeerArchivo;

public class Grafo {
	private Integer numeroDeNodos; 
	private Integer numeroDeAristas;
	private List<Integer> [] adj;
	
	public Grafo (String nombre){
		LeerArchivo archivoIn = new LeerArchivo(nombre);
		String [] linea;
		try {
			numeroDeNodos = Integer.parseInt(archivoIn.readLine());
			this.numeroDeNodos = numeroDeNodos; 
			this.numeroDeAristas = 0;
			adj = (List<Integer>[]) new LinkedList[numeroDeNodos]; 
			for(int i=0; i<numeroDeNodos; i++){ 
				adj[i] = new LinkedList<Integer>(); 
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
		adj[destino].add(origen);
		numeroDeAristas++;
	}
	
	public Iterable<Integer> adj(Integer v){ 
		return adj[v]; 
	}
	
	public void mostrarAristas(){
		for(int i=0; i<numeroDeNodos; i++){
			System.out.print("nodo " + (i+1) + ": ");
			for(Integer j: adj[i]){
				
				System.out.print((j+1) +" ");
			}
			System.out.println();
		}
	}
	
	public int secuencialAleatorio(ArrayList<Integer> secuenciaDeNodos){
		char color = 'A';
		boolean [] tieneColor = new boolean[numeroDeNodos];
		HashMap<Integer, String> coloreo = new HashMap<Integer, String>();
		for(Integer cadaNodo: secuenciaDeNodos){
			ArrayList<String> colores = new ArrayList<String>(); 
			for(Integer cadaNodoAdy : adj[cadaNodo]){
				if(tieneColor[cadaNodoAdy]){
					boolean existe = false;
					for(String cadaColor: colores){
						if(cadaColor.equals(coloreo.get(cadaNodoAdy))){
							existe = true;
							break;
						}
					}
					if(!existe)
						colores.add(coloreo.get(cadaNodoAdy));
				}
			}
			Collections.sort(colores);
			Integer i=0;
			for(String cadaColor: colores){
				if(cadaColor.equals(String.valueOf(Character.toChars(color+i))))
					i++;
				else
					break;
			}
			tieneColor[cadaNodo]=true;
			if(colores.isEmpty())
				coloreo.put(cadaNodo, String.valueOf(color));
			else	
				coloreo.put(cadaNodo, String.valueOf(Character.toChars(color+i)));
		}
		System.out.println();
		Set<String> coloresUsados = new HashSet<String>();
		for(Integer cadaNodo: secuenciaDeNodos){
			System.out.println(cadaNodo+1 + " " + coloreo.get(cadaNodo));
			coloresUsados.add(coloreo.get(cadaNodo));
		}
		return coloresUsados.size();
	}
	
	public int welshPowell(){
		HashMap<Integer,Integer> grados = new HashMap<Integer,Integer>();
		class ValueComparator implements Comparator<Integer> {
		    Map<Integer, Integer> base;
		    public ValueComparator(Map<Integer, Integer> base) { this.base = base; }    
		    public int compare(Integer a, Integer b) {
		        if (base.get(a) > base.get(b)) { return -1; } 
		        else { return 1; } 
		    }
		}
		ValueComparator bvc =  new ValueComparator(grados);
		TreeMap<Integer,Integer> ordenadoPorGrados = new TreeMap<Integer,Integer>(bvc);
		for(int i=0; i<numeroDeNodos; i++){
			grados.put(i, adj[i].size());
		}
		ordenadoPorGrados.putAll(grados);
		ArrayList<Integer> secuenciaDeNodos = new ArrayList<Integer>(); 
		for(Map.Entry<Integer,Integer> cadaNodo : ordenadoPorGrados.entrySet()) {
			secuenciaDeNodos.add(cadaNodo.getKey());
		}
		return secuencialAleatorio(secuenciaDeNodos);
	}
	
	public int matula(){
		HashMap<Integer,Integer> grados = new HashMap<Integer,Integer>();
		class ValueComparator implements Comparator<Integer> {
		    Map<Integer, Integer> base;
		    public ValueComparator(Map<Integer, Integer> base) { this.base = base; }    
		    public int compare(Integer a, Integer b) {
		        if (base.get(a) < base.get(b)) { return -1; } 
		        else { return 1; } 
		    }
		}
		ValueComparator bvc =  new ValueComparator(grados);
		TreeMap<Integer,Integer> ordenadoPorGrados = new TreeMap<Integer,Integer>(bvc);
		for(int i=0; i<numeroDeNodos; i++){
			grados.put(i, adj[i].size());
		}
		ordenadoPorGrados.putAll(grados);
		ArrayList<Integer> secuenciaDeNodos = new ArrayList<Integer>(); 
		for(Map.Entry<Integer,Integer> cadaNodo : ordenadoPorGrados.entrySet()) {
			secuenciaDeNodos.add(cadaNodo.getKey());
		}
		return secuencialAleatorio(secuenciaDeNodos);
	}
	
	public ArrayList<Integer> busquedaEnAnchura(Integer nodo){
		Queue<Integer> cola = new LinkedList<Integer>();
		ArrayList<Integer> recorrido = new ArrayList<Integer>();
		boolean [] fueVisitado = new boolean[numeroDeNodos];
		cola.add(nodo);
		fueVisitado[nodo]=true;
		recorrido.add(nodo);
		System.out.print((nodo+1) + " ");
		while(!cola.isEmpty()){
			Integer n1 = cola.remove();
			for(Integer j:adj[n1]){
				if(!fueVisitado[j]){
					fueVisitado[j]=true;
					System.out.print((j+1) + " ");
					recorrido.add(j);
					cola.add(j);
				}
			}
		}
		return recorrido;
	}
	
	public static void main(String[] args) {
		Grafo grafo = new Grafo("grafo.in");
		grafo.mostrarAristas();
		grafo.busquedaEnAnchura(0);
		System.out.println();
		System.out.println("Coloreo Secuencial Aleatorio");
		System.out.println("cantidad de colores usados: "+grafo.secuencialAleatorio(grafo.busquedaEnAnchura(0)));
		System.out.println("Coloreo Welsh-Powell");
		System.out.println("cantidad de colores usados: "+grafo.welshPowell());
		System.out.println("Coloreo Matula");
		System.out.println("cantidad de colores usados: "+grafo.matula());
	}
}
