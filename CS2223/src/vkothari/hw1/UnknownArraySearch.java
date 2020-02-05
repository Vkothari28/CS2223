


/**
 * This is a sample of the structure of your solutions.
 * 
 * This code assumes nothing about the placement of the values.
 *
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class UnknownArraySearch extends ArraySearch {

	public UnknownArraySearch(int[][] a) {
		super(a); 
	}
	
	/**
	 * Create a sample Unknown Array with all positive values between 1 and n*n*n inclusive.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid BandedArray of size nxn
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n][n];
		
		// n*(n^2-1) = n*(n-1)*(n+1)
		// start at n and go to n*n*n - n
		// i.e., 4 to 60  or 3 to 24
		//   delta = 4     delta = 3
		int val = n;
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				a[r][c] = val; val += (n-1);
			}
		}
		return a;
	}
	
	/** 
	 * The only thing that is known is the positive values in the array are all <= n*n*n.
	 * 
	 * @param a    array to be investigated
	 */
	protected void checkProperty(int[][] a) {
		int n = this.length();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (inspect(r,c) <= 0) {
					throw new IllegalArgumentException ("Values must be positive:" + a[r][c]);
				}
				if (inspect(r,c) > n*n*n) {
					throw new IllegalArgumentException ("Values must be smaller than " + (n*n*n) + ":" + a[r][c]);
				}
			}
		}
	}
}
;