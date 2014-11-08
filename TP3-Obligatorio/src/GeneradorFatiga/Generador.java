package GeneradorFatiga;

import java.io.File;
import java.io.PrintWriter;

public class Generador { 
	public static void main(String[] args) {
		String[] operaciones = {"+", "-", "*", "^", "/"};
		File archivo = null;
		PrintWriter pw = null;
		try{
			archivo = new File("funcion.in");
			pw = new PrintWriter(archivo);
			pw.print("y" + " ");
			pw.print(Math.round(Math.random()*100) + " ");
			pw.print(operaciones[(int)Math.round(Math.random()*4)] + " ");
			pw.print(Math.round(Math.random()*100) + " ");
			pw.print("ln ");
			pw.print("x" + " ");
			pw.print(operaciones[(int)Math.round(Math.random()*4)] + " ");
			pw.print(Math.round(Math.random()*100) + " ");
			pw.print("z" + " ");
			pw.print(operaciones[(int)Math.round(Math.random()*4)] + " ");
			pw.print(operaciones[(int)Math.round(Math.random()*4)] + " ");
			pw.print(operaciones[(int)Math.round(Math.random()*4)]);
			pw.println();
			pw.println("3 1000");
			for(int i = 0; i < 1000; i++)
				pw.println(Math.round(Math.random()*100) + " " + Math.round(Math.random()*100) + " " + Math.round(Math.random()*100));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pw.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}

	}

}
