package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

class Nodo implements Comparable<Nodo> {
	Integer nodo;
	Integer valor;
	
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
	
	public PrimConPrioridad() {
		
	}
	
	public void resolver() {
		PriorityQueue<Nodo> colaDePrioridad = new PriorityQueue<Nodo>();
		Nodo nodoCola;
		valorAdyacente.set(0, 0);
		colaDePrioridad.add(new Nodo(0,0));
		
		while(colaDePrioridad.isEmpty() == false) {
			nodoCola = colaDePrioridad.poll();
			Integer valor = Integer.MAX_VALUE;
			
			if(visitado.get(nodoCola.getNodo()) == false) {
				visitado.set(nodoCola.getNodo(), true);
				for(Nodo cadaNodo: listaAdyacencia.get(nodoCola.getNodo())) {
					valor = (Integer) cadaNodo.getValor();
					Integer nodo = (Integer) cadaNodo.getNodo();
					if(visitado.get(nodo) == false && valor < valorAdyacente.get(nodo)) {
						valorAdyacente.set(nodo, valor);
						nodoAdyacente.set(nodo, nodoCola.getNodo());
						colaDePrioridad.add(new Nodo(nodo, valor));
					}
				}
			}
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
			}

			while((linea = br.readLine()) != null) {
				lineaSplit = linea.split(" ");
				
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
			pw.println(cantidadNodos + " " + cantidadAristas + " " + porcentajeAdyacencia);
			for(int x = 0; x < cantidadNodos; x++)
				if(nodoAdyacente.get(x) != null)
					pw.println(nodoAdyacente.get(x) + " " + x);
			
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
