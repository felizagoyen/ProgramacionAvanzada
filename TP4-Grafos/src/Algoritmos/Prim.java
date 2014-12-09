package Algoritmos;

import java.util.*;
import java.io.*;

public class Prim {

	private final static int inf = 100000;
	private int cantNodos;
	private int cantAristas;
	private int[][] matrizAdy;
	private double porcentajeAdy;	
	private ArrayList<String> arbol = new ArrayList<String>();
	private int[] cantAdy;
	
	public Prim(String ruta){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] datos = null;
		
		try{
			archivo = new File (ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
			
			datos = br.readLine().split(" ");
			
			cantNodos = Integer.parseInt(datos[0]);
			cantAristas = Integer.parseInt(datos[1]);
			porcentajeAdy = Double.parseDouble(datos[2]);
			matrizAdy = new int[cantNodos][cantNodos];
			cantAdy = new int[cantNodos];
			
			for (int i = 0; i < cantNodos; i++)
				for (int j = 0; j < cantNodos; j++)
					matrizAdy[i][j] = inf;
			
			for (int i = 0; i < cantAristas; i++){
				datos = br.readLine().split(" ");
				matrizAdy[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])] = Integer.parseInt(datos[2]);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				fr.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	

	public int resolver(){
	
		boolean[] conjSolucion = new boolean[cantNodos];
		boolean bandera = false;
		int min, fila, columna;
		int costo = 0;
		
		conjSolucion[0] = true;
		
		while(!bandera){
			bandera = true;
			min = inf;
			fila = 0;
			columna = 0;
			for (int i = 0; i < cantNodos; i++)
				for (int j = i+1; j < cantNodos; j++)
					if ((conjSolucion[i] && !conjSolucion[j]) || (conjSolucion[j] && !conjSolucion[i]))
						if(matrizAdy[i][j] != inf)
							if(matrizAdy[i][j] < min){							
								min = matrizAdy[i][j];
								bandera = false;
								fila = i;
								columna = j;
							}
			if (!bandera){
				arbol.add(fila + " " + columna + " " + min);
				cantAdy[fila]++;
				cantAdy[columna]++;
				costo += min;
				if (!conjSolucion[columna])
					conjSolucion[columna] = true;
				else{
					conjSolucion[fila] = true;
				}
			}
		}
		
		for (int i = 0; i < cantNodos; i++)
			if (!conjSolucion[i])
				return 0;

		return costo;	
		
	}

	public void guardarGrafo (String ruta){
		
		File archivo = null;
		PrintWriter pw = null;
	
		try{
			archivo = new File (ruta);
			pw = new PrintWriter (archivo);
			Arrays.sort(cantAdy);
			
			porcentajeAdy = (arbol.size()*(cantNodos-1)*50.0) / (cantNodos * (cantNodos-1));
			
			pw.println(cantNodos + " " +  arbol.size() + " " + porcentajeAdy + " " + cantAdy[cantNodos- 1] + " " + cantAdy[0]);
			for(String eachPar: arbol)
				pw.println(eachPar);
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				pw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		Prim prim = new Prim("grafo.in");
		
		GregorianCalendar tIni = new GregorianCalendar();
		prim.resolver();
		GregorianCalendar tFin = new GregorianCalendar();
		long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
		System.out.println(diff);

		prim.guardarGrafo("arbolMinimo.out");
	}
}
