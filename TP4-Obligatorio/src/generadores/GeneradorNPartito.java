package generadores;

import java.util.Scanner;

import matriz.MatrizDeAdyacencia;

public class GeneradorNPartito implements Generador {
	private Integer numeroDeNodos;
	private Integer numeroDeParticiones;
	
	public GeneradorNPartito(Integer numeroDeNodos, Integer numeroDeParticiones) {
		this.numeroDeNodos=numeroDeNodos;
		this.numeroDeParticiones=numeroDeParticiones;
	}
	
	public void generar(String nombre){
		EscribeArchivo archivo = new EscribeArchivo(nombre);
		MatrizDeAdyacencia m = new MatrizDeAdyacencia(numeroDeNodos);
		m.generatNPartito(numeroDeParticiones);
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
		Integer numeroDeNodos, numeroDeParticiones;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese numero de nodos: ");
		numeroDeNodos = entrada.nextInt(); 
		System.out.println("Ingrese numero de particiones: ");
		numeroDeParticiones = entrada.nextInt(); 
		GeneradorNPartito g = new GeneradorNPartito(numeroDeNodos, numeroDeParticiones);
		g.generar("grafo.in");
	}
}
