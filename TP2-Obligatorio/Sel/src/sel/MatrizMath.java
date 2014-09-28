package sel;

import java.text.DecimalFormatSymbols;
import java.util.Arrays;

public class MatrizMath {
	private Integer filas, columnas;
	private Double[][] matriz;

	public MatrizMath(int columnas, int filas, Double[][] matriz) {
		this.columnas = columnas;
		this.filas = filas;
		this.matriz = matriz;
	}

	public MatrizMath(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		matriz = new Double[filas][columnas];
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				matriz[i][j] = 0.0;
	}

	public MatrizMath sumar(MatrizMath otra) throws DistDimException {
		if (this.filas != otra.filas || this.columnas != otra.columnas)
			throw new DistDimException("Las matrices a sumar no tienen la misma dimenson");

		MatrizMath resultado = new MatrizMath(otra.filas, otra.columnas);
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				resultado.matriz[i][j] = matriz[i][j] + otra.matriz[i][j];
		return resultado;
	}

	public MatrizMath restar(MatrizMath otra) throws DistDimException {
		if (otra == null)
			throw new DistDimException("Otra es NULL!");

		if (!this.filas.equals(otra.filas) || !this.columnas.equals(otra.columnas))
			throw new DistDimException("Las matrices a sumar no tienen la misma dimenson");

		MatrizMath resultado = new MatrizMath(otra.filas, otra.columnas);
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				resultado.matriz[i][j] = matriz[i][j] - otra.matriz[i][j];
		return resultado;
	}

	public MatrizMath multiplicar(MatrizMath otra) throws DistDimException {
		if (otra == null)
			throw new DistDimException("Otra es NULL!");

		if (!this.columnas.equals(otra.filas))
			throw new DistDimException("Dimensiones incorrectas");

		MatrizMath resultado = new MatrizMath(this.filas, otra.columnas);
		for (int i = 0; i < resultado.filas; i++)
			for (int j = 0; j < resultado.columnas; j++)
				for (int k = 0; k < columnas; k++)
					resultado.matriz[i][j] += matriz[i][k] * otra.matriz[k][j];
		return resultado;
	}

	public MatrizMath multiplicar(double n) {
		MatrizMath resultado = new MatrizMath(filas, columnas);
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				resultado.matriz[i][j] = matriz[i][j] * n;
		return resultado;
	}

	public double normaUno() {
		double[] norma = new double[columnas];
		for (int i = 0; i < columnas; i++)
			for (int j = 0; j < filas; j++)
				norma[i] += Math.abs(matriz[j][i]);
		Arrays.sort(norma);
		return norma[columnas - 1];
	}

	public double normaInfinito() {
		double[] norma = new double[filas];
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				norma[i] += Math.abs(matriz[i][j]);
		Arrays.sort(norma);
		return norma[filas - 1];
	}

	public double normaDos() {
		double norma = 0.0;
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				norma = Math.abs(matriz[i][j]);
		return norma;
	}

	public static MatrizMath identidad(int dimension) {
		MatrizMath ident = new MatrizMath(dimension, dimension);
		for (int i = 0; i < dimension; i++)
			ident.matriz[i][i] = 1.0;
		return ident;
	}


	
	public MatrizMath inversa() throws DistDimException {

		MatrizMath aumentada = this.ampliar();
		MatrizMath inversa = new MatrizMath(filas, columnas);

		//Triangulacion inferior
		for (int i = 0; i < columnas; i++) {
			if(aumentada.matriz[i][i] == 0) 
				aumentada.intercambiarFilas(i);
			aumentada.multiplicarFila(i);
			aumentada.restarFilasHaciaAbajo(i);
		}

		//Triangulacion superior
		for (int i = columnas - 1; i >= 0; i--) 
			aumentada.restarFilasHaciaArriba(i);

		//Obtener solo la inversa de la aumentada
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				inversa.matriz[i][j] = aumentada.matriz[i][columnas + j];

		return inversa;
	}
	
	public String toString() {
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('.');
		StringBuilder matriz = new StringBuilder();
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				matriz.append(this.matriz[i][j]);
				matriz.append("  ");
			}
			matriz.append("\n");
		}
		return matriz.toString();
	}

	public Double[][] getMatriz() {
		return matriz;
	}
	
	public Integer getFilas() {
		return filas;
	}
	
	private MatrizMath ampliar() {
		MatrizMath aumentada = new MatrizMath(filas, columnas*2);
		MatrizMath identidad = identidad(filas);
		for (int i = 0; i < aumentada.filas; i++)
			for (int j = 0; j < columnas; j++) {
				aumentada.matriz[i][j] = this.matriz[i][j];
				aumentada.matriz[i][j + columnas] = identidad.matriz[i][j];
			}
		return aumentada;
	}
	
	private void intercambiarFilas(int fila) throws DistDimException {
		for(int actual = fila; matriz[actual][fila] == 0 && actual + 1 < columnas; actual++) {
			if (matriz[actual + 1][fila] != 0) {
				for (int j = 0; j < columnas; j++) {
					Double aux = matriz[actual][j];
					matriz[actual][j] = matriz[fila + 1][j];
					matriz[fila + 1][j] = aux;
				}
				break;
			}
			if (actual == columnas - 1)
				throw new DistDimException("No tiene solucion, la matriz no tiene inversa");
		}
	}
		
	private void multiplicarFila(int fila) {
		Double escalar = (1/matriz[fila][fila]);
		for (int j = 0; j < columnas; j++)
			matriz[fila][j] *= escalar;
	}
	
	private void restarFilasHaciaAbajo(int fila) {
		for (int i = fila + 1; i < filas; i++) 
			if (matriz[i][fila] != 0) {
				Double escalar = matriz[i][fila];
				for (int j = 0; j < columnas; j++) 
					matriz[i][j] -= matriz[fila][j] * escalar;
			}
	}
	
	private void restarFilasHaciaArriba(int fila) {
		for (int i = fila - 1; i >= 0; i--) 
			if (matriz[i][fila] != 0) {
				Double escalar = matriz[i][fila];
				for (int j = 0; j < columnas; j++)
					matriz[i][j] -= matriz[fila][j] * escalar;
			}
	}

}
