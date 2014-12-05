package generadores;


import java.util.Scanner;

import fileIO.EscribeArchivo;
import matrizDeAdyacencia.MatrizDeAdyacencia;

public class Generador{
	private static final int PORCENTAJE_ADYACENCIA_ALEATORIO = 1;
	private static final int PROBABILIDAD_ARISTA_ALEATORIO = 2;
	private static final int N_PARTITO = 3;
	private static final int GRADO_REGULAR = 4;
	private static final int GRADO_REGULAR_PORCENTAJE_ADYACENCIA = 5;
	
	
	public void generar(Integer numeroDeNodos, Integer key, String nombre){
		EscribeArchivo archivo = new EscribeArchivo(nombre);
		MatrizDeAdyacencia m = new MatrizDeAdyacencia(numeroDeNodos);
		Scanner entrada = new Scanner(System.in);
		switch (key) {
		case PORCENTAJE_ADYACENCIA_ALEATORIO:{
			System.out.println("Ingrese porcentaje adyacencia: ");
			Integer porcentajeAdyacencia = entrada.nextInt(); 
			m.generarPorPorcentajeDeAdyacencia(porcentajeAdyacencia);
		}break;
		case PROBABILIDAD_ARISTA_ALEATORIO:{
			System.out.println("Ingrese porcentaje adyacencia: ");
			Integer porcentajeAdyacencia = entrada.nextInt(); 
			m.generarProbabilidadDeArista(porcentajeAdyacencia);
		}break;
		case N_PARTITO:{
			System.out.println("Ingrese numero de particiones: ");
			Integer numeroDeParticiones = entrada.nextInt(); 
			m.generatNPartito(numeroDeParticiones);	
		}break;
		case GRADO_REGULAR:{
			m.generadorRegular();	
		}break;
		case GRADO_REGULAR_PORCENTAJE_ADYACENCIA:{
			System.out.println("Ingrese porcentaje adyacencia: ");
			Integer porcentajeAdyacencia = entrada.nextInt(); 
			m.generadorRegularPorPorcentajeDeAdyacencia(porcentajeAdyacencia);	
		}break;	
		default:
			System.out.println("Opcion incorrecta");
			break;
		}
			
		archivo.writeLine(numeroDeNodos.toString());
		for(int i=0; i<numeroDeNodos; i++){
			Integer numeroDeAristas = m.getNumeroAristas(i);
			if(numeroDeAristas>0){
				archivo.writeLine((i+1) + " " + numeroDeAristas);
				for(int j=0; j<numeroDeNodos; j++){
					if(m.getMatriz()[i][j]>0)
						archivo.writeLine((j+1) + " " + m.getMatriz()[i][j]);
				}
			}
		}	
		archivo.cerrarArchivo();
	}
	
	public static void main(String[] args) {
		Integer numeroDeNodos, key=0;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Seleccione el grafo a generar: ");
		System.out.println("1-Grafo aleatorio dado N y el porcentaje de adyacencia");
		System.out.println("2-Grafo aleatorio dado N y una probabilidad para cada arista");
		System.out.println("3-Grafo n-partito, dado N y n");
		System.out.println("4-Grafo regular dado N y el grado");
		System.out.println("5-Grafo regular dado N y el porcentaje de adyacencia");
		key = entrada.nextInt();
		System.out.println("Ingrese numero de nodos: ");
		numeroDeNodos = entrada.nextInt(); 
		Generador g = new Generador();
		g.generar(numeroDeNodos, key, "grafo.in");
	}
}
