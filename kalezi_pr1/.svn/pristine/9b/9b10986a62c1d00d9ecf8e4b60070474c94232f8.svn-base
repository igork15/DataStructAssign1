package mylistpackage;

import java.util.Arrays;

public class ArrayListSorted<E extends Comparable<? super E>> extends AbstractArrayMyList<E> implements MyList<E>{
	
	
    /**
     * Constructs an empty list of default capacity.
     */
    public ArrayListSorted() {
        this(DEFAULT_CAPACITY);
        
    }

    /**
     * Constructs an empty list of the given capacity.
     * 
     * @param capacity > 0
     * @throws IllegalArgumentException if capacity <= 0
     */
    @SuppressWarnings("unchecked")
    public ArrayListSorted(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (E[]) new Comparable[capacity];
        size = -1;
    }
	
	@Override
	public void insert(E value) {
		
		if(size < 0){
			elementData[0] = value;
			size++;
		}
		
		else {
			ensureCapacity(size + 2);
			
			
			if(value.compareTo(elementData[size]) >= 0){
				elementData[size + 1] = value;
				size++;
			} else if (value.compareTo(elementData[0]) <= 0){
				for(int i = size; i >= 0; i--){
					elementData[i + 1]= elementData[i];
				}
				elementData[0] = value;
				size++;
			} else {
				int low = 0;
				int high = size;
				
				boolean flag = true;
				
				int mid = (high)/2;
				
				while(low <= high){
					if(value.compareTo(elementData[mid + 1]) <= 0
					&& value.compareTo(elementData[mid]) >= 0){
						for(int i = size; i >= size - (size - mid); i--){
							elementData[i + 1]= elementData[i];
						}
						elementData[mid+1] = value;
						size++;
						flag = false;
					} else if(value.compareTo(elementData[mid])> 0){
						low = mid + 1;
						mid = (high + low)/2;
					} else if (value.compareTo(elementData[mid]) < 0){
						high = mid - 1;
						mid = (high + low) /2;
					}								
				}		
			}
			
			
			
		}

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		elementData = (E[]) new Comparable[DEFAULT_CAPACITY];
        size = -1;
		
	}

	@Override
	public void remove(E value) {
		int low = 0;
		int high = size;
		int mid = high/2;
		
		boolean flag = true;
		
		while(low <= high){
			if(value.compareTo(elementData[mid]) == 0){				
				for(int i = mid; i <= size; i++){
					elementData[i] = elementData[i+1];					
				}
			size--;
			flag = false;
			} else if(value.compareTo(elementData[mid]) > 0){
				low = mid + 1;
				mid = (high + low)/2;
			} else if(value.compareTo(elementData[mid]) < 0){
				high = mid - 1;
				mid = (high + mid)/2;
			} 
		}
		
	}

	@Override
	public void removeAtIndex(int index) {
		
		if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
		
		for(int i = index; i < size; i++){
			elementData[i] = elementData[i+1];
		}
		size--;		
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

}
