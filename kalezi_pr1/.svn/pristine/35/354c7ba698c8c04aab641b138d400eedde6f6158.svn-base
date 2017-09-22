package mylistpackage;

import java.util.Arrays;

/**
 * Represents basic unsorted array-based list.
 * 
 * @author modified from Building Java Programs 3rd ed.
 * @version Sep 26, 2016
 * @param <E> is of any object type
 */
public class ArrayListUnsorted<E> extends AbstractArrayMyList<E> implements MyList<E>{


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
        elementData = (E[]) new Object[capacity];
        size = -1;
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
     * Removes value at the given index, shifting subsequent values up.
     * 
     * @param index <= size and index >= 0
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    public void removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(elementData[index] == elementData[size]) {
        	elementData[size] = null;
        	size--;        	
        }
        else{
        	elementData[index] = elementData[size];
        	elementData[size] = null;
        	size--;
        }
    
    }



}
