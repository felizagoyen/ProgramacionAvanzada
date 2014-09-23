package sel;
import java.io.*;
import java.util.Arrays;


/** 
* Vector Matem�tico
* @author sarasa  
*/

public class VectorMath {

	private Integer tam;
	private Double [] vec;
	
	/**
	 * Constructor por defecto 
	 */
	public VectorMath(){
		tam = 0;
		vec = null;
	}
	
	public VectorMath(int tam){
		this.tam = tam;
		vec = new Double [tam];
	}

	public VectorMath(String nombre){
		File archivo = new File(nombre);
		FileReader fr = null;
		BufferedReader br = null;
		try{
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			tam = Integer.parseInt(br.readLine());
			vec = new Double [tam];
		
			for(int i = 0; i < tam; i++)
				vec[i] = Double.parseDouble(br.readLine());
		} catch(Exception e){
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
	
	public Double[] getVec(){
		return vec;
	}

	public VectorMath sumar(VectorMath vec) throws DistDimException{
		if (tam != vec.tam)
			throw new DistDimException(" Distinta Dimension ");
		 
		VectorMath aux = new VectorMath(tam);
		for (int i=0; i<tam; i++)
			aux.vec[i]=this.vec[i]+vec.vec[i];
		return aux;
	}
	
	public VectorMath restar(VectorMath vec) throws DistDimException{
		if (tam != vec.tam)
			throw new DistDimException(" Distinta Dimension ");
		 
		VectorMath aux = new VectorMath(tam);
		for (int i=0; i<tam; i++)
			aux.vec[i]=this.vec[i]-vec.vec[i];
		return aux;
	}
	
	public VectorMath multiplicar(Double valor) {
		VectorMath aux = new VectorMath(tam);
		for (int i=0; i<tam; i++)
			aux.vec[i]=this.vec[i]*valor;
		return aux;
	}
	
	public Double multiplicar (VectorMath vec) throws DistDimException{
		if (tam != vec.tam)
			throw new DistDimException(" Distinta Dimension ");
		 
		Double aux=0.0;
		for (int i=0; i<tam; i++)
			aux+=this.vec[i]*vec.vec[i];
		return aux;
	}
	
	public Double norma1 (){
		Double norma =0.0;
		for(int i=0; i<this.tam; i++)
			norma+=Math.abs(this.vec[i]);
		return norma;
	}
	
	public Double norma2 (){
		Double norma =0.0;
		for(int i=0; i<this.tam; i++)
			norma+=this.vec[i]*this.vec[i];
		return Math.sqrt(norma);
	}
	
	public Double normaInf (){
		VectorMath v = new VectorMath(tam);
		for(int i=0; i<this.tam; i++)
			v.vec[i] = Math.abs(vec[i]); 
		Arrays.sort(v.vec); 
		return v.vec[this.tam-1];
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
		if (tam == null) {
			if (other.tam != null)
				return false;
		} else if (!tam.equals(other.tam))
			return false;
		if (!Arrays.equals(vec, other.vec))
			return false;
		return true;
	}

	public String toString(){
		StringBuilder cad = new StringBuilder("["); //Recibe el string inicial
		for(int i = 0; i < tam; i++) {
			cad.append(vec[i]); //Agrega al final
			if(i!=tam-1)
				cad.append(", ");
		}
		cad.append("]");
		return cad.toString();
	}
	
	public MatrizMath toMatrizMathColumna (){
		MatrizMath resultado = new MatrizMath(tam,1);
		
		for(int i=0; i<tam; i++)
			resultado.getMatriz()[i][0]=vec[i];
	
		return resultado;
	}
	
	/**
	 * Metodo de prueba
	 * @author sarasa
	 * @param a Valor numerico
	 * @param b Cadena
	 * @return Flag de Error
	 */
	public static boolean print (int a, String b){
		return false;		
	}
	
	
}



