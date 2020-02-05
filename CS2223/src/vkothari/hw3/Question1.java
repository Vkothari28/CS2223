package vkothari.hw3;

import edu.princeton.cs.algs4.StdRandom;
/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {

		// for N in 16 .. 512
		
		//   for each N, do T=100 trials you want to keep track of 
		//       what you believe to be the MOST number of exch invocations
		//       and most number of less invocations
		
		//       compute a random array of N uniform doubles
		
		//   Make sure you output for each N the maximum values you saw
		//   in a table like...
		//
		//       N   MaxComp    MaxExch
		//       16  22         8
		//     .....
		System.out.println("N\tMaxComp\tMaxExch");
		for (int n = 16; n < 512; n = n * 2) {
			int maxExch =0;
			int maxComp = 0;
			for (int test = 0; test < 100; test++) {
				
				Comparable[] myvals = new Comparable[n];
				
				
				
				for (int i = 0; i < n; i++) {
				
					
					myvals[i] =  Math.floor(StdRandom.uniform() * n) ;
				}
				
				Heap.constructHeap(myvals);
				
				
				
				
				if(Heap.numexch > maxExch) {
					maxExch = Heap.numexch;
				}
				
				
				if(Heap.numcomp > maxComp) {
					maxComp = Heap.numcomp;
				}
				
				
			
				
				
				Heap.numexch = 0;
				Heap.numcomp = 0;
				

			}
			System.out.println(n + "\t" + maxComp + "\t" + maxExch);

		}
	}


	}

