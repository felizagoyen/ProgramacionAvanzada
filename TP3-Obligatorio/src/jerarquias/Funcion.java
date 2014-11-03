package jerarquias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Funcion {

	private Stack<Expresion> pila = new Stack<Expresion>();
	private ArrayList<Double> resultados = new ArrayList<Double>();
	private Integer cantidadVariables;
	private Punto punto = Punto.getPunto();
	private ArrayList<Double> valoresX = new ArrayList<Double>();
	private ArrayList<Double> valoresY = new ArrayList<Double>();
	private ArrayList<Double> valoresZ = new ArrayList<Double>();
	
	public Funcion(String path) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String [] linea = null;
		
		try {
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			linea = br.readLine().split(" ");
			for(String cadaExpresion: linea)
				pila.push(this.getExpresion(cadaExpresion)); 
		
			linea = br.readLine().split(" ");
			cantidadVariables = Integer.parseInt(linea[0]);
			int cantidadValores = Integer.parseInt(linea[1]);
			
			for(int x = 0; x < cantidadValores; x++) {
				linea = br.readLine().split(" ");
				valoresX.add(Double.parseDouble(linea[0]));
				if(cantidadVariables >= 2) valoresY.add(Double.parseDouble(linea[1]));
				if(cantidadVariables == 3) valoresZ.add(Double.parseDouble(linea[2]));
			}
			
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void resolver() {
		for(int x = 0; x < valoresX.size(); x++) {
			punto.setX(valoresX.get(x));
			if(cantidadVariables >= 2) punto.setY(valoresY.get(x));
			if(cantidadVariables == 3) punto.setZ(valoresZ.get(x));
			resultados.add(pila.peek().resolver());
		}
	}
	
	public void generarSalida(String path) {
		File archivo = null;
		PrintWriter pw = null;
		
		try {
			archivo = new File(path);
			pw = new PrintWriter(archivo);
			pw.println(resultados.size());
			for(Double cadaResultado: resultados)
				pw.println(cadaResultado);
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(pw != null)
					pw.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private Expresion getExpresion(String expresion) {
		if(expresion.equals("+")) return new Suma(pila.pop(), pila.pop());
		if(expresion.equals("-")) return new Resta(pila.pop(), pila.pop());
		if(expresion.equals("*")) return new Multiplicacion(pila.pop(), pila.pop());
		if(expresion.equals("/")) return new Division(pila.pop(), pila.pop());
		if(expresion.equals("^")) return new Potencia(pila.pop(), pila.pop());
		
		if(expresion.equals("log")) return new LogaritmoNatural(pila.pop());

		if(expresion.toLowerCase().equals("x")) return new Variable("x");
		if(expresion.toLowerCase().equals("y")) return new Variable("y");
		if(expresion.toLowerCase().equals("z")) return new Variable("z");
		
		return new Constante(Double.parseDouble(expresion));
	}
	
	
	public static void main(String[] args) {
		
		Funcion funcion = new Funcion("funcion.in");
		
		funcion.resolver();
		
		funcion.generarSalida("funcion.out");

	}

}
