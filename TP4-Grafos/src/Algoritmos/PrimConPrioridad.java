package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class PrimConPrioridad {

	private Integer cantidadNodos;
	private Integer cantidadAristas;
	private Double porcentajeAdyacencia;
	private ArrayList<ArrayList<HashMap<Integer,Integer>>> listaAdyacencia;
	private ArrayList<Integer> colaDePrioridad = new ArrayList<Integer>();
	private ArrayList<Integer> valorAdyacente = new ArrayList<Integer>();
	private ArrayList<Integer> nodoAdyacente = new ArrayList<Integer>();
	
	public PrimConPrioridad() {
		
	}
	
	public void resolver() {
		Integer nodoCola = 0;
		valorAdyacente.set(0, 0);
		
		while(colaDePrioridad.isEmpty() == false) {
			nodoCola = minimoCola();
			Integer valor = Integer.MAX_VALUE;
			
			for(HashMap<Integer, Integer> cadaNodo: listaAdyacencia.get(nodoCola)) {
				valor = (Integer) cadaNodo.values().toArray()[0];
				Integer nodo = (Integer) cadaNodo.keySet().toArray()[0];
				if(colaDePrioridad.contains(nodo) && valor < valorAdyacente.get(nodo)) {
					valorAdyacente.set(nodo, valor);
					nodoAdyacente.set(nodo, nodoCola);
				}
			}
		}
	}
	
	private Integer minimoCola() {
		Integer minimo = Integer.MAX_VALUE;
		Integer nodoMinimo = colaDePrioridad.get(0);

		for(Integer cadaValor: valorAdyacente) {
			Integer nodo = valorAdyacente.indexOf(cadaValor);
			if(cadaValor < minimo && colaDePrioridad.contains(nodo)) {
				minimo = cadaValor;
				nodoMinimo = nodo;
			}
		}

		colaDePrioridad.remove(nodoMinimo);
		return nodoMinimo;
	}
	
	public void cargarDatosDesdeArchivo(File archivo) {

		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea = br.readLine();
			String lineaSplit[] = linea.split(" ");
			
			this.cantidadNodos = Integer.parseInt(lineaSplit[0]);
			this.cantidadAristas = Integer.parseInt(lineaSplit[1]);
			this.porcentajeAdyacencia = Double.parseDouble(lineaSplit[2]);
			
			listaAdyacencia = new ArrayList<ArrayList<HashMap<Integer, Integer>>>();
			
			for(int x = 0; x < cantidadNodos; x++) {
				listaAdyacencia.add(x,new ArrayList<HashMap<Integer, Integer>>());
				nodoAdyacente.add(null);
				valorAdyacente.add(Integer.MAX_VALUE);
				colaDePrioridad.add(x);
			}

			HashMap<Integer, Integer> nodo = null;
			while((linea = br.readLine()) != null) {
				lineaSplit = linea.split(" ");
				
				Integer x = Integer.parseInt(lineaSplit[0]);
				Integer y = Integer.parseInt(lineaSplit[1]);
				Integer valor = Integer.parseInt(lineaSplit[2]);
				nodo = new HashMap<Integer, Integer>();
				nodo.put(y, valor);
				listaAdyacencia.get(x).add(nodo);
				nodo = new HashMap<Integer, Integer>();
				nodo.put(x, valor);
				listaAdyacencia.get(y).add(nodo);
			}
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(br != null) 
					br.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void generarArchivoSalida(File archivo) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(archivo);
			pw.println(cantidadNodos + " " + cantidadAristas + " " + porcentajeAdyacencia);
			for(int x = 0; x < cantidadNodos; x++)
				if(nodoAdyacente.get(x) != null)
					pw.println(nodoAdyacente.get(x) + " " + x + " " + valorAdyacente.get(x));
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null)
				pw.close();
		}
	}

	
	
	public static void main(String[] args) {
		PrimConPrioridad prim = new PrimConPrioridad();
		prim.cargarDatosDesdeArchivo(new File("grafo.in"));
		prim.resolver();
		prim.generarArchivoSalida(new File("arbol.in"));
	}

}
