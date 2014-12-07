package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Kruskal {
	Integer matrizAdyacencia[][];
	Integer cantidadNodos;
	Integer cantidadAristas;
	Double porcentajeAdyacencia;
	Integer centinela[];
	Integer matrizArbolRecubridorMinimo[][];	
	
	public Kruskal() {
	}
	
	
	public void resolver() {
		Integer nodoA = 0;
		Integer nodoB = 0;
		Integer aristasRecorridas = 1;
		
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
				matrizArbolRecubridorMinimo[nodoA][nodoB] = minimo;
				matrizArbolRecubridorMinimo[nodoB][nodoA] = minimo;
				
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
			this.porcentajeAdyacencia = Double.parseDouble(lineaSplit[2]);
			
			centinela = new Integer[cantidadNodos];
			this.matrizAdyacencia = new Integer[cantidadNodos][cantidadNodos];
			matrizArbolRecubridorMinimo = new Integer[cantidadNodos][cantidadNodos];
			
			for(int x = 0; x < cantidadNodos; x++) {
				for(int y = 0; y < cantidadNodos; y++) { 
					matrizArbolRecubridorMinimo[x][y] = 0;
					matrizAdyacencia[x][y] = Integer.MAX_VALUE;
				}
				centinela[x] = x;
			}
			
			while((linea = br.readLine()) != null) {
				lineaSplit = linea.split(" ");
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
			pw.println(cantidadNodos + " " + cantidadAristas + " " + porcentajeAdyacencia);
			for(int x = 0; x < cantidadNodos; x++)
				for(int y = x + 1; y < cantidadNodos; y++)
					if(matrizArbolRecubridorMinimo[x][y] != 0)
							pw.println(x + " " + y);
			
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
		kruskal.resolver();
		kruskal.generarArchivoSalida(new File("arbol.in"));

	}

}
