package sel;

public class Sel {
	private Integer numeroIncognitas;
	private MatrizMath MatrizCoeficientes;
	private VectorMath vectorFactoresIndependientes;
	private VectorMath vectorSolucion;

	public Sel() {
		this(0);
	}

	public Sel(Integer numeroIncognitas) {
		this.numeroIncognitas = numeroIncognitas;
		MatrizCoeficientes = new MatrizMath(numeroIncognitas, numeroIncognitas);
		for (int i = 0; i < numeroIncognitas; i++)
			for (int j = 0; j < numeroIncognitas; j++)
				MatrizCoeficientes.getMatriz()[i][j] = 0.0;
		vectorFactoresIndependientes = new VectorMath(numeroIncognitas);
		for (int i = 0; i < numeroIncognitas; i++) {
			vectorFactoresIndependientes.getVec()[i] = 0.0;
			vectorSolucion.getVec()[i] = 0.0;
		}
	}

	public Sel(String nombre) {
		try {
			ReaderFile archivo = new ReaderFile(nombre);
			numeroIncognitas = Integer.parseInt(archivo.readLine());
			MatrizCoeficientes = new MatrizMath(numeroIncognitas, numeroIncognitas);
			vectorFactoresIndependientes = new VectorMath(numeroIncognitas);
			String[] linea;
			for (int i = 0; i < numeroIncognitas * numeroIncognitas; i++) {
				linea = archivo.readLine().split(" ");
				MatrizCoeficientes.getMatriz()[Integer.parseInt(linea[0])][Integer.parseInt(linea[1])] = Double.parseDouble(linea[2]);
			}
			for (int i = 0; i < numeroIncognitas; i++) 
				vectorFactoresIndependientes.getVec()[i] = Double.parseDouble(archivo.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void calcular() {
		MatrizMath matrizSolucion = MatrizCoeficientes.inversaGaussJordan().multiplicar(vectorFactoresIndependientes.toMatrizMathColumna());
		System.out.println("gaussjordar " + matrizSolucion);
		MatrizMath matrizSolucion1 = MatrizCoeficientes.inversa().multiplicar(vectorFactoresIndependientes.toMatrizMathColumna());
		System.out.println("cofactor " + matrizSolucion1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sel sistemaEcuacionesLineales = new Sel("sel.in");
		sistemaEcuacionesLineales.calcular();
	}

}
