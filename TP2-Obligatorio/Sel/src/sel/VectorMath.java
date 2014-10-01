package sel;

import java.util.Arrays;

public class VectorMath {

	private Integer dimension;
	private Double[] vector;

	public VectorMath() {
		dimension = 0;
		vector = null;
	}

	public VectorMath(int dimension, Double[] vector) {
		this.dimension = dimension;
		this.vector = vector;
	}

	public VectorMath(int dimension) {
		this.dimension = dimension;
		this.vector = new Double[dimension];
		for(int i = 0; i < dimension; i++)
			vector[i] = 0.0;
	}

	public Double[] getVector() {
		return vector;
	}

	public VectorMath sumar(VectorMath vector) throws DistDimException {
		if (dimension != vector.dimension)
			throw new DistDimException(" Distinta Dimension ");

		VectorMath vectorResultado = new VectorMath(dimension);
		for (int i = 0; i < dimension; i++)
			vectorResultado.vector[i] = this.vector[i] + vector.vector[i];
		return vectorResultado;
	}

	public VectorMath restar(VectorMath vector) throws DistDimException {
		if (dimension != vector.dimension)
			throw new DistDimException(" Distinta Dimension ");

		VectorMath vectorResultado = new VectorMath(dimension);
		for (int i = 0; i < dimension; i++)
			vectorResultado.vector[i] = this.vector[i] - vector.vector[i];
		return vectorResultado;
	}

	public VectorMath multiplicar(Double valor) {
		VectorMath vectorResultado = new VectorMath(dimension);
		for (int i = 0; i < dimension; i++)
			vectorResultado.vector[i] = this.vector[i] * valor;
		return vectorResultado;
	}

	public Double multiplicar(VectorMath vector) throws DistDimException {
		if (dimension != vector.dimension)
			throw new DistDimException(" Distinta Dimension ");

		Double resultado = 0.0;
		for (int i = 0; i < dimension; i++)
			resultado += this.vector[i] * vector.vector[i];
		return resultado;
	}

	public VectorMath multiplicar(MatrizMath matriz) throws DistDimException {
		if (dimension != matriz.getFilas())
			throw new DistDimException(" Distinta Dimension ");

		VectorMath resultado = new VectorMath(dimension);
		for(int i = 0; i < matriz.getColumnas(); i++)
			for (int j = 0; j < dimension; j++)
				resultado.vector[i] += this.vector[j] * matriz.getMatriz()[i][j];
		return resultado;
	}

	public Double norma1() {
		Double norma = 0.0;
		for (int i = 0; i < this.dimension; i++)
			norma += Math.abs(this.vector[i]);
		return norma;
	}

	public Double norma2() {
		Double norma = 0.0;
		for (int i = 0; i < this.dimension; i++)
			norma += this.vector[i] * this.vector[i];
		return Math.sqrt(norma);
	}

	public Double normaInf() {
		VectorMath vector = new VectorMath(dimension);
		for (int i = 0; i < this.dimension; i++)
			vector.vector[i] = Math.abs(this.vector[i]);
		Arrays.sort(vector.vector);
		return vector.vector[this.dimension - 1];
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VectorMath other = (VectorMath) obj;
		if (dimension == null) {
			if (other.dimension != null)
				return false;
		} else if (!dimension.equals(other.dimension))
			return false;
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder cadena = new StringBuilder("[");
		for (int i = 0; i < dimension; i++) {
			cadena.append(vector[i]);
			if (i != dimension - 1)
				cadena.append(", ");
		}
		cadena.append("]");
		return cadena.toString();
	}

	//borrar
	public MatrizMath toMatrizMathColumna() {
		MatrizMath resultado = new MatrizMath(dimension, 1);
		for (int i = 0; i < dimension; i++)
			resultado.getMatriz()[i][0] = vector[i];
		return resultado;
	}
	
	public Integer getDimension() {
		return dimension;
	}
}
