package mylistpackage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents basic unsorted array-based list.
 * 
 * @author modified from Building Java Programs 3rd ed.
 * @version Sep 26, 2016
 * @param <E> is of any object type
 */
public class ArrayListUnsorted<E extends Comparable<? super E>> implements MyList<E> {

    /**
     * default list capacity.
     */
    private static final int DEFAULT_CAPACITY = 100;

    /**
     * list of values
     */
    private E[] elementData;

    /**
     * index of the last element in the list
     */
    private int size;

    /**
     * Constructs an empty list of default capacity.
     */
    public ArrayListUnsorted() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty list of the given capacity.
     * 
     * @param capacity > 0
     * @throws IllegalArgumentException if capacity <= 0
     */
    @SuppressWarnings("unchecked")
    public ArrayListUnsorted(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (E[]) new Comparable[capacity];
        size = -1;
    }

    /**
     * @see mylistpackage.MyList#getSize()
     */
    public int getSize() {
        return size + 1;
    }

    /**
     * @see mylistpackage.MyList#isEmpty()
     */
    public boolean isEmpty() {
        return size == -1;
    }

    /**
     * @see mylistpackage.MyList#contains(java.lang.Object)
     */
    public boolean contains(E value) {
        for (int i = 0; i <= size; i++) {
            if (elementData[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see mylistpackage.MyList#insert(java.lang.Object)
     */
    public void insert(E value) {
        ensureCapacity(size + 2);
        size++;
        elementData[size] = value;       
    }

    /**
     * @see mylistpackage.MyList#clear()
     */
    @SuppressWarnings("unchecked")
	public void clear() {
    	elementData = (E[]) new Object[DEFAULT_CAPACITY];
        size = -1;
    }

    /**
     * Creates a comma-separated, bracketed version of the list.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (size == -1) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i <= size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    /**
     * @see mylistpackage.MyList#remove(java.lang.Object)
     */
    public void remove(E value) {
        int index = getIndex(value);
        if (size >= 0 && index >= 0) {
            elementData[index] = elementData[size];
            elementData[size] = null;
            size--;
        }
    }

    /**
     * Ensures that the underlying array has the given capacity; if not,
     * increases the size by 100.
     * 
     * @param capacity > elementData.length.
     */
    private void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length + 100;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /*********************************************
     * Index list methods follow
     *********************************************/

    /**
     * Returns the index of value.
     * 
     * @param value assigned.
     * @return index of value if in the list, -1 otherwise.
     */
    public int getIndex(E value) {
        for (int i = 0; i <= size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Removes value at the given index, shifting subsequent values up.
     * 
     * @param index <= size and index >= 0
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    public void removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size] = null;
        size--;
    }

    /**
     * Replaces the value at the given index with the given value.
     * 
     * @param index <= size and index >= 0
     * @value is assigned
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    public void set(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    /**
     * Returns the value at the given index in the list.
     * 
     * @param index <= size and index >= 0
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     * @return the value at the given index in the list.
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }
    
    public void myinsertion() {
    	E temp;
    	
    	for(int i = 1; i <= size; i++){
    		int j = i;
    		temp = elementData[i];
    		while(j > 0 && elementData[j].compareTo(elementData[j-1]) < 0 ){
    			
        		elementData[j] = elementData[j-1];
        		elementData[j-1] = temp;
        		j--;
			
    		}
    		
    	}
	
    }
    
    public void myselection() {
    	E min;
    	int index = 0;
    	
    	for(int i = 0; i < size-1; i++){
    		min = elementData[i];
    		for(int j = i+1; j < size;j++){
    			if(min.compareTo(elementData[j]) > 0){
    				min = elementData[j];
    				index = j;
    			}
    			
    		}
    		if(min.compareTo(elementData[index]) == 0){
    			elementData[index] = elementData[i];
        		elementData[i] = min;
    		}
    		
    	}

    }
    
    public void myquick() {
    	quicksort(elementData,0,size);
    }
    
    public void mymerge() {
    	mergeSort(elementData);
    }
    
    private void insertionSort(E[] a, int low, int high){
    	for( int p = low + 1; p <= high; p++ )
        {
            E tmp = a[ p ];
            int j;

            for( j = p; j > low && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }
    private void quicksort( E [ ] a, int low, int high )
    {

        if( low + 10 > high )
            insertionSort( a, low, high );
        else
        {
                
            int middle = ( low + high ) / 2;
            if( a[ middle ].compareTo( a[ low ] ) < 0 )
                swapReferences( a, low, middle );
            if( a[ high ].compareTo( a[ low ] ) < 0 )
                swapReferences( a, low, high );
            if( a[ high ].compareTo( a[ middle ] ) < 0 )
                swapReferences( a, middle, high );

                
            swapReferences( a, middle, high - 1 );
            E pivot = a[ high - 1 ];

                
            int i, j;
            for( i = low, j = high - 1; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 )
                    ;
                while( pivot.compareTo( a[ --j ] ) < 0 )
                    ;
                if( i >= j )
                    break;
                swapReferences( a, i, j );
            }

                
            swapReferences( a, i, high - 1 );

            quicksort( a, low, i - 1 );    
            quicksort( a, i + 1, high );   
        }
        
 
        
    }
    
    public static final <E> void swapReferences( E [ ] a, int index1, int index2 )
    {
        E tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }
    
    private void mergeSort(E[] array){
    	int indexOne = 0;
    	int indexTwo = 0;
    	
    	if(array.length > 1){
    		int lengthL = array.length/2;
    		int lengthR = array.length - lengthL;
    		
    		E[] left = (E[]) new Comparable[lengthL];
    		E[] right = (E[]) new Comparable[lengthR];
    		
    		for(int i = 0; i < lengthL; i++){
    			left[i] = array[i];
    		}
    		for(int j = 0; j < lengthR; j++){
    			right[j] = array[j + lengthL];
    		}
    		
    		mergeSort(left);
    		mergeSort(right);
    		
    		for(int i = 0; i < array.length; i++){
    			if(indexTwo >= right.length || indexOne < left.length 
    			&& left[indexOne].compareTo(right[indexTwo]) <= 0){
    				array[i] = left[indexOne];
    				indexOne++;
    			} else{
    				array[i] = right[indexTwo];
    				indexTwo++;
    			}
    		}
    	}
    }
    /*********************************************
     * Index list methods end
     *********************************************/

    /*********************************************
     * Iterator list class / methods follow
     *********************************************/

    /**
     * Returns an iterator for this list.
     * 
     * @return an iterator for the list.
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Represents an iterator for the list.
     * 
     * @author BuildingJavaPrograms 3rd Edition
     */
    private class ArrayListIterator implements Iterator<E> {

        /**
         * current position within the list.
         */
        private int position;

        /**
         * flag that indicates whether list element can be removed.
         */
        private boolean removeOK;

        /**
         * Constructs an iterator for the given list
         */
        public ArrayListIterator() {
            position = 0;
            removeOK = false;
        }

        /**
         * Returns whether there are more list elements.
         * 
         * @return true if there are more elements left, false otherwise
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return position <= size;
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @throws NoSuchElementException if no more elements.
         * @return the next element in the iteration.
         * @see java.util.Iterator#next()
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData[position];
            position++;
            removeOK = true;
            return result;
        }

        /**
         * Removes the last element returned by the iterator.
         * 
         * @throws IllegalStateException if a call to next has not been made
         *             before call to remove.
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ArrayListUnsorted.this.removeAtIndex(position - 1);
            position--;
            removeOK = false;
        }
    }

    /*********************************************
     * Iterator list class / methods end
     *********************************************/

}
