package sel;

import java.io.*;
import java.util.Arrays;

public class MatrizMath {
	private Integer fila, columna;
	private Double [][] matriz;   
	
	public MatrizMath(int columna, int fila, Double [][] matriz){
		this.columna = columna;
		this.fila = fila;
		this.matriz = matriz;
	}

	public MatrizMath(int fila, int columna){
		this.fila=fila;
		this.columna=columna;
		setMatriz(new Double[fila][columna]);
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				getMatriz()[i][j]=0.0;
	}
	
	public MatrizMath sumar (MatrizMath otra) throws DistDimException{
		if(this.fila!=otra.fila || this.columna!=otra.columna)
			throw new DistDimException("Las matrices a sumar no tienen la misma dimens�n");
	
		MatrizMath resultado = new MatrizMath(otra.fila,otra.columna);
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				resultado.getMatriz()[i][j]=getMatriz()[i][j] + otra.getMatriz()[i][j];
		return resultado;
	}
	
	public MatrizMath restar (MatrizMath otra) throws DistDimException{
		if(otra==null)
			throw new DistDimException("Otra es NULL!");

		if(this.fila!=otra.fila || this.columna!=otra.columna)
			throw new DistDimException("Las matrices a sumar no tienen la misma dimens�n");
	
		MatrizMath resultado = new MatrizMath(otra.fila,otra.columna);
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				resultado.getMatriz()[i][j]=getMatriz()[i][j] - otra.getMatriz()[i][j];
		return resultado;
	}
	
	public MatrizMath multiplicar (MatrizMath otra) throws DistDimException {
		if(otra==null)
			throw new DistDimException("Otra es NULL!");

		if(this.columna!=otra.fila)
			throw new DistDimException("Dimensiones incorrectas");

		MatrizMath resultado = new MatrizMath(this.fila,otra.columna);
		for(int k=0; k<columna*fila; k++)
			for(int i=0; i<fila; i++)
				for(int j=0; j<columna; j++)
					resultado.getMatriz()[i][j]+=getMatriz()[i][j]*otra.getMatriz()[i][j];
		return resultado;
	}

	public MatrizMath multiplicar (double n) {
		MatrizMath resultado = new MatrizMath(fila,columna);
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				resultado.getMatriz()[i][j] = getMatriz()[i][j] * n;
		return resultado;
	}

	public double norma1 () {
		double [] norma = new double[columna];
		
		for(int i=0; i<columna; i++)
			for(int j=0; j<fila; j++)
			norma[i] += Math.abs(getMatriz()[j][i]);
		Arrays.sort(norma);
		return norma[columna-1];
	}

	public double normaInf () {
		double [] norma = new double[fila];
		
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
			norma[i] += Math.abs(getMatriz()[i][j]);
		Arrays.sort(norma);
		return norma[fila-1];
	}
	
	public double norma2 () {
		double norma = 0.0;
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				norma = Math.abs(getMatriz()[i][j]);
		return norma;
	}
	
	public MatrizMath identidad (int dim){
		MatrizMath ident = new MatrizMath(dim,dim);
		for(int i=0; i< dim; i++)
			ident.matriz[i][i]=1.0;
		return ident;
	}
	
	public Double determinante() throws DistDimException {
		Double det=0.0;
		if(fila!=columna)
			throw new DistDimException("La matriz no es cuadrada" + fila + " - " + columna);
		if(matriz==null)
			throw new DistDimException("Matriz NULL!");
		if(fila==2)
			return (matriz[0][0]*matriz[1][1] - matriz[1][0]*matriz[0][1]);

		for(int i=0; i<columna; i++){
			MatrizMath subMatriz = new MatrizMath(fila-1, columna-1);
//			System.out.println(fila + " - " + columna);
			int m,n=0;
			for(int x=1; x<fila; x++) {
				m = 0;
				for(int y=0; y<columna; y++)
					if(y!=i){
						subMatriz.matriz[n][m] = matriz[x][y];
						m++;
					}
				n++;
			}
			det+=matriz[0][i]*Math.pow(-1, i)*subMatriz.determinante();
		}
		return det;
	}
	
	public Double [][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Double [][] matriz) {
		this.matriz = matriz;
	}

}
