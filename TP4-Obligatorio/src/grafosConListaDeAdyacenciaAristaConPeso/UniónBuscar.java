package grafosConListaDeAdyacenciaAristaConPeso;

import java.util.ArrayList;

public class Uni�nBuscar {
	 
	ArrayList<Integer> padre = new ArrayList<Integer>(); 


	public Uni�nBuscar( int n ) {
		for( int i = 0 ; i < n ; i++ ){
	    	padre.add(i);
	    }
	}

	//M�todo para encontrar la raiz del v�rtice actual X
	int Find( int x ){
	    if( x == padre.get(x) ){          
	        return x;                   
	    }
	    else{ 
	    	padre.set(x, Find( padre.get(x) )); //Compresion de caminos
	    	return padre.get(x);
	    }
	}

	//M�todo para unir 2 componentes conexas
	void Union( int x , int y ){
	    int xRoot = Find( x );   
	    int yRoot = Find( y );    
	    padre.set(xRoot, yRoot);  
	}


}
