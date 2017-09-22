package mylistpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents basic unsorted node-based list.
 * 
 * @author modified from Building Java Programs 3rd ed.
 * @version Sep 2016
 * @param <E> is of any object type
 */
public class LinkedListUnsorted<E> extends AbstractLinkedMyList<E>implements MyList<E> {




    /**
     * Constructs an empty list.
     */
    public LinkedListUnsorted() {
        
        back = null;
        size = -1;
    }


    
}

