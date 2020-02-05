package vkothari.hw3;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StopwatchCPU;

/**
 * Compare SeparateChainingHashST against BST
 * 
 * Just an example code to give some ideas how to proceed with homework.
 * Dropping from homework 3
 */
public class Comparison {
	public static void main(String[] args) {
		for (int n = 65536; n <= 2097152; n*= 2) {
			SeparateChainingHashST hash = new SeparateChainingHashST();
			BST bst = new BST();
			
			StopwatchCPU sw = new StopwatchCPU();
			for (int i = 0; i < n; i++) {
				bst.put(Math.random(), true);
			}
			double bst_time = sw.elapsedTime();
			
			sw = new StopwatchCPU();
			for (int i = 0; i < n; i++) {
				hash.put(Math.random(), true);
			}
			double hash_time = sw.elapsedTime();
			
			System.out.println(n + "\t" + bst_time + "\t" + hash_time);
			
		}
		
		
	}
}
