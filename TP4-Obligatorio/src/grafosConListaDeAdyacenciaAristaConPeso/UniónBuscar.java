package grafosConListaDeAdyacenciaAristaConPeso;

import java.util.ArrayList;

public class Uni�nBuscar {
	 
	ArrayList<Integer> p = new ArrayList<Integer>(); 


	public Uni�nBuscar( int n ) {
		for( int i = 0 ; i < n ; i++ ){
	    	p.add(i);
	    }
	}

	//M�todo para encontrar la raiz del v�rtice actual X
	int Find( int x ){
	    if( x == p.get(x) ){          
	        return x;                   
	    }
	    else{ 
	    	p.set(x, Find( p.get(x) )); //Compresion de caminos
	    	return p.get(x);
	    }
	}

	//M�todo para unir 2 componentes conexas
	void Union( int x , int y ){
	    int xRoot = Find( x );   
	    int yRoot = Find( y );    
	    p.set(xRoot, yRoot);  
	}


}
