package colaDePrioridad;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ColaDePrioridadesMia {
	private ArrayList<Nodo<Integer>> monticulo;
	private Integer numerodeElementos;
	
	public ColaDePrioridadesMia() {
		monticulo = new ArrayList<Nodo<Integer>>();
		monticulo.add(new Nodo(0, -1.0));
		numerodeElementos=1;
	}
	
	public void add(Nodo dato){
		try{
			monticulo.add(dato);
			flotar(monticulo.size()-1, dato);
			numerodeElementos++;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Boolean isEmpty(){
		return monticulo.size()<=1;
	}
	
	public boolean contains(Integer nodo){
		for(Nodo<Integer> n: monticulo){
			if(n.getDato()==nodo)
				return true;
		}
		return false;
	}
	
	public void change(Integer i, Double minDistancia) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        for(int index=1; index< monticulo.size(); index++){
        	if(monticulo.get(index).getDato().equals(i)){
        		monticulo.get(index).setMinDistance(minDistancia);
        		i=index;
        		break;
        	}
        }
        i=flotar(i,monticulo.get(i));
        i=hundir(i,monticulo.get(i));
        
    }
	
	private Integer flotar(Integer i, Nodo<Integer> nodo){
		while(monticulo.get(i/2).getMinDistance()>nodo.getMinDistance()){
			monticulo.set(i,monticulo.get(i/2));
			i/=2;
		}
		monticulo.set(i, nodo);
		return i;
	}
	
	private Integer hundir(Integer i, Nodo<Integer> nodo){
		while(esPadre(i) && esMayorQueSusHijos(i, monticulo.get(i))){
			if(monticulo.get(i*2+1).getMinDistance() < monticulo.get(i*2).getMinDistance()){
				monticulo.set(i, monticulo.get(i*2+1));
				i=i*2+1;
			}
			else{
				monticulo.set(i, monticulo.get(i*2));
				i=i*2;
			}
		}
		monticulo.set(i, nodo);
		return i;
	}
	
	private Boolean esPadre(Integer i){
		return (i*2+1< monticulo.size()-1 || i*2< monticulo.size()-1);
	}
	
	private Boolean esMayorQueSusHijos(Integer i, Nodo<Integer> nodo){
		return (monticulo.get(i*2+1).getMinDistance()<nodo.getMinDistance() || monticulo.get(i*2).getMinDistance()<nodo.getMinDistance());
	}

	public Nodo<Integer> remove() throws Exception{
		if(isEmpty()) return null;
		Integer i=1;
		Nodo<Integer> nodo = new Nodo(monticulo.get(i).getDato(), monticulo.get(i).getMinDistance());
		monticulo.set(i, monticulo.get(monticulo.size()-1));
		monticulo.remove(monticulo.size()-1);
		numerodeElementos--;
		if(!isEmpty())
			hundir(i,monticulo.get(i));
		return nodo;
	}

	public String toString(){
		StringBuilder salida = new StringBuilder();
		for(int i=1; i<monticulo.size(); i++){
			salida.append(monticulo.get(i).toString() + " - ");
		}
		return salida.toString();
	}
	
	public Integer size(){
		return monticulo.size();
	}
	
//	public static void main(String[] args) {
//		ColaDePrioridadesMia cola = new ColaDePrioridadesMia();
//		Nodo<Integer> aux = new Nodo<Integer>();
////		System.out.println(aux);
////		aux.setMinDistance(10.0);
////		aux.setDato(5);
////		System.out.println(aux);
////		System.out.println(aux.getDato() + " " + aux.getMinDistance());
//		for(int i=1; i<7; i++){
//			Double valor = (double) Math.round(Math.random()*100);
//			Nodo<Integer>  nodo = new Nodo<Integer>(i, valor);
//			//System.out.print(nodo + " - ");
//			cola.add(nodo);
//			if(i==3){
//				aux.setMinDistance(nodo.getMinDistance());
//				aux.setDato(aux.getDato());
//			}
//		}
//		System.out.println();
//		//System.out.println(cola);
//		try {
//			//while(!cola.isEmpty()){
//				//cola.remove().toString();
//				System.out.println(cola);
//			//}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		cola.change(3, 99.0);
//		System.out.println(aux);
//		System.out.println(cola);
////		for(int i=0; i<cola.size(); i++){
////			try {
////				System.out.println(cola.remove());
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
////			
////		}
//	}
	
}
