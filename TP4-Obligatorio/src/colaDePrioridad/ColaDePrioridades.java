package colaDePrioridad;

import java.util.ArrayList;

public class ColaDePrioridades<E> {
	private ArrayList<Contenedor<E>> monticulo;
	
	public ColaDePrioridades() {
		monticulo = new ArrayList<Contenedor<E>>();
	}
	
	public void add(Contenedor dato){
		try{
			monticulo.add(dato);
			flotar(monticulo.size()-1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Boolean isEmpty(){
		return monticulo.size()==0;
	}
	
	public boolean contains(Integer nodo){
		for(Contenedor<E> n: monticulo){
			if(n.getDato().equals(nodo)){
				return true;
			}
		}
		return false;
	}
	
	public void change(Integer i, Double minDistancia) {
        for(int index=0; index< monticulo.size(); index++){
        	if(monticulo.get(index).getDato().equals(i)){
        		monticulo.get(index).setMinDistance(minDistancia);
        		i=index;
        		break;
        	}
        }
        i=flotar(i);
        hundir(i); 
    }
	

	private Integer padre(Integer i){
		return (i-1)/2;
	}
	
	private Integer izquierda(Integer i){
		return i*2+1;
	}
	
	private Integer derecha(Integer i){
		return i*2+2;
	}
	
	private Boolean tieneIzquierda(Integer i){
		return i*2+1<monticulo.size();
	}
	
	private Boolean tieneDerecha(Integer i){
		return i*2+2<monticulo.size();
	}
	
	private Integer flotar(Integer i){
		Contenedor<E> nodo = monticulo.get(i);
		while(i>0 && monticulo.get(padre(i)).getMinDistance()>nodo.getMinDistance()){
			monticulo.set(i,monticulo.get(padre(i)));
			i=padre(i);
		}
		monticulo.set(i, nodo);
		return i;
	}
	
	
	public void hundir(int i) {
		while (tieneIzquierda(i)) { 
			int elMasChico = izquierda(i); 
			if (tieneDerecha(i)) {
				if (monticulo.get(izquierda(i)).getMinDistance() > monticulo.get(derecha(i)).getMinDistance())
					elMasChico = derecha(i); 
			}
			if (monticulo.get(elMasChico).getMinDistance() >= monticulo.get(i).getMinDistance())
				break; 
			
			Contenedor<E> temp = monticulo.get(i);
			monticulo.set(i, monticulo.get(elMasChico));
			monticulo.set(elMasChico, temp);
			
			i = elMasChico; 
		}
	}

	public Contenedor<Integer> remove(){
		if(isEmpty()) return null;
		Integer i=0;
		Contenedor<Integer> nodo = new Contenedor(monticulo.get(i).getDato(), monticulo.get(i).getMinDistance());
		monticulo.set(i, monticulo.get(monticulo.size()-1));
		monticulo.remove(monticulo.size()-1);
		if(!isEmpty())
			hundir(i);
		return nodo;
	}

	public String toString(){
		StringBuilder salida = new StringBuilder();
		for(int i=0; i<monticulo.size(); i++){
			salida.append(monticulo.get(i).toString() + " - ");
		}
		return salida.toString();
	}
	
	public Integer size(){
		return monticulo.size();
	}
	
	public static void main(String[] args) {
		ColaDePrioridades<Integer> cola = new ColaDePrioridades<Integer>();
		
		for(int i=1; i<=7; i++){
			Double valor = (double) Math.round(Math.random()*100);
			Contenedor<Integer>  nodo = new Contenedor<Integer>(i, valor);
			cola.add(nodo);
		}
		System.out.println();
		System.out.println(cola);
		
		cola.change(5, 15.2);
		System.out.println(cola);
		try {
			while(!cola.isEmpty()){
				System.out.print("removido: " + cola.remove().toString());
				System.out.println(" cola: " + cola);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
