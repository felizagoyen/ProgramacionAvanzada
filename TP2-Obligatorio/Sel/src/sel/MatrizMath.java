package sel;

import java.io.*;
import java.util.Arrays;

//import matrizMath.MatrizMath;

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
			for(int i=0; i<fila*columna; i++){
				linea = br.readLine().split(" ");
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
		for(int i=0; i<resultado.fila; i++){
			for(int j=0; j<resultado.columna; j++){
				for(int k=0; k<columna; k++){
					resultado.getMatriz()[i][j]+=getMatriz()[i][k]*otra.getMatriz()[k][j];
				}
			}
		}
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
	
	public MatrizMath transpuesta(){
		MatrizMath resultado = new MatrizMath(columna, fila);
		for(int i=0; i<fila; i++)
		   	for(int j=0; j<columna; j++)
		      	resultado.matriz[j][i] = matriz[i][j];
		return resultado;
	}
	
	public MatrizMath cofactor(){
		MatrizMath resultado = new MatrizMath(fila, columna);
		for(int i=0; i<fila; i++)
		   	for(int j=0; j<columna; j++)
		      	resultado.matriz[i][j] = Math.pow(-1, i+j)*this.subMatriz(i, j).determinante();
		return resultado;
	}
	
	
	public MatrizMath adjunta(){
		return this.cofactor().transpuesta();
	}
	
	public MatrizMath inversa(){
		return this.adjunta().multiplicar(1/this.determinante());
	}
	
	
	
	public MatrizMath inversaGaussiana(){
		MatrizMath inv = new MatrizMath().identidad(fila);
		//1-localizar la columna de la izq que no conste completamente de ceros
		for(int j=0; j<columna; j++){
			for(int i=0; i<fila; i++){
				if(matriz[i][j]!=0){
					//3-multiplicar renglon por 1/a 
					Double escalar = matriz[i][j];
					for(int k=j; k<columna; k++){
						matriz[i][k]=matriz[i][k]*escalar;
						inv.matriz[i][k]=inv.matriz[i][k]*escalar;
					}
				}
				else{
					//2-intercambiar renglones en caso de ser necesario
					
				}
				//4-reducir a cero los terminos de esa columna
				for(int y=i+1; y<fila; y++){
					for(int x=j; x<columna; x++){
						if(matriz[y][x]!=0){
							matriz[y][x]*=(1-matriz[i][x]);
							inv.matriz[y][x]*=(1-matriz[i][x]);
						}
					}
				}
			}
		}
		
		
		
		
		//5-aplicar lo mismo a la submatrix restante desde el paso 1
		
		//6-Empezando por el ultimo renglon diferente de ceroy trabajando hacia arriba, reducir a ceros.
		
		
		return inv;
	}
	
	public String toString(){
		StringBuilder cad = new StringBuilder("[");
		for(int i=0; i<fila; i++){
			cad.append("[");
		   	for(int j=0; j<columna; j++){
		   		cad.append(matriz[i][j]);
		   		if(j!=columna-1)
		   			cad.append(", ");
		   	}
		   	cad.append("]");
		   	if(i!=fila-1)
	   			cad.append(", ");
		}
		cad.append("]");
		return cad.toString();
	}
	
	

	public Double [][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Double [][] matriz) {
		this.matriz = matriz;
	}

}
