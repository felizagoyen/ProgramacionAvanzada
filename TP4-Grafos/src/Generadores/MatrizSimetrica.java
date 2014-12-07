package Generadores;

import java.io.*;
import static java.lang.Math.pow;

public class MatrizSimetrica {
	private int cantNodos;
	private int cantAristas;
	private int vector[];
	
	public MatrizSimetrica(String ruta){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try{
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String []datos = br.readLine().split(" ");
			cantNodos=Integer.parseInt(datos[0]);
			cantAristas=Integer.parseInt(datos[1]);
			
			int tamVector = (int)((pow(cantNodos, 2)-cantNodos)/2);
			vector = new int [tamVector];
			
			for(int i = 0; i < cantAristas; i++){
				datos = br.readLine().split(" ");
				setVector(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				fr.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public MatrizSimetrica(int nodos){
		this.cantNodos = nodos;
		int tamVector = (int)((pow(cantNodos, 2) - cantNodos)/2);
		vector=new int [tamVector];
	}
	
	public void setVector(int fila, int columna , int valor){
		if(columna < fila){
			int aux=fila;
			fila = columna;
			columna = aux;
		}
		
		int indice = (int)(fila *cantNodos+ columna- (pow(fila,2)+3 * fila + 2)/2);
		vector[indice] = valor;
	}
	
	public int getVector(int fila, int columna){
		if(columna < fila){
			int aux=fila;
			fila = columna;
			columna = aux;
		}
		
		int indice = (int)(fila *cantNodos+ columna- (pow(fila,2)+3 * fila + 2)/2);
		return vector[indice];
	}
	
	public static void main(String[] arg){
		MatrizSimetrica m1 = new MatrizSimetrica(4);
		m1.setVector(1, 2, 32);
		System.out.println(m1.getVector(1, 2));
	}
}
