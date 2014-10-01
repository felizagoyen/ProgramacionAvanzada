package sel;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Sel {

	private MatrizMath matrizCoeficientes;
	private VectorMath vectorTerminosIndependientes;
	private VectorMath vectorIncognitas;

	public Sel(String nombre) {
		ReaderFile archivo = null;
		try {
			archivo = new ReaderFile(nombre);
			int dimension = Integer.parseInt(archivo.readLine());
			matrizCoeficientes = new MatrizMath(dimension, dimension);
			vectorTerminosIndependientes = new VectorMath(dimension);

			String[] linea;
			for (int i = 0; i < dimension * dimension; i++) {
				linea = archivo.readLine().split(" ");
				matrizCoeficientes.getMatriz()[Integer.parseInt(linea[0])][Integer.parseInt(linea[1])] = Double.parseDouble(linea[2]);
			}
			for (int i = 0; i < dimension; i++)
				vectorTerminosIndependientes.getVector()[i] = Double.parseDouble(archivo.readLine());

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
		vectorIncognitas = new VectorMath(vectorTerminosIndependientes.getDimension());
		try {
			vectorIncognitas = matrizCoeficientes.inversa().multiplicar(vectorTerminosIndependientes);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Double calcularError() {
		VectorMath resultadoMultiplicacion = matrizCoeficientes.multiplicar(vectorIncognitas);
		double error = vectorTerminosIndependientes.restar(resultadoMultiplicacion).normaDos();
		return error;
	}

	public void generarArchivoSalida(String nombre, boolean marca) {
		CreateFile archivo = null;

		try {
			archivo = new CreateFile(nombre);
			if (marca == false)
				archivo.writeLine("No se pudo resolver el sistema de ecuaciones.");
			else {
				archivo.writeLine(vectorIncognitas.getDimension().toString());
				for (int i = 0; i < vectorIncognitas.getDimension(); i++) {
					archivo.writeLine(vectorIncognitas.getVector()[i].toString());
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

		sel.generarArchivoSalida("sel.out", marca);
	}
}