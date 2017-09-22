package correlator;

import java.util.Iterator;
import java.util.Stack;





// Implements a set of objects using a hash table.
// The hash table uses separate chaining to resolve collisions.
public class HashSetBJP<AnyKey, AnyValue> implements MyTreeMap<AnyKey, AnyValue>, Iterable<AnyKey>{
    private static final double MAX_LOAD_FACTOR = 0.75;
    private HashEntry<AnyKey, AnyValue>[] elementData;
    private int size;
    
    // Constructs an empty set.
    public HashSetBJP() {
        elementData = new HashEntry[1009];
        size = 0;
    }
    
    // Constructs an empty set with desired size.
    public HashSetBJP(int arraySize) {
        elementData = new HashEntry[arraySize];
        size = 0;
    }
    
    // Adds the given element to this set, if it was not already
    // contained in the set.
    public void put(AnyKey key, AnyValue value) {
        if (get(key) == null) {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }
            
            // insert new value at front of list
            int bucket = hashFunction(key);
            elementData[bucket] = new HashEntry<AnyKey, AnyValue>(key, value, elementData[bucket]);
            size++;
        } 
    }
    
    // Removes all elements from the set.
    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    // Returns true if the given value is found in this set.
    public AnyValue get(AnyKey value) {
        int bucket = hashFunction(value);
        HashEntry<AnyKey, AnyValue> current = elementData[bucket];
        while (current != null) {
            if (current.data.equals(value)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    
    // Returns true if there are no elements in this queue.
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Removes the given value if it is contained in the set.
    // If the set does not contain the value, has no effect.
    public void remove(AnyKey value) {
        int bucket = hashFunction(value);
        if (elementData[bucket] != null) {
            // check front of list
            if (elementData[bucket].data.equals(value)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry<AnyKey, AnyValue> current = elementData[bucket];
                while (current.next != null && !current.next.data.equals(value)) {
                    current = current.next;
                }
                
                // if the element is found, remove it
                if (current.next != null && current.next.data.equals(value)) {
                    current.next = current.next.next;
                    size--;
				}
            }
        }
    }
    
    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }
    
    // Returns a string representation of this queue, such as "[10, 20, 30]";
    // The elements are not guaranteed to be listed in sorted order.
    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry<AnyKey, AnyValue> current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }
    
    
    // Returns the preferred hash bucket index for the given value.
    private int hashFunction(AnyKey value) {
        return Math.abs(value.hashCode()) % elementData.length;
    }
    
    private double loadFactor() {
        return (double) size / elementData.length;
    }
    
    // Resizes the hash table to twice its former size.
    
	private void rehash() {
        // replace element data array with a larger empty version
        HashEntry<AnyKey, AnyValue>[] oldElementData = elementData;
        elementData = new HashEntry[nextPrime(elementData.length) * 3];
        size = 0;

        // re-add all of the old data into the new array
        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry<AnyKey, AnyValue> current = oldElementData[i];
            while (current != null) {
                put(current.data, current.value);
                current = current.next;
            }
        }
    }
    //returns next prime.
    private static int nextPrime(int n){
    	if(n % 2 == 0){
    		n++;
    	}
    	for(; !isPrime(n); n += 2){
    		;
    	}
    	return n;
    }
    //Returns if input is prime.
    private static boolean isPrime(int n) {
        
        
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0) return false;
        return true;
    }
    //Returns size of intersection of two sets.
    public int intersection(HashSetBJP<AnyKey, AnyValue> set){
    	int setSize = 0;
    	
    	MyIterator iterator = new MyIterator();
    	
    	while(iterator.hasNext()){
    		AnyKey next = iterator.next();
    		
    		if(set.get(next) != null){
    			setSize++;
    		}
    	}
    	return setSize;

    }

    //returns size of union of two sets.
    public int union(HashSetBJP<AnyKey, AnyValue> set){
    	return (size + set.size) - intersection(set);
    }
    // Represents a single value in a chain stored in one hash bucket.
    private class HashEntry<AnyKeyl, AnyValuel> {
        public AnyKeyl data;
        public AnyValuel value;
        public HashEntry<AnyKey, AnyValue> next;

        public HashEntry(AnyKeyl data) {
            this(data,null, null);
        }

        public HashEntry(AnyKeyl data, AnyValuel value, HashEntry<AnyKey, AnyValue> next) {
            this.data = data;
            this.next = next;
            this.value = value;
        }

    }
	@Override
	public Iterator<AnyKey> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator();
	}
	
	 private class MyIterator implements Iterator<AnyKey>
	   {
	      Stack<HashEntry<AnyKey, AnyValue>> stk = new Stack<HashEntry<AnyKey, AnyValue>>();

	      public MyIterator()
	      {
	         for(int i = elementData.length -1; i >= 0; i--){
	        	 if(elementData[i] != null){
	        		 stk.push(elementData[i]);
	        		 
	        	 }
	         }
	      }
	      public boolean hasNext()
	      {
	         return !stk.isEmpty();
	      }

	      public AnyKey next()
	      {
	    	HashEntry<AnyKey, AnyValue> cur = stk.pop();
	    	
	    	if(cur.next != null){
	    		stk.push(cur.next);
	    		
	    	}
	    	
	    	return cur.data;

	      }

	   }

}
