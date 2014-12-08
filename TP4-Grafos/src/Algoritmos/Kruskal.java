package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Kruskal {
	private Integer matrizAdyacencia[][];
	private Integer cantidadNodos;
	private Integer cantidadAristas;
	private Double porcentajeAdyacencia;
	private Integer centinela[];
	private ArrayList<String> arbolRecubridorMinimo = new ArrayList<String>();	
	private Integer costo;
	
	public Kruskal() {
	}
	
	public void resolver() {
		Integer nodoA = 0;
		Integer nodoB = 0;
		Integer aristasRecorridas = 1;
		costo = 0;
		
		while(aristasRecorridas < cantidadNodos) {
			Integer minimo = Integer.MAX_VALUE;
			
			for(int x = 0; x < cantidadNodos; x++)
				for(int y = 0; y < cantidadNodos; y++) 
					if(matrizAdyacencia[x][y] < minimo && !centinela[x].equals(centinela[y])) {
						minimo = matrizAdyacencia[x][y];
						nodoA = x;
						nodoB = y;
					}
						
			if(!centinela[nodoA].equals(centinela[nodoB])) {
				Integer centinelaB = centinela[nodoB];
				arbolRecubridorMinimo.add(nodoA + " " + nodoB + " " + minimo);
				costo += minimo;
				for(int x = 0; x < cantidadNodos; x++)
					if(centinela[x].equals(centinelaB))
						centinela[x] = centinela[nodoA];
				
				aristasRecorridas++;
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
			
			centinela = new Integer[cantidadNodos];
			this.matrizAdyacencia = new Integer[cantidadNodos][cantidadNodos];
			
			for(int x = 0; x < cantidadNodos; x++) {
				for(int y = 0; y < cantidadNodos; y++) 
					matrizAdyacencia[x][y] = Integer.MAX_VALUE;
				centinela[x] = x;
			}
			
			for(int z = 0; z < cantidadAristas; z++) {
				lineaSplit = br.readLine().split(" ");
				int x = Integer.parseInt(lineaSplit[0]);
				int y = Integer.parseInt(lineaSplit[1]);
				int valor = Integer.parseInt(lineaSplit[2]);
				
				matrizAdyacencia[x][y] = valor;
				matrizAdyacencia[y][x] = valor;						
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
			porcentajeAdyacencia = (arbolRecubridorMinimo.size()*(cantidadNodos-1)*50.0) / (cantidadNodos * (cantidadNodos-1));
			pw.println(cantidadNodos + " " + arbolRecubridorMinimo.size() + " " + porcentajeAdyacencia);

			for(String cadaPar: arbolRecubridorMinimo)
				pw.println(cadaPar);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null)
				pw.close();
		}
	}
	
	
	public static void main(String[] args) {
	
		Kruskal kruskal = new Kruskal();
		kruskal.cargarDatosDesdeArchivo(new File("grafo.in"));

		GregorianCalendar tIni = new GregorianCalendar();
		kruskal.resolver();
		GregorianCalendar tFin = new GregorianCalendar();
		long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
		System.out.println(diff);

		kruskal.generarArchivoSalida(new File("arbolMinimo.out"));
		
	}

}
