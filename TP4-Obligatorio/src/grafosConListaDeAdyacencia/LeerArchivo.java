package grafosConListaDeAdyacencia;

import java.io.*;

public class LeerArchivo {
	private File archivo=null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	public LeerArchivo(String nombre){
		try{
			archivo = new File(nombre);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String readLine() throws Exception{
		return br.readLine();
	}
	
	public void close() throws Exception {
		if(fr!=null)
			fr.close();
	}
	
}
