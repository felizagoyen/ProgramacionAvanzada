package Algoritmos;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import Generadores.MatrizSimetrica;

public class ColoreoSecuencialAleatorio {

	private MatrizSimetrica matrizAdyacencia;
	private Integer cantidadNodos;
	private Integer cantidadAristas;
	private Double porcentajeAdyacencia;
	private ArrayList<Integer> nodos = new ArrayList<Integer>();
	private ArrayList<Integer> colorNodos = new ArrayList<Integer>();
	private Integer cantidadColores = 1;
	private Integer maximoGrado;
	private Integer minimoGrado;
	private ArrayList<Integer> gradoNodo = new ArrayList<Integer>();
	
	public ColoreoSecuencialAleatorio(String ruta) {
		matrizAdyacencia = new MatrizSimetrica(ruta);
		this.cantidadNodos = matrizAdyacencia.getCantNodos();
		this.cantidadAristas = matrizAdyacencia.getCantAristas();
		this.porcentajeAdyacencia = (cantidadAristas*(cantidadNodos-1)*50.0) / (cantidadNodos * (cantidadNodos-1));

		for(int x = 0; x < cantidadNodos; x++) {
			nodos.add(x);
			colorNodos.add(0);
			gradoNodo.add(0);
		}
		
		for(int x = 0; x < cantidadNodos; x++) 
			for (int y = x + 1; y < cantidadNodos; y++) 
			if(matrizAdyacencia.getVector(x, y) == 1) {
				gradoNodo.set(x, gradoNodo.get(x) + 1);
				gradoNodo.set(y, gradoNodo.get(y) + 1);
			}
		
		Collections.sort(gradoNodo);
		this.minimoGrado = gradoNodo.get(0);
		this.maximoGrado = gradoNodo.get(cantidadNodos - 1);
	}

	public void resolver() {
		for(int x = 0; x < cantidadNodos; x++){
			Integer nodo = nodos.get(x);
			colorNodos.set(nodo, 1);
			Boolean coincideColor = false;
			Boolean finWhile = false;
			 
			while(!finWhile) {
				for(int y = 0; y < cantidadNodos; y++) 
					if(nodo != y && matrizAdyacencia.getVector(nodo, y) == 1) 
						if(colorNodos.get(nodo).equals(colorNodos.get(y)))
							coincideColor = true;
				if(coincideColor) {
					colorNodos.set(nodo, colorNodos.get(nodo) + 1);
					coincideColor = false;
					finWhile = false;
				} else {
					finWhile = true;
				}
				if(colorNodos.get(nodo) > cantidadColores)
					cantidadColores = colorNodos.get(nodo);
			 }
		}
	}

	public void generarArchivoSalida(File archivo) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(archivo);
			pw.println(cantidadNodos + " " + cantidadAristas + " "
					+ cantidadColores + " " + porcentajeAdyacencia + " "
					+ maximoGrado + " " + minimoGrado);

			for (int x = 0; x < cantidadNodos; x++) {
				Integer nodo = nodos.get(x);
				pw.println(nodo + " " + colorNodos.get(nodo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	public static void main(String[] args) {
		ColoreoSecuencialAleatorio secuencialAleatorio = new ColoreoSecuencialAleatorio("grafo.in");
		secuencialAleatorio.resolver();
		secuencialAleatorio.generarArchivoSalida(new File("coloreado.out"));
	}

}