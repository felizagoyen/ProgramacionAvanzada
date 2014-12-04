package grafosConListaDeAdyacenciaAristaConPeso;

import java.util.ArrayList;

public class UniónBuscar {
	 
	ArrayList<Integer> p = new ArrayList<Integer>(); 


	public UniónBuscar( int n ) {
		for( int i = 0 ; i < n ; i++ ){
	    	p.add(i);
	    }
	}

	//Método para encontrar la raiz del vértice actual X
	int Find( int x ){
	    if( x == p.get(x) ){          
	        return x;                   
	    }
	    else{ 
	    	p.set(x, Find( p.get(x) )); //Compresion de caminos
	    	return p.get(x);
	    }
	}

	//Método para unir 2 componentes conexas
	void Union( int x , int y ){
	    int xRoot = Find( x );   
	    int yRoot = Find( y );    
	    p.set(xRoot, yRoot);  
	}


}
