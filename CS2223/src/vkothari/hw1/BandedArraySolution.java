package vkothari.hw1;

import algs.hw1.arraysearch.BandedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class BandedArraySolution extends BandedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public BandedArraySolution(int[][] a) {
		super(a);
	}
	
	int[] indexOfRow(int target, int row) {
		int lo = 0;
		int hi = length()-1;
		
		// Within this Row use Binary search for target.
		while (lo <= hi) {
			int mid = lo + (hi - lo)/2;
			
			int rc = inspect(row,mid) - target;
			if (rc < 0) {
				lo = mid+1;
			} else if (rc > 0) {
				hi = mid-1;
			} else {
				return new int[] {row, mid};
			}
		}
		
		// not found
		return null;
	}
	

	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override

		
		public int[] locate(int target) {
			for (int r = 0; r < length(); r++) {
				
				int[] location = indexOfRow(target, r);
				if (location !=null) { return location; }
			}
			
			return null;
		}

		
	
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = BandedArraySearch.create(13);
		new BandedArraySolution(ar).trial();
	}
}
