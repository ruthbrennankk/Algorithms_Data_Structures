import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 01/10/18 17:35:49
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  
     *  We assume all comparisons and return statements here will execute in Theta(1) asymptotic time.
     *  
     */
    public boolean isEmpty()
    {
      if (head==null && tail==null) {
    	  return true;
      } else {
    	  return false;
      }
      
    }
    
    /**
     * Finds the size of the doubly linked list
     * 	Loops through the linked list until it reaches the final element and increases its count every time
     * @return integer the size of the linked list 
     */

    int getSize() {
    	if ( !isEmpty() ) {
    		int count=0; 
        	DLLNode node = head;
        	while(node!=null) {
        		count++;
        		node=node.next;
        	}
        	return count;
    	}
    	return 0;
    }
    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(n)
     *
     * Justification:
     *  We assume all basic statements here (e.g., initalization, comparisons ) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will in the worst case iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     *  
     */
    public void insertBefore( int pos, T data ) 
    {
    	DLLNode newNode;
      if ( !isEmpty() ) {
    	  int size = getSize();
          if (pos<=0){
        	  //add to front of list
        	  newNode = new DLLNode(data, null, head);
        	  head.prev = newNode;
        	  head = newNode;
          } else if (pos>=size)
          {
        	//add to end of list 
        	newNode = new DLLNode(data, tail, null);
        	tail.next = newNode;
        	tail=newNode;
          } else {
        	//add before pos in list  
        	DLLNode tmp=head;
        	for( int i=0; i<pos; i++ ) {
        		tmp=tmp.next;
        	}
        	newNode = new DLLNode(data, tmp.prev, tmp);
        	tmp.prev.next=newNode;
        	tmp.prev = newNode;
          }
      } else {
    	  //add the first node to the list
    	  newNode = new DLLNode (data, null, null);
    	  head = newNode;
    	  tail = newNode;
      } 
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: Theta(n)
     *
     * Justification:
     *  We assume all basic statements here (e.g., initalization, comparisons ) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the position of the element to be deleted has a value of 'n'.
     *  The for-loop will therefore loop through n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     *
     */
    public T get(int pos) 
    {
    	if ( !isEmpty() && pos>=0) {
        	DLLNode tmp=head;
        	for( int i=0; i<pos && tmp!=null; i++ ) {
        		tmp=tmp.next;
        	}
        	if (tmp!=null) {
        		return tmp.data;
        	} 
    	}
    	return  null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(n)
     *
     * Justification:
     *  We assume all basic statements here (e.g., initalization, comparisons ) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the position of the element to be deleted has a value of 'n'.
     *  The for-loop will therefore loop through n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public boolean deleteAt(int pos) 
    {
    	if ( !isEmpty() ) {
    		int size = getSize();
    		if (pos==size-1) {
    			tail=tail.prev;
    			if (tail!=null) {
        			tail.next=null;	
        		} else {
        			head=null;
        		}
    			return true;
    		} else if ( pos>0 && pos<size ) {
        		int i=0;
            	DLLNode tmp=head;
            	
            	while( i<pos ) {
            		tmp=tmp.next;
            		i++;
            	}
            	
            	if (tmp.next!=null) {
            		tmp.next.prev = tmp.prev;
            	}
            	tmp.prev.next = tmp.next;
            	return true;
        	} else if (pos==0) {
        		head=head.next;
        		if (head!=null) {
        			head.prev=null;	
        		} /*else {
        			tail=null;
        		} */
        		return true;
        	}
    	}
        return  false;
    } 

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(n)
     *
     * Justification:
     *  We assume all basic statements here (e.g., initalization, comparisons ) will execute in Theta(1) time.
     *  Thus, every one iteration of the while-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     *  
     */
    
    void reverse() 
    { 
         DLLNode tmp = null;   
         DLLNode current = head; 
           
         while (current !=  null) 
         { 
           tmp = current.prev; 
           current.prev = current.next; 
           current.next = tmp;               
           current = current.prev; 
         }       
           
         if( tmp != null ) { 
            tail=head;
        	head = tmp.prev;
         }
    } 

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements unique.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(n^2)
     *
     * Justification:
     *  We assume all basic statements here (e.g., initalization, comparisons ) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The first while-loop will in the worst case iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     *  The second while loop will in the worst case iterate over n-1 elements of the list, and therefore the total cost of this method will be (n-1)*Theta(n) = Theta(n^2 - n) ~ Theta(n^2).
     */
     public void makeUnique()
    {
    	DLLNode node = head;
    	DLLNode tmp=null;
    	
    	while( node!=null ) {
    		tmp= node.next;
    		while ( tmp!=null ) {
    			if( node.data==tmp.data ) {
    				if (tmp.next!=null) {
    					tmp.next.prev = tmp.prev;
    				} else {
    					tail=node;
    				}
    				tmp.prev.next = tmp.next;
        		}
    			tmp=tmp.next;
    		}
    		node=node.next;
    	}
    	
    }


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *		We assume all comparisons and return statements here will execute in Theta(1) asymptotic time.
     *		Although the insertBefore function has a runtime of theta(n), the for-loop will not be executed as the pos will always be 0
     *		This allows us to conclude that the runtime will be Theta(1)*Theta(1) = Theta(1).
     */
    public void push(T item) 
    {
    	insertBefore(0, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  We assume all comparisons and return statements here will execute in Theta(1) asymptotic time.
     *  
     */
    public T pop() 
    {
    	if ( !isEmpty() ) {
        	DLLNode tmp=head;
        	if (tmp.next!=null) {
        		tmp.next.prev = tmp.prev; 
        	} else {
        		tail=tmp.prev;
        	}
        	head=tmp.next;
            return tmp.data;
    	}
    	return  null;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  	We assume all comparisons and return statements here will execute in Theta(1) asymptotic time.
     *		Although the insertBefore function has a runtime of theta(n), the for-loop will not be executed as the pos will always be greater than the last position in the list
     *		This allows us to conclude that the runtime will be Theta(1)*Theta(1) = Theta(1).
     */
    public void enqueue(T item) 
    {
      insertBefore(getSize(), item);
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an enqueue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *    We assume all comparisons and return statements here will execute in Theta(1) asymptotic time.
     *    We also know that as the asymptotic runtime of the pop function is Theta(1) that the method call executes in a time of theta(1)
     *    As theta(1)*theta(1) the total asymptotic runtime for this function is theta(1)
     *    
     */
    public T dequeue() 
    {
      return pop();
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }

}

