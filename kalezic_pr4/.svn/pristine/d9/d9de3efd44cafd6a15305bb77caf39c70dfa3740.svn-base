package correlator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

// Implements a priority queue of comparable objects using a
// min-heap represented as an array.
public class HeapPriorityQueue<AnyKey, AnyValue>{
    private HeapEntry<AnyKey, AnyValue>[] elementData;
    private int size;
    private Comparator<AnyValue> comparator;
    // Constructs an empty queue.
    @SuppressWarnings("unchecked")
    public HeapPriorityQueue() {
    	
        elementData = new HeapEntry[10];
        size = 0;
        comparator = null;
    }
    
    public HeapPriorityQueue(Comparator<AnyValue> comp){
        elementData = new HeapEntry[10];
        size = 0;
        comparator = comp;
    }
    
    // Adds the given element to this queue.
    public void add(HeapEntry<AnyKey, AnyValue>value) {
        
        if (size + 1 >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        
        // insert as new rightmost leaf
        elementData[size + 1] = value;
        
        // "bubble up" toward root as necessary to fix ordering
        int index = size + 1;
        boolean found = false;   // have we found the proper place yet?
        while (!found && hasParent(index)) {
            int parent = parent(index);
            if (comparator.compare(elementData[index].value, elementData[parent].value) < 0) {
                swap(elementData, index, parent(index));
                index = parent(index);
            } else {
                found = true;  // found proper location; stop the loop
            }
        }
        
        size++;
    }
    
    // Returns true if there are no elements in this queue.
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Returns the minimum value in the queue without modifying the queue.
    // If the queue is empty, throws a NoSuchElementException.
    public HeapEntry<AnyKey, AnyValue> peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elementData[1];
    }
    
    // Removes and returns the minimum value in the queue.
    // If the queue is empty, throws a NoSuchElementException.
    public HeapEntry<AnyKey, AnyValue> remove() {
        HeapEntry<AnyKey, AnyValue> result = peek();

        // move rightmost leaf to become new root
        elementData[1] = elementData[size];
        size--;
        
        // "bubble down" root as necessary to fix ordering
        int index = 1;
        boolean found = false;   // have we found the proper place yet?
        while (!found && hasLeftChild(index)) {
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;
            if (hasRightChild(index) &&
                    comparator.compare(elementData[right].value, elementData[left].value) < 0) {
                child = right;
            }
            
            if (comparator.compare(elementData[index].value, elementData[child].value) > 0) {
                swap(elementData, index, child);
                index = child;
            } else {
                found = true;  // found proper location; stop the loop
            }
        }
        
        return result;
    }
    
    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }
    
    // Returns a string representation of this queue, such as "[10, 20, 30]";
    // The elements are not guaranteed to be listed in sorted order.
    public String toString() {
        String result = "[";
        if (!isEmpty()) {
            result += elementData[1].data;
            for (int i = 2; i <= size; i++) {
                result += ", " + elementData[i].data;
            }
        }
        return result + "]";
    }
    
    
    // helpers for navigating indexes up/down the tree
    private int parent(int index) {
        return index / 2;
    }
    
    // returns index of left child of given index
    private int leftChild(int index) {
        return index * 2;
    }
    
    // returns index of right child of given index
    private int rightChild(int index) {
        return index * 2 + 1;
    }
    
    // returns true if the node at the given index has a parent (is not the root)
    private boolean hasParent(int index) {
        return index > 1;
    }
    
    // returns true if the node at the given index has a non-empty left child
    private boolean hasLeftChild(int index) {
        return leftChild(index) <= size;
    }
    
    // returns true if the node at the given index has a non-empty right child
    private boolean hasRightChild(int index) {
        return rightChild(index) <= size;
    }
    
    // switches the values at the two given indexes of the given array
    private void swap(HeapEntry<AnyKey, AnyValue>[] a, int index1, int index2) {
        HeapEntry<AnyKey, AnyValue> temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
}
