import java.util.Scanner;
import java.io.*;

public class GenerarSel {

	public static void generarSelIn (){
		File archivo = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			archivo = new File("sel.in");
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			int dim;
			Scanner in = new Scanner(System.in);
			System.out.println("Ingrese la cantidad de incognitas del sistema de ecuaciones que quiere generar:");
			dim = in.nextInt();
			in.close();
			pw.println(dim);
			
			for (int i=0; i<dim; i++){
				for (int j=0; j<dim; j++)
					pw.println(i + " " + j + " " + Math.round(Math.random()*100));
			}
			
			for (int i=0; i<dim; i++)
				pw.println(Math.round(Math.random()*100));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pw.close();
				System.out.println("El archivo sel.in ha sido generado correctamente.");
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		generarSelIn();
	}

}
