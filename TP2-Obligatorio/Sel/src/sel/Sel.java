package sel;

public class Sel {
	private MatrizMath matriz;
	private VectorMath vector;
	private VectorMath resutlado;
	
	public Sel(String ruta) {
		ReaderFile file = null;
		try {
			file = new ReaderFile(ruta);
			int dimension = Integer.parseInt(file.readLine());
			String [] linea;
			Double [][] matriz = new Double[dimension][dimension];

			for(int i=0; i<dimension*dimension; i++){
				linea = file.readLine().split(" ");
				System.out.println(linea[0] + " " + linea[1] + " " + linea[2]);
				matriz[Integer.parseInt(linea[0])]
						   [Integer.parseInt(linea[1])]
								   = Double.parseDouble(linea[2]);		
			}
			this.matriz = new MatrizMath(dimension,dimension,matriz);
			
			Double [] vector = new Double[dimension];
			for(int i = 0; i < dimension; i++)
				vector[i] = Double.parseDouble(file.readLine());
			this.vector = new VectorMath(dimension, vector);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(file != null)
					file.close();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
