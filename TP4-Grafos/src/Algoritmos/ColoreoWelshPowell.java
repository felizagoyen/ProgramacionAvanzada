package Algoritmos;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import Generadores.MatrizSimetrica;

public class ColoreoWelshPowell {

	private MatrizSimetrica matrizAdyacencia;
	private Integer cantidadNodos;
	private Integer cantidadAristas;
	private Integer porcentajeAdyacencia;
	private ArrayList<Integer> gradoNodo = new ArrayList<Integer>();
	private ArrayList<Integer> nodos = new ArrayList<Integer>();
	private ArrayList<Integer> colorNodos = new ArrayList<Integer>();
	private Integer cantidadColores = 1;
	private Integer maximoGrado;
	private Integer minimoGrado;
	
	public ColoreoWelshPowell(String ruta) {
		
		matrizAdyacencia = new MatrizSimetrica(ruta);
		this.cantidadNodos = matrizAdyacencia.getCantNodos();
		this.cantidadAristas = matrizAdyacencia.getCantAristas();
		this.porcentajeAdyacencia = (int) ((cantidadNodos - 1) * 100) / (((cantidadNodos -1) * cantidadNodos)/2);

		for(int x = 0; x < cantidadNodos; x++) {
			gradoNodo.add(0);
			nodos.add(x);
			colorNodos.add(0);
		}

		for (int x = 0; x < cantidadNodos; x++)
			for (int y = x + 1; y < cantidadNodos; y++) 
				if(matrizAdyacencia.getVector(x, y) == 1) {
					gradoNodo.set(x, gradoNodo.get(x) + 1);
					gradoNodo.set(y, gradoNodo.get(y) + 1);
				}

		for (int x = 0; x < cantidadNodos; x++) 
			for (int y = x + 1; y < cantidadNodos; y++) 
				if (gradoNodo.get(x) < gradoNodo.get(y)) {
					Integer auxGrado = gradoNodo.get(x);
					gradoNodo.set(x, gradoNodo.get(y));
					gradoNodo.set(y, auxGrado);
					Integer auxNodo = nodos.get(x);
					nodos.set(x, nodos.get(y));
					nodos.set(y, auxNodo);
				}
		
		this.maximoGrado = gradoNodo.get(0);
		this.minimoGrado = gradoNodo.get(cantidadNodos - 1);
		
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
		ColoreoWelshPowell welshPowell = new ColoreoWelshPowell("Prim con matriz 200 nodos 50% ady.out");
		welshPowell.resolver();
		welshPowell.generarArchivoSalida(new File("coloreado.out"));
	}

}