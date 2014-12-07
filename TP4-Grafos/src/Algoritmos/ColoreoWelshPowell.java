package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ColoreoWelshPowell {

	private Boolean[][] matrizAdyacencia;
	private Integer cantidadNodos;
	private Integer cantidadAristas;
	private Double porcentajeAdyacencia;
	private ArrayList<Integer> gradoNodo = new ArrayList<Integer>();
	private ArrayList<Integer> nodos = new ArrayList<Integer>();
	private ArrayList<Integer> colorNodos = new ArrayList<Integer>();
	private Integer cantidadColores = 1;

	public ColoreoWelshPowell() {

	}

	public void resolver() {
		for(int x = 0; x < cantidadNodos; x++){
			Integer nodo = nodos.get(x);
			colorNodos.set(nodo, 1);
			Boolean coincideColor = false;
			Boolean finWhile = false;
			 
			while(!finWhile) {
				for(int y = 0; y < cantidadNodos; y++) 
					if(matrizAdyacencia[nodo][y] == true) 
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
			this.matrizAdyacencia = new Boolean[cantidadNodos][cantidadNodos];

			for (int x = 0; x < cantidadNodos; x++) {
				for (int y = 0; y < cantidadNodos; y++)
					matrizAdyacencia[x][y] = false;
				gradoNodo.add(0);
				nodos.add(x);
				colorNodos.add(0);
			}

			while ((linea = br.readLine()) != null) {
				lineaSplit = linea.split(" ");

				Integer x = Integer.parseInt(lineaSplit[0]);
				Integer y = Integer.parseInt(lineaSplit[1]);

				gradoNodo.set(x, gradoNodo.get(x) + 1);
				gradoNodo.set(y, gradoNodo.get(y) + 1);
				matrizAdyacencia[x][y] = true;
				matrizAdyacencia[y][x] = true;
			}

			for (int x = 0; x < cantidadNodos; x++) {
				for (int y = x + 1; y < cantidadNodos; y++) {
					if (gradoNodo.get(x) < gradoNodo.get(y)) {
						Integer auxGrado = gradoNodo.get(x);
						gradoNodo.set(x, gradoNodo.get(y));
						gradoNodo.set(y, auxGrado);
						Integer auxNodo = nodos.get(x);
						nodos.set(x, nodos.get(y));
						nodos.set(y, auxNodo);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void generarArchivoSalida(File archivo) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(archivo);
			pw.println(cantidadNodos + " " + cantidadAristas + " "
					+ porcentajeAdyacencia + " " + cantidadColores);

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
		ColoreoWelshPowell welshPowell = new ColoreoWelshPowell();
		welshPowell.cargarDatosDesdeArchivo(new File("grafo.in"));
		welshPowell.resolver();
		welshPowell.generarArchivoSalida(new File("coloreado.out"));
	}

}