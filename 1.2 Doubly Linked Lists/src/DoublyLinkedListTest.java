import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);

       testDLL.insertBefore(1,4);
       assertEquals( "Checking insertBefore to a list containing 3 elements at position 1", "1,4,2", testDLL.toString() );

       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,1);

      testDLL.insertBefore(100,4);
      assertEquals( "Checking insertBefore to a list containing 3 elements at position 1", "1,4", testDLL.toString() );

        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    @Test
    public void testGet() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
         testDLL.insertBefore(1,2);
         testDLL.insertBefore(2,3);
         
         //testDLL.get(0);
         assertEquals( "Checking get to a list containing 3 elements at position 0", "1", testDLL.get(0).toString() );
         //testDLL.get(1);
         assertEquals( "Checking get to a list containing 3 elements at position 1", "2", testDLL.get(1).toString() );
         //testDLL.get(2);       
         assertEquals( "Checking get to a list containing 3 elements at position 2", "3", testDLL.get(2).toString() );
         testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
         assertEquals( "Checking get to a list containing 1 element at position -2", null, testDLL.get(-2) );
                  
         // test empty list
         testDLL = new DoublyLinkedList<Integer>();
         //testDLL.get(0);
         assertEquals( "Checking get to an empty list at position 0 - expected that it returns null", null, testDLL.get(0) );
         //testDLL.get(2);
         assertEquals( "Checking get to an empty list at position 2 - expected that it returns null", null, testDLL.get(2) );
                
    }
    
    @Test
    public void testDeleteAt() {
    	 DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
         testDLL.insertBefore(1,2);
         testDLL.insertBefore(2,3);
         
         testDLL.deleteAt(0);
         assertEquals( "Checking deleteAt on a list containing 3 elements at position 0", "2,3", testDLL.toString() );
         
         testDLL.insertBefore(0,1);
         testDLL.insertBefore(0,4);
         testDLL.insertBefore(1,5);
         testDLL.deleteAt(1);
         assertEquals( "Checking deleteAt on a list containing 4 elements at position 1", "4,1,2,3", testDLL.toString() );
         testDLL.insertBefore(2,6);
         testDLL.insertBefore(1,5);
         testDLL.deleteAt(2);
         assertEquals( "Checking deleteAt to a list containing 6 elements at position 2", "4,5,6,2,3", testDLL.toString() );
         testDLL.deleteAt(-1);        
         assertEquals( "Checking deleteAt to a list containing 6 elements at position -1 - expected doesn't delete any elements", "4,5,6,2,3", testDLL.toString() );
         testDLL.deleteAt(7);        
         assertEquals( "Checking deleteAt to a list containing 6 elemenets at position 8 - expected doesn't delete any elements", "4,5,6,2,3", testDLL.toString() );
         testDLL.deleteAt(700);        
         assertEquals( "Checking deleteAt to a list containing 6 elements at position 700 - expected doesn't delete any elements", "4,5,6,2,3", testDLL.toString() );

         testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(0,2);
    	 testDLL.deleteAt(0);
         assertEquals( "Checking deleteAt to a list containing 2 elements at position 0 - expected doesn't delete any elements", "1", testDLL.toString() );

         testDLL = new DoublyLinkedList<Integer>();
         testDLL.insertBefore(0,0);
         testDLL.insertBefore(100,3);
         testDLL.deleteAt(0);
         assertEquals("", "3", testDLL.toString() );
         
         testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
         
         //testDLL.deleteAt(0);
         assertEquals( "Checking deleteAt on a list containing 1 element at position 0", true, testDLL.deleteAt(0) );
         //testDLL.deleteAt(-2) 
         assertEquals( "Checking deleteAt on a list containing 1 element at position -2", false, testDLL.deleteAt(-2) );

         testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(1,2);
         //testDLL.deleteAt(1);
         assertEquals( "Checking deleteAt on a list containing 2 elements at position 1", true, testDLL.deleteAt(1) );
  
                  
         // test empty list
         testDLL = new DoublyLinkedList<Integer>();
         testDLL.deleteAt(0);
         assertEquals( "Checking get to an empty list at position 0 - expected that it returns null", false, testDLL.deleteAt(0) );
         testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(1,1);
         testDLL.deleteAt(1);
         assertEquals( "Checking get to an empty list at position 2 - expected that it returns null", "1", testDLL.toString() );
        
    }
    
    @Test
    public void testDeleteATWC(){
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
            String msg = "Checking deleteAt(-2) on an empty list - should return false";
            assertFalse(msg, testDLL.deleteAt(-2));
            msg = "Checking deleteAt(-1) on an empty list - should return false";
            assertFalse(msg, testDLL.deleteAt(-1));
            msg = "Checking deleteAt(0) on an empty list - should return false";
            assertFalse(msg, testDLL.deleteAt(0));
            msg = "Checking deleteAt(1) on an empty list - should return false";
            assertFalse(msg, testDLL.deleteAt(1));
            msg = "Checking deleteAt(2) on an empty list - should return false";
            assertFalse(msg, testDLL.deleteAt(2));
            msg = "Checking deleteAt() on an empty list does not modify the list";
            assertEquals(msg, "", testDLL.toString() );

            msg = "calling insertBefore(-1,1) after attempting to deleteAt on an empty list";
            testDLL.insertBefore(-1,1);
            msg = "Checking deleteAt(-2) on 1-element list ["+ testDLL +"] - should return false";
            assertFalse(msg, testDLL.deleteAt(-2));
            msg = "Checking deleteAt(-1) on 1-element list ["+ testDLL +"] - should return false";
            assertFalse(msg, testDLL.deleteAt(-1));
            msg = "Checking deleteAt(1) on 1-element list ["+ testDLL +"] - should return false";
            assertFalse(msg, testDLL.deleteAt(1));
            msg = "Checking deleteAt(2) on 1-element list ["+ testDLL +"] - should return false";
            assertFalse(msg, testDLL.deleteAt(2));
            msg = "Checking deleteAt(0) on 1-element list [" + testDLL + "] - should return true";
            assertTrue(msg, testDLL.deleteAt(0));
            msg = "Checking deleteAt(0) on 1-element list ["+ testDLL +"] deletes the element from the list";
            assertEquals(msg + " - output was [" + testDLL.toString() + "]", "", testDLL.toString() );

            msg = "calling insertBefore(-1,1) - deleteAt(0) - insertBefore(100,2) - insertBefore(100,3) - toString()";
            testDLL.insertBefore(100,2);
            testDLL.insertBefore(100,3);
            assertEquals(msg, "2,3", testDLL.toString() );
            msg = "Checking deleteAt(-2) on 2-element list - should return false";
            assertFalse(msg, testDLL.deleteAt(-2));
            msg = "Checking deleteAt(-1) on 2-element list - should return false";
            assertFalse(msg, testDLL.deleteAt(-1));
            msg = "Checking deleteAt(2) on 2-element list - should return false";
            assertFalse(msg, testDLL.deleteAt(2));
            msg = "Checking deleteAt(1) on 2-element list - should return true";
            assertTrue(msg, testDLL.deleteAt(1));
            msg = "Checking deleteAt(1) on 2-element list deletes the second element in the list";
            assertEquals(msg, "2", testDLL.toString() );
            testDLL.insertBefore(100,3);
            assertEquals(msg, "2,3", testDLL.toString() );
            msg = "Checking deleteAt(0) on 2-element list - should return true";
            assertTrue(msg, testDLL.deleteAt(0));
            msg = "Checking deleteAt(0) on 2-element list deletes the head element from the list";
            assertEquals(msg, "3", testDLL.toString() );
    }
    
    @Test
    public void testReverse() {
    	// test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.reverse();
        assertEquals( "Checking reverse to a list containing 3 elements at position 0", "3,2,1", testDLL.toString() );
       
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.reverse();        
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,3,2,1", testDLL.toString() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.insertBefore(4,5);
        testDLL.insertBefore(5,6);
        testDLL.insertBefore(6,7);
        testDLL.insertBefore(7,8);
        testDLL.reverse();
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "8,7,6,5,4,3,2,1", testDLL.toString() );
        
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();       
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();     
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "", testDLL.toString() );
    }
    
    @Test
    public void testMakeUnique() {
    	
    	// test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,2);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list containing 4 elements with 2 instances of 2", "2,1,3", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,3);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,2);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list containing 4 elements with 2 instances of 2 and 2 instances of 3", "3,2", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(1,2);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list containing 5 elements with 3 instances of the number 2", "2,1,3", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,1);
        testDLL.insertBefore(2,1);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list containing 3 elements all of which are the same", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.insertBefore(4,5);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list containing 5 unique elements", "1,2,3,4,5", testDLL.toString() );
   
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,1);
        testDLL.insertBefore(4,1);
        testDLL.insertBefore(5,1);
        //testDLL.insertBefore(1,3);
        testDLL.makeUnique();
        testDLL.insertBefore(1,3);
        assertEquals( "Checking makeUnique on a list containing 5 unique elements", "2,3,1", testDLL.toString() );

        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.makeUnique();        
        assertEquals( "Checking insertBefore on an empty list at position 0 - expected the element at the head of the list", "", testDLL.toString() );      
     }
    
    @Test
    public void testPush()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.push(4);
        assertEquals( "Checking pop to a list containing 3 elements", "4,1,2,3", testDLL.toString() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.push(1);
        assertEquals( "Checking pop to a list containing 1 element", "1,2", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(5);        
        assertEquals( "Checking insertBefore to an empty list", "5", testDLL.toString() );
    }
    
    @Test
    public void testPop()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.pop();
        assertEquals( "Checking pop to a list containing 3 elements", "2,3", testDLL.toString() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.pop();
        assertEquals( "Checking pop to a list containing 1 element", "", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.pop();        
        assertEquals( "Checking insertBefore to an empty list", "", testDLL.toString() );
    }
    
    @Test
    public void testEnqueue()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.enqueue(4);
        assertEquals( "Checking enqueue on a list containing 3 elements", "1,2,3,4", testDLL.toString() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.enqueue(2);
        assertEquals( "Checking enqueue to a list containing 1 element", "1,2", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(1);        
        assertEquals( "Checking enqueue to an empty list", "1", testDLL.toString() );
    }
    
    @Test
    public void testDequeue()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.dequeue();
        assertEquals( "Checking enqueue on a list containing 3 elements", "2,3", testDLL.toString() );
       
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);        
        testDLL.insertBefore(4,5);

        testDLL.dequeue();
        assertEquals( "Checking enqueue on a list containing 3 elements", "2,3,4,5", testDLL.toString() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.dequeue();
        assertEquals( "Checking pop to a list containing 1 element", "", testDLL.toString() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,100);
        testDLL.insertBefore(1,200);
        testDLL.insertBefore(2,300);
        
        testDLL.dequeue();
        assertEquals( "Checking pop to a list containing 3 elements", "200,300", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.dequeue();        
        assertEquals( "Checking insertBefore to an empty list", "", testDLL.toString() );
    }
    
    @Test
    public void testSizeOf() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        //assertEquals( "Checking enqueue on a list containing 3 elements", 3, testDLL.getSize() );
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        assertEquals( "Checking enqueue on a list containing 2 elements", 2, testDLL.getSize() );

    }
    
    

}
