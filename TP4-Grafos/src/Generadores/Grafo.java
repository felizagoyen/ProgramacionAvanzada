package Generadores;


import java.io.*;

public class Grafo {

	private final static int inf = 100000;
	private int cantNodos;
	private int cantAristas;
	private int[][] matrizAdy;
	private double porcentajeAdy;
	private int gradoMax;
	private int gradoMin;
	
	
	public Grafo (String ruta){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] datos = null;
		
		try{
			archivo = new File (ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
			
			datos = br.readLine().split(" ");
			
			cantNodos = Integer.parseInt(datos[0]);
			cantAristas = Integer.parseInt(datos[1]);
			porcentajeAdy = Integer.parseInt(datos[2]);
			gradoMax = Integer.parseInt(datos[3]);
			gradoMin = Integer.parseInt(datos[4]);
			
			matrizAdy = new int[cantNodos][cantNodos];
			for(int i = 0; i < cantNodos; i++)
				for(int j = 0; j < cantNodos; j++){
					matrizAdy[i][j] = inf;
				}
			
			for (int i = 0; i < (cantNodos* (cantNodos-1))/2; i++){
				datos = br.readLine().split(" ");
				matrizAdy[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])] = Integer.parseInt(datos[2]);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				fr.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public Grafo (){
		matrizAdy = null;
	}

	
	
	public void grafoDadoNYPorcArista (int cantidad, int porcentaje){
		cantNodos = cantidad;
		porcentajeAdy = porcentaje;
		cantAristas = (int) ((cantNodos * (cantNodos-1) /2) * (porcentajeAdy/100));
		matrizAdy = new int[cantNodos][cantNodos];
		
		for (int i = 0; i < cantNodos;i++)
			for (int  j = i; j < cantNodos; j++){
				matrizAdy[i][j] = inf;
				matrizAdy[j][i] = inf;
			}
		
		int aristasAux = cantAristas;
		int filas, columnas, rand;
		
		while (aristasAux > 0){
			filas = (int)(Math.random()*cantNodos);
			columnas = (int)(Math.random()*cantNodos);
			rand = (int)(Math.random()*100);
			
			if(filas != columnas && matrizAdy[filas][columnas] == inf){
			matrizAdy[filas][columnas] = rand;
			matrizAdy[columnas][filas] = rand;
			aristasAux--;
			}
		}
		
		gradoMax = buscarGradoMax();
		gradoMin = buscarGradoMin();
		
	}
	
	public void grafoDadoNYProbArista (int cantidad, double prob){ //Prob va de 0 a 1.
	
		cantNodos = cantidad;
		cantAristas = 0;
		matrizAdy = new int[cantNodos][cantNodos];
		
		for (int i = 0; i < cantNodos; i++)
			for (int j = i; j < cantNodos; j++){
				matrizAdy[i][j] = inf;
				matrizAdy[j][i] = inf;
			}
		
		int costo = inf;
		double rand; 
		for (int i = 0; i < cantNodos; i++)
			for (int j = i+1 ; j < cantNodos; j++){
				rand = Math.random();
				if (rand < prob){
					costo = (int)(Math.random()*100);
					matrizAdy[i][j] = costo;
					matrizAdy[j][i] = costo;
					cantAristas++;
				}
			}
		int aristasMax = (int) ((cantNodos * (cantNodos - 1))/2);
		porcentajeAdy = (cantAristas*100)/aristasMax;
		gradoMax = buscarGradoMax();
		gradoMin = buscarGradoMin();
		
	}
	
	
	public void grafoRegularDadoGradoYN(int cantidad, int grado){
		
		cantNodos = cantidad;
		porcentajeAdy = ((double)grado/(cantNodos-1))*100;
		cantAristas = (cantNodos*grado)/2;
		matrizAdy = null;
		
		if((cantNodos % 2)==0 || (grado % 2)==0) {
			matrizAdy = new int [cantNodos][cantNodos];
			
			for (int i = 0; i < cantNodos; i++)
				for (int j = i; j < cantNodos; j++){
					matrizAdy[i][j] = inf;
					matrizAdy[j][i] = inf;
				}
			
			int j, rand = inf;

			int saltoMax = (grado/2)-1;
			for(int salto = 0; salto <= saltoMax; salto++){ 
				for(int i = 0; i < cantNodos; i++){ 
					j = (i+1 +salto) % cantNodos;
					rand = (int)((Math.random()*99)+1);
					matrizAdy[i][j] = rand;
					matrizAdy[j][i] = rand;
					}			
				}	

			
			if((grado % 2) != 0){ 
				for(int i=0; i<cantNodos/2 ;i++){
					j = i+cantNodos/2; 
					matrizAdy [i][j] = 1;
					matrizAdy [j][i] = 1;
				}
			}
		}
		
		gradoMax = grado;
		gradoMin = grado;
		
		if(matrizAdy==null){
			System.out.println("No se puede generar un grafo regular con esas caracteristicas");
			cantAristas = 0;
		}
	}
	
	public void grafoRegularDadoPorcYN (int cantidad, int porcentaje){
		cantNodos = cantidad;
		porcentajeAdy = porcentaje;
		
		int grado = (int)((porcentajeAdy*(cantNodos-1))/100);
		
		gradoMax = grado;
		gradoMin = grado;
		
		cantNodos = cantidad;
		cantAristas = (cantNodos*grado)/2;
		matrizAdy = null;
		
		if((cantNodos % 2)==0 || (grado % 2)==0) {
			matrizAdy = new int [cantNodos][cantNodos];
			
			for (int i = 0; i < cantNodos; i++)
				for (int j = i; j < cantNodos; j++){
					matrizAdy[i][j] = inf;
					matrizAdy[j][i] = inf;
				}
			
			int j, rand = inf;
			
			int saltoMax = (grado/2)-1;
			for(int salto = 0; salto <= saltoMax; salto++){ 
				for(int i = 0; i < cantNodos; i++){ 
					j = (i+1 +salto) % cantNodos;
					rand = (int)((Math.random()*99) +1);
					matrizAdy[i][j] = rand;
					matrizAdy[j][i] = rand;
					}			
				}	

			
			if((grado % 2) != 0){ 
				for(int i=0; i<cantNodos/2 ;i++){
					j = i+cantNodos/2;
					rand = (int)((Math.random()*99) +1);
					matrizAdy [i][j] = rand;
					matrizAdy [j][i] = rand;
				}
			}
		}	
		
		if(matrizAdy==null){
			System.out.println("No se puede generar un grafo regular con esas caracteristicas");
			cantAristas = 0;
		}		
	}
	
	public void grafoNPartito (int cantidad, int n){
		
		cantNodos = cantidad;
		matrizAdy = new int [cantNodos][cantNodos];
		cantAristas = 0;
		
		for (int i = 0; i < cantNodos;i++)
			for (int  j = i; j < cantNodos; j++){
				matrizAdy[i][j] = inf;
				matrizAdy[j][i] = inf;
			}
		
		int[] cantidades = new int [n];
		
		cantidades = generarTamañoSubGrafos(cantNodos, n);
		
		int[] posiciones = new int [n+1];
		posiciones[0] = 0;
		
		for (int i = 1; i < n+1; i++)
			posiciones[i] = posiciones [i-1] + cantidades[i-1];	
		
		int rand = inf;
		
		for (int i = 0; i < n; i++){
			for (int j = posiciones[i]; j < posiciones [i+1]; j++){
				for (int k = j; k < posiciones[i+1]; k++){
					
					if(Math.random() < 0.7){
					rand = (int)((Math.random()*99)+1);
					matrizAdy [j][k] = rand;
					matrizAdy [k][j] = rand;
					cantAristas++;
					}
				}
			}
		}
		int aristasMax = (int) ((cantNodos * (cantNodos - 1))/2);
		porcentajeAdy = (cantAristas*100)/aristasMax;
		
		gradoMax = buscarGradoMax();
		gradoMin = buscarGradoMin();
	}
	
	public int buscarGradoMax (){
		int grado, aux;
		
		grado = 0;
		for (int i = 0; i < cantNodos; i++){
			aux = 0;
			for (int j = 0; j < cantNodos; j++)
					if (matrizAdy[i][j] != inf)
						aux ++;
			if (aux > grado)
				grado = aux;
		}
		return grado;
	}


	public int buscarGradoMin (){
		int grado, aux;
		
		grado = inf;
		for (int i = 0; i < cantNodos; i++){
			aux = 0;
			for (int j = 0; j < cantNodos; j++)
					if (matrizAdy[i][j] != inf)
						aux ++;
			if (aux < grado)
				grado = aux;
		}
		return grado;
	}

	
	public void guardarGrafo (String ruta){
		
		File archivo = null;
		PrintWriter pw = null;
	
		try{
			archivo = new File (ruta);
			pw = new PrintWriter (archivo);
			
			porcentajeAdy = (int)porcentajeAdy;
			
			pw.println(cantNodos + " " +  cantAristas + " " + (int)porcentajeAdy + " " + gradoMax + " " + gradoMin);
			for (int i = 0; i < cantNodos; i++)
				for (int j = i+1; j < cantNodos; j++){
					if (matrizAdy [i][j] != 0){
						pw.println(i + " " + j + " " + matrizAdy[i][j]);
					}
				}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				pw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public int[] generarTamañoSubGrafos (int cantNodos, int n){
		
		
		int[] cantidades = new int[n];
		int auxNodos = cantNodos;
		
		for (int i = 0; i < n-1; i++){
			cantidades[i] = (int)(((Math.random()+0.25)*(auxNodos/2)-1)+1);
			auxNodos -= cantidades[i];
		}
		
		cantidades [n-1] = auxNodos;
		
		int suma = 0;

		for (int i = 0; i < n; i++){
			System.out.print(cantidades[i] + " ");
			suma += cantidades[i];
		}
		
		System.out.println(suma);
		
		return cantidades;
	}
	
	public int getCantNodos() {
		return cantNodos;
	}

	public int getCantAristas() {
		return cantAristas;
	}

	public int[][] getMatrizAdy() {
		return matrizAdy;
	}

	public double getPorcentajeAdy() {
		return porcentajeAdy;
	}

	
	
	public static void main(String[] args) {
		Grafo g1 = new Grafo ();
		

//		g1.grafoRegularDadoPorcYN(5, 50);
		g1.grafoDadoNYProbArista(4, 0.60);
		g1.guardarGrafo("grafo2.in");
		System.out.println(g1.cantNodos + " " + g1.cantAristas + " " + (int)g1.porcentajeAdy);

		g1.grafoNPartito(10, 3);
		
		System.out.println(g1.cantNodos + " " + g1.cantAristas + " " + (int)g1.porcentajeAdy + " " + g1.gradoMax + " " + g1.gradoMin);

		
		for (int i = 0; i < g1.cantNodos; i++)
			for (int j = i+1; j < g1.cantNodos; j++)
				if(g1.matrizAdy[i][j] != inf)
				System.out.println(i + " " + j + " " + g1.matrizAdy[i][j]);
					
	}
}

