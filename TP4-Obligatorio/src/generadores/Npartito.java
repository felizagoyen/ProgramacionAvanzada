package generadores;

public class Npartito {

	private int particiones;
	private int cantNodos;
	private int [][] matrizAdy;
	
	public Npartito (int part, int nodos){
		particiones = part;
		cantNodos = nodos;
		matrizAdy = new int [cantNodos][cantNodos];
		
		int nodoIni, nodosSub;
		nodosSub = (int)(cantNodos/particiones);
		
		for (int i = 0; i < particiones-1; i++){
			nodoIni = i*nodosSub;
			generadorAux (nodosSub, nodoIni);
		}
		int nodosRestantes = cantNodos - nodosSub*particiones-1;
		nodoIni = cantNodos - nodosRestantes;
		generadorAux(nodosRestantes, nodoIni);
	}
	
	public void generadorAux (int cantNodos, int nodoIni){
		for (int i = nodoIni; i < nodoIni+cantNodos-1 ; i++)
			for (int j = i; i < nodoIni+cantNodos-1 ; j++){
				if (i == j)
					matrizAdy [i][j] = 0;
				else{
				matrizAdy[i][j] = 1;
				matrizAdy[j][i] = 1;
			}
}

}
}