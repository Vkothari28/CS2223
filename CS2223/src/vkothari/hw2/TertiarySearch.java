package vkothari.hw2;

import edu.princeton.cs.algs4.StdOut;

/**
 * This cousin to BinarySearch attempts to locate a value by dividing the array into "thirds"
 * and checking within each third for the target value.
 */
public class TertiarySearch {

	/** Record the number of inspections. */
	static int numInspections = 0;
	static int bnumInspections = 0;
	
	/**
	 * Request tertiary array search on the given collection and return whether value was found.
	 * 
	 * After this function completes, the static value numInspections records the total number
	 * of array inspections needed.
	 * 
	 * Note numInspections is reset each time this function is called.
	 * 
	 * DO NOT MODIFY THIS METHOD.
	 * 
	 * @param collection
	 * @param target
	 * @return
	 */
	public static boolean find (int[] collection, int target) {
		numInspections = 0;
		return find(collection, target, 0, collection.length - 1);
	}
	
	/**
	 * Same as above function - but for binary search (for comparison bonus question 1.2)
	 */
	public static boolean binaryFind(int[] collection, int target) {
		bnumInspections = 0;
		return binarySearch(collection, target);
	}
	
	/**
	 * Given an array of ascending values, subdvide into "thirds" and attempt to locate
	 * recursively using a Tertiary Array Search.
	 * 
	 * You do not need to modify this method. You should not call this method directly.
	 * Rather call find(collection, target).
	 *  
	 * @param collection    ascending order collection
	 * @param target        target to be sought
	 * @param lo            low end of range within which search proceeds (inclusive)
	 * @param hi            high end of range within which searhc proceeds (inclusive)
	 */
	static boolean find (int[] collection, int target, int lo, int hi) {
		while (lo <= hi) {
			int len = (hi-lo)/3;
			int left = lo + len;
					
			int rc = collection[left] - target; numInspections++;
			if (rc > 0) {
				return find (collection, target, lo, left-1);         // lower third
			} else if (rc < 0) {
				int right = left + len + 1;
				rc = collection[right] - target; numInspections++;

				if (rc > 0) {
					return find(collection, target, left+1, right-1); // middle third
				} else if (rc < 0) {
					return find(collection, target, right+1, hi);     // upper third
				} else {
					return true;                                      // found at right
				}
			} else {
				return true;                                          // found at original left
			}
		}
		
		return false;
	}
	
	/**
	 * Binary search function for comparison to TertiarySearch
	 * @param collection	ascending order collection
	 * @param target		target to be sought
	 */
	static boolean binarySearch (int[] collection, int target) {
		int low = 0;
		int high = collection.length-1;

		while (low <= high) {
			int mid = (low+high)/2;

			int rc = collection[mid] - target;
			bnumInspections++;
			
			if (rc < 0) {
				low = mid+1;
			} else if (rc > 0) {
				high = mid-1;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		StdOut.println("N\tMax\tTotal\tFull Counts");
		int total = 0;
		int max = -1;
		String fullCounts = "";
		for (int n = 1; n <= 20; n++) {
			int [] collection = new int [n];
			for (int i = 0; i<n; i++) {		// creating n arrays with 0 to n-1 as their data
				collection[i] = i;
			}
			for (int y = 0; y<collection.length; y++) {
				find(collection, y);	 // numInspections is reset at start of find
				total += numInspections;
				if (max < numInspections) {
					max = numInspections;
				}
				if (y == 0) {
					fullCounts = fullCounts + numInspections;
				}
				else if (y > 0) {
					fullCounts = fullCounts + "," + numInspections;
				}
			}
			System.out.println(n + "\t" + max + "\t" + total + "\t" + fullCounts);
			max = -1;
			total = 0;
			fullCounts = "";
		}
		
		StdOut.println("\nBeginning of Bonus Problem 1.2:");
		StdOut.println("N" + "\t" + "Tertiary" + "\t" + "Binary");
		// we must find average number of inspections for arrays of size 3^k for k=1..10
		int size; 
		int TertAvg = 0;
		int BinAvg = 0;
		int terminaltertavg = 0;
		int EndBinAvg = 0;
		int [] collection = null;
		for (int k=1; k<=10; k++) { 
			size = (int) Math.pow(3, k);
			collection = new int [size];
			for (int i=0; i<size; i++) {
				collection[i] = i;
				TertAvg = 0;
				BinAvg = 0;
			}
			
			
			
			for (int j=0; j<size; j++) { // getting of # of inspections for each method
				find(collection, j); 
				binaryFind(collection, j);
				
				BinAvg += bnumInspections;
				TertAvg += numInspections;
				
			}
			TertAvg = TertAvg/(size-1);
			BinAvg = BinAvg/(size-1);
			terminaltertavg += TertAvg;
			EndBinAvg += BinAvg;
			
			
			StdOut.println("3^" + k + "\t" + TertAvg + "\t\t" + BinAvg);
		}
		
		
		
		StdOut.println("\nSum of average number of inspections: ");
		
		
		StdOut.println("TertiarySearch: " + terminaltertavg + "\t" + "Binary: " + EndBinAvg);
	}
}