package vkothari.hw4;

import edu.princeton.cs.algs4.StdRandom;

/**
 * All you have to do for Question 1 is rearrange the values in line 17.
 */
public class BonusQuestion {

	public static void main(String[] args) {
		
		BinaryMaxHeap<Integer,String> heap = new BinaryMaxHeap<Integer,String>();
		
		// create strings of K characters with priority K
		int vals[] = new int[40];
		for (int i = 1; i <= vals.length; i++) {
			vals[i-1] = i;
		}
		StdRandom.shuffle(vals);
		
		for (int r : vals) {
			String s = "";
			for (int j = 0; j < r; j++) {
				s = s + "*";
			}
			
			heap.insert(r,  s);
		}
		
		// print out heap one by one to show an inverted triangle.
		while (!heap.isEmpty()) {
			System.out.println(heap.delMax());
		}
	}
}
