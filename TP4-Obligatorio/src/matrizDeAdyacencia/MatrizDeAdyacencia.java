package matrizDeAdyacencia;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MatrizDeAdyacencia {
	private Integer [][] matriz;
	private Integer numeroDeNodos;
	
	
	public MatrizDeAdyacencia(Integer numeroDeNodos) {
		matriz = new Integer [numeroDeNodos][numeroDeNodos];
		this.numeroDeNodos=numeroDeNodos;
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				this.matriz[i][j]=0;
			}
		}
	}
	
	public MatrizDeAdyacencia(Integer [][] matriz) {
		this.matriz = new Integer [matriz.length][matriz[0].length];
		this.numeroDeNodos=matriz.length;
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				this.matriz[i][j]=matriz[i][j];
			}
		}
	}
	
	public void generarMatriz(){
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				Integer valor = (int)Math.round(Math.random()*100);
				if(valor<50)
					matriz[i][j]=1;
			}
		}
	}
	
	
	public void generadorMatrizSimetrica(){
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=i; j<numeroDeNodos; j++){
				Integer valor = (int)Math.round(Math.random()*100);
				if(valor<50)
					matriz[i][j]=1;
				else
					matriz[i][j]=0;
				matriz[j][i]=matriz[i][j];
			}
		}
	}
	
	
	public Boolean esSimetrica(){
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=i+1; j<numeroDeNodos; j++){
				if(matriz[i][j]!=matriz[j][i])
					return false;
			}
		}
		return true;
	}
	
	
	public Integer[] simetricaToVector(){
		Integer [] vector = new Integer[(numeroDeNodos*numeroDeNodos-numeroDeNodos)/2];
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=i+1; j<numeroDeNodos; j++){
				vector[i*numeroDeNodos+j-(i*i+3*i+2)/2]=matriz[i][j];
			}
		}
		return vector;
	}
	
	public void resume(){
		System.out.println("Numero de nodos: " + numeroDeNodos);
		System.out.println("Grado Maximo: " + this.gradoMax(this.vectorGrados()));
		System.out.println("Grado Minimo: " + this.gradoMin(this.vectorGrados()));
		for(int i=0; i<numeroDeNodos; i++)
			System.out.println("Grado de nodo " + (i+1) + " es: " + this.gradoNodo(i));
		if(this.esSimetrica()){
			System.out.println("Es simetrica");
		}
		else{
			System.out.println("Es NO simetrica");
		}
		System.out.println("El numero de arista es: " + this.numeroDeAristas());
		System.out.println("El numero de bucles es: " + this.numeroDeBucles());
		if(this.esSimple())
			System.out.println("Es Simple");
		else
			System.out.println("Es NO Simple");
	}
	
	public Boolean esSimple(){
		if(this.numeroDeBucles()==0)
			return true;
		else
			return false;
	}
	
	public Integer gradoNodo(Integer nodo){
		return this.vectorGrados()[nodo];
	}
	
	public Integer [] vectorGrados(){
		Integer [] vector = new Integer[numeroDeNodos];
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				if(j==0)
					vector[i]=0;
				if(j!=i)
					vector[i]+=matriz[i][j];
				else
					vector[i]+=matriz[i][j]*2;
			}
		}
		return vector;
	}
	
	public static MatrizDeAdyacencia sucesionGrafica(String entrada){
		String [] args = entrada.split(" ");
		Integer [] sucesion = new Integer[args.length];
		for(int i=0; i< sucesion.length; i++){
			sucesion[i]=Integer.parseInt(args[i]);
			System.out.print(sucesion[i] + " ");
		}
		System.out.println();
		Integer [][] matriz = new Integer [sucesion.length][sucesion.length]; 
		//Arrays.sort(sucesion);
		for(int i=0; i<sucesion.length; i++){
			matriz[i][i]=0;
			for(int j=i+1; j<sucesion.length; j++){
				if(sucesion[j]>0 && sucesion[i]>0){
					matriz[i][j]=1;
					matriz[j][i]=1;
					sucesion[i]--;
					sucesion[j]--;
				}
				else{
					matriz[i][j]=0;
					matriz[j][i]=0;
				}
			}
			for(int k=0; k< sucesion.length; k++){
				System.out.print(sucesion[k] + " ");
			}
			System.out.println();
		}
		return new MatrizDeAdyacencia(matriz);
	}
	
	public static Boolean esSucesionGrafica(String entrada){
		String [] args = entrada.split(" ");
		Integer [] sucesion = new Integer[args.length];
		for(int i=0; i< sucesion.length; i++){
			sucesion[i]=Integer.parseInt(args[i]);
		}
		for(int i=0; i<sucesion.length; i++){
			for(int j=i+1; j<sucesion.length; j++){
				if(sucesion[j]>0 && sucesion[i]>0){
					sucesion[i]--;
					sucesion[j]--;
				}
			}
		}
		Integer sumGrados=0;
		for(int i=0; i< sucesion.length; i++)
			sumGrados+=sucesion[i];
		if(sumGrados>0)
			return false;
		else
			return true;
	}
	
	public Integer numeroDeBucles(){
		Integer bucles=0;
		for(int i=0; i<numeroDeNodos; i++){
			if(matriz[i][i]==1)
				bucles++;
		}
		return bucles;
	}
	
	public Integer gradoMax(Integer [] vector){
		Arrays.sort(vector);
		return vector[vector.length-1];
	}
	
	public Integer gradoMin(Integer [] vector){
		Arrays.sort(vector);
		return vector[0];
	}
	
	public Integer numeroDeAristas(){
		Integer aristas=0;
		Integer [] vector = this.vectorGrados();
		for(int i=0; i<vector.length; i++){
			aristas+=vector[i];
		}
		return aristas/2;
	}
	
	public String toString(){
		StringBuilder salida = new StringBuilder();
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				salida.append(matriz[i][j] + " ");
			}
			salida.append("\n");
		}
		return salida.toString();
	}
	

	public Integer[][] getMatriz() {
		return matriz;
	}

	public void generarPorPorcentajeDeAdyacencia(Integer porcentajeAdyacencia) {
		Integer numeroDeAristasAQuitar = (((int) Math.pow(numeroDeNodos,2)-numeroDeNodos)*(100-porcentajeAdyacencia)/100);
		this.generadorRegular();
		while(numeroDeAristasAQuitar>0){
			Integer i = (int)Math.round(Math.random()*100)%numeroDeNodos;
			Integer j = (int)Math.round(Math.random()*100)%numeroDeNodos;
			if(i!=j){
				matriz[i][j]=0;
				numeroDeAristasAQuitar--;
			}
		}
	}

	public void generarProbabilidadDeArista(Integer porcentajeAdyacencia) {
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				if(i!=j)
					matriz[i][j] = (int)Math.round(Math.random()*100);	
			}
		}
		Integer numeroDeAristasAQuitar = (((int) Math.pow(numeroDeNodos,2)-numeroDeNodos)*(100-porcentajeAdyacencia)/100);
		while(numeroDeAristasAQuitar>0){
			Integer i = (int)Math.round(Math.random()*100)%numeroDeNodos;
			Integer j = (int)Math.round(Math.random()*100)%numeroDeNodos;
			if(i!=j){
				matriz[i][j]=0;
				numeroDeAristasAQuitar--;
			}
		}
	}

	public void generatNPartito(Integer numeroDeParticiones) {
		this.generadorRegular();
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=0; j<numeroDeNodos; j++){
				
			}
		}
		
	}

	public void generadorRegular() {
		for(int i=0; i<numeroDeNodos; i++){
			for(int j=i+1; j<numeroDeNodos; j++){
				matriz[i][j] = 1;
				matriz[j][i] = 1;
			}
		}
	}

	public void generadorRegularPorPorcentajeDeAdyacencia(Integer porcentajeAdyacencia) {
		Integer numeroDeAristasAQuitarPorNodo = (((int) Math.pow(numeroDeNodos,2)-numeroDeNodos)*(100-porcentajeAdyacencia)/100)/numeroDeNodos;
		this.generadorRegular();
		for(int i=0; i<numeroDeNodos; i++){
			Set<Integer> aristasSeleccionadas = new HashSet<Integer>(); 
			while(aristasSeleccionadas.size()<numeroDeAristasAQuitarPorNodo){
				Integer j = (int)Math.round(Math.random()*100)%numeroDeNodos;
				aristasSeleccionadas.add(j);
			}
			for(int j: aristasSeleccionadas)
				matriz[i][j] = 0;
		}
	}

	public Integer getNumeroAristas(Integer i) {
		Integer numeroDeArista=0;
		for(int j=0; j<numeroDeNodos; j++){
			if(matriz[i][j]>0)
				numeroDeArista++;  
		}
		return numeroDeArista;
	}
}
