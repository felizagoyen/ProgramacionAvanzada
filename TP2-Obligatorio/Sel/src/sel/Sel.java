package sel;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Sel {

	private MatrizMath matrizCoeficientes;
	private VectorMath vectorResultado;
	private MatrizMath vectorColumnaSolucion;

	public Sel(String nombre) {
		ReaderFile archivo = null;
		try {
			archivo = new ReaderFile(nombre);
			int dimension = Integer.parseInt(archivo.readLine());
			matrizCoeficientes = new MatrizMath(dimension, dimension);
			vectorResultado = new VectorMath(dimension);

			String[] linea;
			for (int i = 0; i < dimension * dimension; i++) {
				linea = archivo.readLine().split(" ");
				matrizCoeficientes.getMatriz()[Integer.parseInt(linea[0])][Integer
						.parseInt(linea[1])] = Double.parseDouble(linea[2]);
			}
			for (int i = 0; i < dimension; i++)
				vectorResultado.getVector()[i] = Double.parseDouble(archivo
						.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (archivo != null)
					archivo.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void resolverSistema() {
		vectorColumnaSolucion = new MatrizMath(vectorResultado.getDimension(),
				1);
		try {
			vectorColumnaSolucion = matrizCoeficientes.inversa()
					.multiplicar(vectorResultado.toMatrizMathColumna());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Double calcularError() {
		MatrizMath resultadoMultiplicacion = matrizCoeficientes.multiplicar(vectorColumnaSolucion);
		double error = vectorResultado.toMatrizMathColumna().restar(resultadoMultiplicacion).normaDos();
		return error;
	}
	
	public void guardarSolucion(String nombre) {
		CreateFile archivo = null;

		try {
			archivo = new CreateFile(nombre);
			archivo.writeLine(vectorColumnaSolucion.getFilas().toString());
			for (int i = 0; i < vectorColumnaSolucion.getFilas(); i++) {
				archivo.writeLine(vectorColumnaSolucion.getMatriz()[i][0].toString());
			}
			archivo.writeLine("");
			archivo.writeLine(calcularError().toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (archivo != null)
					archivo.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Sel sel = new Sel("sel.in");

		Calendar tIni = new GregorianCalendar();
		
		sel.resolverSistema();
		
		Calendar tFin = new GregorianCalendar();
		
		long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();

		System.out.println(Math.round(diff / 1000));
		
		sel.guardarSolucion("sel.out");
	}
}