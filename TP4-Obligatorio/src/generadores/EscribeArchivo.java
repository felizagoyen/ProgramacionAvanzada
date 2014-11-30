package generadores;

import java.io.*;

public class EscribeArchivo {
	FileWriter archivo=null;
	PrintWriter pw=null;
	
	public EscribeArchivo(String nombre){
		try{
			archivo=new FileWriter(nombre);
			pw = new PrintWriter(archivo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void writeLine (String cadena){
		pw.println(cadena);
	}
	
	public void cerrarArchivo(){
		try{
			if(pw!=null)
				pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
