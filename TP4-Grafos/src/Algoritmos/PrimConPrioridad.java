package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

class Nodo implements Comparable<Nodo> {
	private Integer nodo;
	private Integer valor;
	
	public Nodo(Integer nodo, Integer valor){
		this.nodo = nodo; 
		this.valor = valor;
	}
	public int compareTo(Nodo other){
		if(this.valor > other.valor)
			return 1;
		if(this.valor < other.valor)
			return -1;
		return 0;
	}
	
	public Integer getNodo() {
		return nodo;
	}
	
	public Integer getValor() {
		return valor;
	}
}

public class PrimConPrioridad {

	private Integer cantidadNodos;
	private Integer cantidadAristas;
	private Double porcentajeAdyacencia;
	private ArrayList<ArrayList<Nodo>> listaAdyacencia;
	private ArrayList<Integer> valorAdyacente = new ArrayList<Integer>();
	private ArrayList<Integer> nodoAdyacente = new ArrayList<Integer>();
	private ArrayList<Boolean> visitado = new ArrayList<Boolean>();
	private Integer costo;
	private ArrayList<Integer> cantidadAdyacentes = new ArrayList<Integer>();
	
	public PrimConPrioridad() {
		
	}
	
	public void resolver() {
		PriorityQueue<Nodo> colaDePrioridad = new PriorityQueue<Nodo>();
		Nodo nodoCola;
		valorAdyacente.set(0, 0);
		colaDePrioridad.add(new Nodo(0,0));
		costo = 0;
		
		while(colaDePrioridad.isEmpty() == false) {
			nodoCola = colaDePrioridad.poll();
			
			if(visitado.get(nodoCola.getNodo()) == false) {
				visitado.set(nodoCola.getNodo(), true);
				for(Nodo cadaNodo: listaAdyacencia.get(nodoCola.getNodo())) {
					Integer valor = (Integer) cadaNodo.getValor();
					Integer nodo = (Integer) cadaNodo.getNodo();
					if(visitado.get(nodo) == false && valor < valorAdyacente.get(nodo)) {
						valorAdyacente.set(nodo, valor);
						nodoAdyacente.set(nodo, nodoCola.getNodo());
						colaDePrioridad.add(new Nodo(nodo, valor));
					}
				}
			}
		}
		for(int x = 0; x < cantidadNodos; x++) 
			if(nodoAdyacente.get(x) != null) {
				costo += valorAdyacente.get(x);
				cantidadAdyacentes.set(x, cantidadAdyacentes.get(x) + 1);
				cantidadAdyacentes.set(nodoAdyacente.get(x), cantidadAdyacentes.get(nodoAdyacente.get(x)) + 1);
			}
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
			
			listaAdyacencia = new ArrayList<ArrayList<Nodo>>();
			
			for(int x = 0; x < cantidadNodos; x++) {
				listaAdyacencia.add(x,new ArrayList<Nodo>());
				nodoAdyacente.add(null);
				valorAdyacente.add(Integer.MAX_VALUE);
				visitado.add(false);
				cantidadAdyacentes.add(0);
			}

			for(int z = 0; z < cantidadAristas; z++) {
				lineaSplit = br.readLine().split(" ");
				
				Integer x = Integer.parseInt(lineaSplit[0]);
				Integer y = Integer.parseInt(lineaSplit[1]);
				Integer valor = Integer.parseInt(lineaSplit[2]);
				listaAdyacencia.get(x).add(new Nodo(y, valor));
				listaAdyacencia.get(y).add(new Nodo(x, valor));
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

			porcentajeAdyacencia = ((nodoAdyacente.size() - 1)*(cantidadNodos-1)*50.0) / (cantidadNodos * (cantidadNodos-1));
			Collections.sort(cantidadAdyacentes);
			
			pw.println(cantidadNodos + " " + (nodoAdyacente.size() - 1) + " " + porcentajeAdyacencia
									 + " " + cantidadAdyacentes.get(cantidadNodos - 1) + " " + cantidadAdyacentes.get(0));
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
		
		GregorianCalendar tIni = new GregorianCalendar();
		prim.resolver();
		GregorianCalendar tFin = new GregorianCalendar();
		long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
		System.out.println(diff);
		
		prim.generarArchivoSalida(new File("arbolMinimo.out"));
	}

}
