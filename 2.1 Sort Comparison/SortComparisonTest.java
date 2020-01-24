import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

/*
 * 
 * 						10, 	 100, 	   1000,  1000 Duplicates, 1000 nearly sorted, 1000 reverse, 1000 sorted
	Insertion, 		 0.004786, 0.054982, 6.776266, 	 9.129973, 		0.051224, 			5.889314, 		0.092337
	Merge Iterative, 0.120057, 0.142937, 5.392371, 	 2.570865, 		2.121826, 			4.68134, 		4.14239
	Merge Recursive, 0.115365, 0.096875, 11.99104, 	 7.815301, 		6.518904, 			3.215145, 		2.757779
	Quick, 			 0.014784, 0.039744, 0.591289, 	 0.209764, 		0.298744, 			0.526893, 		2.79654
	Selection, 		 0.005567, 0.223149, 15.633927,  3.685464,	 	1.181635, 			2.006729, 		0.871021
	
	
a. Which of the sorting algorithms does the order of input have an impact on? Why?
		Selection Sort, went from 15 with an unsorted array to 3, 1 and 2 with duplicates, nearly sorted and reverse respectively
b. Which algorithm has the biggest difference between the best and worst performance, based
on the type of input, for the input of size 1000? Why?
		Selection Sort, because it depends on the order of input
c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
based on the input size? Please consider only input files with random order for this answer.
		Merge recursively, Selection
d. Did you observe any difference between iterative and recursive implementations of merge
sort?
		The iterative is faster with larger inputs and nearly sorted arrays
e. Which algorithm is the fastest for each of the 7 input files?
		Quick Sort

 * 
 */

/**
 *  Test class for SortComparison.java
 *
 *  @author Ruth Brennan (17329846)
 *  @version HT 2019
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {

	//~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
		
		double[] a = new double[4];

		assertArrayEquals("test insertion sort", new double[4], SortComparison.insertionSort(a),4);
		assertArrayEquals("test quick sort", new double[4], SortComparison.quickSort(a),4);
		assertArrayEquals("test merge sort iterative", new double[4], SortComparison.mergeSortIterative(a),4);
		assertArrayEquals("test merge sort recursive", new double[4], SortComparison.mergeSortRecursive(a),4);
		assertArrayEquals("test selection sort", new double[4], SortComparison.selectionSort(a),4);
    }
    
    @Test
	public void testNull() {
		
		double[] a = null;

		assertNull("test insertion sort", SortComparison.insertionSort(a));
		assertNull("test quick sort", SortComparison.quickSort(a));
		assertNull("test merge sort iterative", SortComparison.mergeSortIterative(a));
		assertNull("test merge sort recursive", SortComparison.mergeSortRecursive(a));
		assertNull("test selection sort", SortComparison.selectionSort(a));
	}
    
    @Test
    public void testOneElement() {
		
		double[] a = {3.0};
		double[] result = {3.0};

		assertArrayEquals("test insertion sort", result, SortComparison.insertionSort(a),4);
		assertArrayEquals("test quick sort", result, SortComparison.quickSort(a),4);
		assertArrayEquals("test merge sort iterative", result, SortComparison.mergeSortIterative(a),4);
		assertArrayEquals("test merge sort recursive", result, SortComparison.mergeSortRecursive(a),4);
		assertArrayEquals("test selection sort", result, SortComparison.selectionSort(a),4);
	}
    
    @Test
    public void testUnsorted() {
		
		double[] a = {3.0, 4.0, 9.5, 2.3};
		double[] result = {2.3,3.0,4.0,9.5};

		assertArrayEquals("test insertion sort", result, SortComparison.insertionSort(a),4);
		assertArrayEquals("test quick sort", result, SortComparison.quickSort(a),4);
		assertArrayEquals("test merge sort iterative", result, SortComparison.mergeSortIterative(a),4);
		assertArrayEquals("test merge sort recursive", result, SortComparison.mergeSortRecursive(a),4);
		assertArrayEquals("test selection sort", result, SortComparison.selectionSort(a),4);
		
		double[] b = {225.6, 100093.9, 1234.5, 1235.32553};
		double[] resultb = {225.6, 1234.5, 1235.32553, 100093.};

		assertArrayEquals("test insertion sort", resultb, SortComparison.insertionSort(b),4);
		assertArrayEquals("test quick sort", resultb, SortComparison.quickSort(b),4);
		assertArrayEquals("test merge sort iterative", resultb, SortComparison.mergeSortIterative(b),4);
		assertArrayEquals("test merge sort recursive", resultb, SortComparison.mergeSortRecursive(b),4);
		assertArrayEquals("test selection sort", resultb, SortComparison.selectionSort(b),4);
		
	}
    
    @Test
    public void testUnsortedLong() {
		
		double[] a = {20.9, 19.3, 18.4, 17.8, 16.2, 15.3, 14.2, 13.9, 12.0, 11.6, 10.6, 9.8, 8.8, 7.8, 6.9, 5.4, 4.3, 3.2, 2.0, 1.9};
		double[] result = {1.9, 2.0, 3.2, 4.3, 5.4, 6.9, 7.8, 8.8, 9.8, 10.6, 11.6, 12.0, 13.9, 14.2, 15.3, 16.2, 17.8, 18.4, 19.3, 20.9};

		assertArrayEquals("test insertion sort", result, SortComparison.insertionSort(a),20);
		assertArrayEquals("test quick sort", result, SortComparison.quickSort(a),20);
		assertArrayEquals("test merge sort iterative", result, SortComparison.mergeSortIterative(a),20);
		assertArrayEquals("test merge sort recursive", result, SortComparison.mergeSortRecursive(a),20);
		assertArrayEquals("test selection sort", result, SortComparison.selectionSort(a),20);
	}
    
    @Test
    public void testSorted() {
		
		double[] a = {2.3,3.0,4.0,9.5};
		double[] result = {2.3,3.0,4.0,9.5};

		assertArrayEquals("test insertion sort", result, SortComparison.insertionSort(a),4);
		assertArrayEquals("test quick sort", result, SortComparison.quickSort(a),4);
		assertArrayEquals("test merge sort iterative", result, SortComparison.mergeSortIterative(a),4);
		assertArrayEquals("test merge sort recursive", result, SortComparison.mergeSortRecursive(a),4);
		assertArrayEquals("test selection sort", result, SortComparison.selectionSort(a),4);
    }
    
    public static void main (String[] args) {
    	
    }
    

}

