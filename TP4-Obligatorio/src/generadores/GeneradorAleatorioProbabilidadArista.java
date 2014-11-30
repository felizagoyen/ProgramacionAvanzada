package generadores;

import java.util.Scanner;

import matriz.MatrizDeAdyacencia;

public class GeneradorAleatorioProbabilidadArista  implements Generador{
private Integer numeroDeNodos;
	
	public GeneradorAleatorioProbabilidadArista(Integer numeroDeNodos) {
		this.numeroDeNodos=numeroDeNodos;
	}
	
	public void generar(String nombre){
		EscribeArchivo archivo = new EscribeArchivo(nombre);
		MatrizDeAdyacencia m = new MatrizDeAdyacencia(numeroDeNodos);
		m.generarProbabilidadDeArista();
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
		Integer numeroDeNodos;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese numero de nodos: ");
		numeroDeNodos = entrada.nextInt(); 
		GeneradorAleatorioProbabilidadArista g = new GeneradorAleatorioProbabilidadArista(numeroDeNodos);
		g.generar("grafo.in");
	}
}
