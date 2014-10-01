package sel;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Sel {

	private MatrizMath matrizCoeficientes;
	private VectorMath vectorResultado;
	private VectorMath vectorColumnaSolucion;

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

	public boolean resolverSistema() {
		vectorColumnaSolucion = new VectorMath(vectorResultado.getDimension());
		try {
			vectorColumnaSolucion = matrizCoeficientes.inversa().multiplicar(
					vectorResultado);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Double calcularError() {
		VectorMath resultadoMultiplicacion = matrizCoeficientes.multiplicar(vectorColumnaSolucion);
		double error = vectorResultado.restar(resultadoMultiplicacion).norma2();
		return error;
	}

	public void guardarSolucion(String nombre, boolean marca) {
		CreateFile archivo = null;

		try {
			archivo = new CreateFile(nombre);
			if (marca == false)
				archivo.writeLine("No se pudo resolver el sistema de ecuaciones.");
			else {
				archivo.writeLine(vectorColumnaSolucion.getDimension().toString());
				for (int i = 0; i < vectorColumnaSolucion.getDimension(); i++) {
					archivo.writeLine(vectorColumnaSolucion.getVector()[i].toString());
				}
				archivo.writeLine("");
				archivo.writeLine(calcularError().toString());
			}
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
		
		boolean marca;
		
		Calendar tIni = new GregorianCalendar();
		
		marca = sel.resolverSistema();
		
		Calendar tFin = new GregorianCalendar();
		
		long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();

		System.out.println(diff / 1000);

		sel.guardarSolucion("sel.out", marca);
	}
}