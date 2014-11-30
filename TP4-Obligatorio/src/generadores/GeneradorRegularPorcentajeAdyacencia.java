package generadores;

import java.util.Scanner;

import matriz.MatrizDeAdyacencia;

public class GeneradorRegularPorcentajeAdyacencia implements Generador{
	private Integer numeroDeNodos;
	private Integer porcentajeAdyacencia;
	
	public GeneradorRegularPorcentajeAdyacencia(Integer numeroDeNodos, Integer porcentajeAdyacencia) {
		this.numeroDeNodos=numeroDeNodos;
		this.porcentajeAdyacencia=porcentajeAdyacencia;
	}
	
	public void generar(String nombre){
		EscribeArchivo archivo = new EscribeArchivo(nombre);
		MatrizDeAdyacencia m = new MatrizDeAdyacencia(numeroDeNodos);
		m.generadorRegularPorPorcentajeDeAdyacencia(porcentajeAdyacencia);
		archivo.writeLine(numeroDeNodos.toString());
		for(int i=0; i<numeroDeNodos; i++){
			Integer numeroDeAristas = m.getNumeroAristas(i);
			if(numeroDeAristas>0){
				archivo.writeLine((i+1) + " " + numeroDeAristas);
				for(int j=0; j<numeroDeNodos; j++){
					if(m.getMatriz()[i][j]==1)
						archivo.writeLine((j+1) + " " + m.getMatriz()[i][j]);
				}
			}
		}	
		archivo.cerrarArchivo();
	}
	
	public static void main(String[] args) {
		Integer numeroDeNodos, porcentajeAdyacencia;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese numero de nodos: ");
		numeroDeNodos = entrada.nextInt(); 
		System.out.println("Ingrese porcentaje adyacencia: ");
		porcentajeAdyacencia = entrada.nextInt(); 
		GeneradorRegularPorcentajeAdyacencia g = new GeneradorRegularPorcentajeAdyacencia(numeroDeNodos, porcentajeAdyacencia);
		g.generar("grafo.in");
	}
}
