package grafosConListaDeAdyacenciaAristaConPeso;

import java.util.ArrayList;

public class UniónBuscar {
	 
	ArrayList<Integer> padre = new ArrayList<Integer>(); 


	public UniónBuscar( int n ) {
		for( int i = 0 ; i < n ; i++ ){
	    	padre.add(i);
	    }
	}

	//Método para encontrar la raiz del vértice actual X
	int Find( int x ){
	    if( x == padre.get(x) ){          
	        return x;                   
	    }
	    else{ 
	    	padre.set(x, Find( padre.get(x) )); //Compresion de caminos
	    	return padre.get(x);
	    }
	}

	//Método para unir 2 componentes conexas
	void Union( int x , int y ){
	    int xRoot = Find( x );   
	    int yRoot = Find( y );    
	    padre.set(xRoot, yRoot);  
	}


}
