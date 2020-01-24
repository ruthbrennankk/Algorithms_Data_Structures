import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Ruth Brennan (17329846)
 *  @version HT 2019
 */

 class SortComparison {

    /*
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
    	
    	if (a != null && a.length>=2) {
			int n = a.length;
			for (int i = 1; i < n; ++i) {
				double key = a[i];
				int j = i - 1;

				/* Move elements of a[0..i-1], that are 
				   greater than key, to one position ahead 
				   of their current position */
				while (j >= 0 && a[j] > key) {
					a[j + 1] = a[j];
					j = j - 1;
				}
				a[j + 1] = key;
			}
		}
    	return a;
        
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
	
		 if (a != null && a.length>=2) {
			 sortQS(a, 0, a.length-1);
		 }
		 return a;

    }
    
    static void sortQS(double a[], int low, int high) {
    	if (low < high) {
    		//pi is partitioning index, a[pi] is now at the right place
    		int pi = partition(a, low, high);
    		//recursively sort elements before
    		//partition and after partition
    		sortQS(a, low, pi-1);
    		sortQS(a, pi+1, high);
    	}
    }
    
    static int partition(double[] a, int low, int high) {
    	double pivot = a[high];
		 int i = low -1; //index of smaller element
		 for (int j=low; j<high; j++) {
			 //if the current element is smaller
			 //or equal to pivot
			 if (a[j] <= pivot) {
				 i++;
				 //swap a[i] with a[j]
				 double temp = a[i];
				 a[i] = a[j];
				 a[j] = temp;
			 }
		 }
		 
		 //swap a[i+1] and a[high] (or pivot)
		 double temp = a[i+1];
		 a[i+1] = a[high];
		 a[high] = temp;
		 
		 return i+1;
    }
    //end quicksort
    
    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortIterative (double a[]) {

		 if (a != null && a.length>=2) {
			 int low = 0;
			 int high = a.length -1;
			 //sort array a using temporary array temp
			 double[] temp = Arrays.copyOf(a, a.length);
			 //divide the array into blocks of size m
			 //m = [1,2,4,8,16,...]
			 for ( int m = 1; m<=high - low; m=2*m) {
				 for(int i=low; i<high; i+=2*m) {
					 int from = i;
					 int mid = i + m -1;
					 int tmp = i+2*m-1;
					 int to;
					 if (tmp<high) {
						 to = tmp;
					 } else {
						 to = high;
					 }
					  //= Integer.min(i+2*m-1, high);
					 
					 merge(a, temp, from, mid, to);
				 }
			 }
		}
		return a;
    }  
    
    static void merge(double[] a, double [] temp, int l, int m, int r) {
    	int k=l,i=l, j=m+1;
    	
    	//loop till there are elements in the left and right runs
    	while ( i <= m && j <= r) {
    		if (a[i] <a[j]) {
    			temp[k++] = a[i++];
    		} else {
    			temp[k++] = a[j++];
    		}
    		
    		//copy remaining elements
    		while ( i<=m ) {
    			temp[k++] = a[i++];
    		}
    		
    		//Dont need to copy second half
    		
    		//copy back to original array
    		for (i=0; i<r; i++) {
    			a[i] = temp[i];
    		}
    	}
    }
    
    
    
    //end mergesortIterative
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
 
    	if (a != null && a.length > 1) {
			 int mid = a.length / 2;
			 
			 //split left part
			 double[] left = new double[mid];
			 for (int i =0; i<mid; i++) {
				 left[i] = a[i];
			 }
			 
			 //split right part
			 double[] right = new double[a.length-mid];
			 for (int i=mid; i<a.length; i++) {
				 right[i-mid] = a[i];
			 }
			 mergeSortIterative(left);
			 mergeSortIterative(right);
			 
			 int i=0, j=0, k=0;
			 
			 //merge left and right arrays
			 while (i<left.length && j<right.length) {
				 if (left[i] <right[j]) {
					 a[k] = left[i];
					 i++;
				 } else {
					 a[k] = right[j];
					 j++;
				 }
				 k++;
			 }
			 //collect remaining elements
			 while (i<left.length) {
				 a[k] = left[i];
				 i++;
				 k++;
			 }
			 while(j<right.length) {
				 a[k] = right[j];
				 j++;
				 k++;
			 }
			 
		 }
		 
		 return a;
   }
    
    
    
    //end mergeSortRecursive
    	
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){

         if (a!=null && a.length>=2) {
			int n = a.length;
			//one by one move the boundary of unsorted subarray
			for (int i = 0; i < n - 1; i++) {
				//find the minimum element in unsorted array
				int min_idx = i;
				for (int j = i + 1; j < n; j++) {
					if (a[j] < a[min_idx])
						min_idx = j;
					//swap the found minimum element with the first element
					double temp = a[min_idx];
					a[min_idx] = a[i];
					a[i] = temp;
				}
			} 
		}
		return a;

    }//end selectionsort

   


    public static void main(String[] args) {
    	/*
    	File file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbers10.txt");
		double a[] = new double[10];
		int i = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    	
    	double startTime = System.nanoTime();
    	selectionSort(a);
    	double endTime = System.nanoTime();
    	double duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 10 " + duration);
    	

    	file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbers100.txt");
		a = new double[100];
		i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		startTime = System.nanoTime();
		selectionSort(a);
    	endTime = System.nanoTime();
    	duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 100 " + duration);
    	
    	file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbers1000.txt");
		a = new double[1000];
		i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		startTime = System.nanoTime();
		selectionSort(a);
    	endTime = System.nanoTime();
    	duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 1000 " + duration);
    	
    	file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbers1000Duplicates.txt");
		a = new double[1000];
		i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		startTime = System.nanoTime();
		selectionSort(a);
    	endTime = System.nanoTime();
    	duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 1000 Duplicates " + duration);
    	
    	file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbersNearlyOrdered1000.txt");
		a = new double[1000];
		i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		startTime = System.nanoTime();
		selectionSort(a);
    	endTime = System.nanoTime();
    	duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 1000 nearly sorted " + duration);
    	
    	file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbersReverse1000.txt");
		a = new double[1000];
		i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		startTime = System.nanoTime();
		selectionSort(a);
    	endTime = System.nanoTime();
    	duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 1000 reverse " + duration);
    	
    	file = new File("/Users/ruthbrennan/Documents/ALD/numbers/numbersSorted1000.txt");
		a = new double[1000];
		i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				a[i] = Double.parseDouble(st);
				i++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		startTime = System.nanoTime();
		selectionSort(a);
    	endTime = System.nanoTime();
    	duration = (endTime - startTime)/1000000;
    	System.out.println("insertion sort - 1000 sorted " + duration);
    	*/
    }//end of main

 }//end class
