package sel;

import java.io.*;
import java.util.Arrays;

public class MatrizMath {
	private Integer fila, columna;
	private Double [][] matriz;   
	
	public MatrizMath(){
		columna=0;
		fila=0;
		setMatriz(null);
	}
	
	public MatrizMath(int fila, int columna){
		this.fila=fila;
		this.columna=columna;
		setMatriz(new Double[fila][columna]);
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				getMatriz()[i][j]=0.0;
	}
	
	public MatrizMath(String nombre){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			archivo = new File(nombre);
			fr=new FileReader(archivo);
			br=new BufferedReader(fr);
			String [] linea = br.readLine().split(" ");
			fila = Integer.parseInt(linea[0]);
			columna = Integer.parseInt(linea[1]);
			matriz = new Double[fila][columna];
			System.out.println(fila + " - " + columna);
			for(int i=0; i<fila*columna; i++){
				linea = br.readLine().split(" ");
				System.out.println(linea[0] + " " + linea[1] + " " + linea[2]);
				getMatriz()[Integer.parseInt(linea[0])]
						   [Integer.parseInt(linea[1])]
								   = Double.parseDouble(linea[2]);		
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fr!=null)
					fr.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
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
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				for(int k=0; k<fila; k++)
					resultado.getMatriz()[i][j]+=getMatriz()[i][k]*otra.getMatriz()[k][j];
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
	
	public Double frobenius(){
		Double norma=0.0;
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				norma+=Math.pow(matriz[i][j], 2);
		return Math.sqrt(norma);
	}
	
	public MatrizMath identidad (int dim){
		MatrizMath ident = new MatrizMath(dim,dim);
		for(int i=0; i< dim; i++)
			ident.matriz[i][i]=1.0;
		return ident;
	}
	
	private MatrizMath subMatriz(Integer i, Integer j){
		MatrizMath subMat = new MatrizMath(fila-1,columna-1);
		int m=0;
		for(int x=0; x<fila; x++){
			int n=0;
			if(i!=x){
				for(int y=0; y<columna; y++){
					 if(j!=y){
						subMat.matriz[m][n]=matriz[x][y];
						n++;
					}
				}
				m++;
			}
		}
		return subMat;
	}
	
	public Double determinante() throws DistDimException {
		Double det=0.0;
		if(fila!=columna)
			throw new DistDimException("La matriz no es cuadrada" + fila + " - " + columna);
		if(matriz==null)
			throw new DistDimException("Matriz NULL!");
		if(fila==2)
			return (matriz[0][0]*matriz[1][1] - matriz[1][0]*matriz[0][1]);

		for(int i=0; i<columna; i++)
			det+=matriz[0][i]*Math.pow(-1, i)*this.subMatriz(0, i).determinante();
		return det;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MatrizMath m = new MatrizMath("matriz.in");
		System.out.println(m.normaInf());
	}
	
	

	public Double [][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Double [][] matriz) {
		this.matriz = matriz;
	}

}
