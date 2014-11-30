package colaDePrioridad;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColaDePrioridades<Key extends Comparable<Key>> implements Iterable<Integer> {
	private int numeroDeElementosMax;        // maximum number of elements on PQ
    private int numeroDeElementos;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    public ColaDePrioridades(int numeroDeElementosMax) {
        if (numeroDeElementosMax < 0) throw new IllegalArgumentException();
        this.numeroDeElementosMax = numeroDeElementosMax;
        keys = (Key[]) new Comparable[numeroDeElementosMax + 1];    // make this of length NMAX??
        pq   = new int[numeroDeElementosMax + 1];
        qp   = new int[numeroDeElementosMax + 1];                   // make this of length NMAX??
        for (int i = 0; i <= numeroDeElementosMax; i++) 
        	qp[i] = -1;
    }

    public boolean isEmpty() {
        return numeroDeElementos == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= numeroDeElementosMax) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    public int size() {
        return numeroDeElementos;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= numeroDeElementosMax) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        numeroDeElementos++;
        qp[i] = numeroDeElementos;
        pq[numeroDeElementos] = i;
        keys[i] = key;
        swim(numeroDeElementos);
    }

    public int delMin() { 
        if (numeroDeElementos == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];        
        exch(1, numeroDeElementos--); 
        sink(1);
        qp[min] = -1;            // delete
        keys[pq[numeroDeElementos+1]] = null;    // to help with garbage collection
        pq[numeroDeElementos+1] = -1;            // not needed
        return min; 
    }

    public void change(int i, Key key) {
        if (i < 0 || i >= numeroDeElementosMax) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= numeroDeElementosMax) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) <= 0) throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        keys[i] = key;
        swim(qp[i]);
    }
    
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i]; pq[i] = pq[j]; pq[j] = swap;
        qp[pq[i]] = i; qp[pq[j]] = j;
    }

    private void swim(int k)  {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= numeroDeElementos) {
            int j = 2*k;
            if (j < numeroDeElementos && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


   /***********************************************************************
    * Iterators
    **********************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private ColaDePrioridades<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new ColaDePrioridades<Key>(pq.length - 1);
            for (int i = 1; i <= numeroDeElementos; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

}
