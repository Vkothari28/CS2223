package vkothari.hw5;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import algs.days.day12.SeparateChainingHashST;

import edu.princeton.cs.algs4.Graph;
// Note that the Day18 implementation of AVL removes <Key,Value> and only uses <Key>
import algs.days.day18.AVL;
import edu.princeton.cs.algs4.BreadthFirstPaths;
/**
 * Modify this class for problem 1 on HW5.
 */
public class WordLadder {
 
	
	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
	 */
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();
	//public boolean[] marked;
	/**
	 * Determine if the two same-sized words are off by just a single character.
	 */
	public static boolean offByOne(String w1, String w2) {
		/*int howfar=0;// the variable to check how far same sized words are
		char[] firstword= w1.toCharArray();
		char[] secondword=w2.toCharArray();
		for(int i=0;i<=4;i++) {
			
			if(firstword[i]!=secondword[i]) {
				howfar++; // increment howfar if the words are not the same
			}
		}
			if(howfar!=1) { // if the words are not 1 away then false is returned
				return false;
			}
			
			else {
				return true;
			}
		}*/
		int common = 0;// common letters
		for (int i=0; i<w1.length(); i++) {
			if ((w1.charAt(i)) == (w2.charAt(i))) { // testing if characters are equal
				common++;
			}
		}
		if (common == 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public static Iterable<Integer> shortestPathTo(int v,int s, int[]edgeTo) {
        if (! hasPathTo(v, marked)) 
            return null;
        
        // since edgeTo[w] gives the parent of w, we
        // use a stack to reverse/store the path vertices
        Stack<Integer> path = new Stack<Integer>();
        for (int o = v; o != s; o = edgeTo[o]) {
            path.push(o);
        }
        return path;
    }
	public static boolean[]marked;
	 public static boolean hasPathTo(int v, boolean[] marked) {

			return marked[v];
	    }


	/**
	 * Main method to execute.
	 * 
	 * From console, enter the start and end of the word ladder.
	 */
	 public static void main(String[] args) throws FileNotFoundException {

			// Use this to contain all four-letter words that you find in dictionary
			AVL<String> avl = new AVL<String>();
			int i = 0;
			// create a graph where each node represents a four-letter word.
			// Also construct avl tree of all four-letter words.
			// Note: you will have to copy this file into your project to access it, unless you
			// are already writing your code within the SedgewickAlgorithms4ed project.
			Scanner sc = new Scanner(new File ("words.english.txt"));
			
			while (sc.hasNext()) {
				String s = sc.next();
				if (s.length()==4) { // add string to tree & tables
					avl.insert(s); // inserting
					table.put(s, i);
					reverse.put(i, s);// reversing them
					i++;
				}
			}
			sc.close();
			//Queue<String> q = (Queue<String>) avl.keys();
			//int sizeq = q.size();
			
			//Graph words = new Graph(sizeq);
			
			
	/*		String[] check = new String[sizeq];
			for(int i =0;i<sizeq;i++) {
				String value = q.dequeue(); //get the value from the avl
				
				for(int j = 0;j< i ;j++) {
					if(check[j] != null) {
				
						if(offByOne(check[j],value)) {
							
							mygraph.addEdge(i, j);
						}
					}
				}
			
			
				check[i] = value;
				table.put(value, i);
				reverse.put(i, value);

		}*/
		
			

			// now construct graph, where each node represents a word, and an edge exists between 
			// two nodes if their respective words are off by a single letter. Hint: use the
			// keys() method provided by the AVL tree.
			// fill in here...
	         
	 

			
			Graph words = new Graph(i);
			for (int j = 0; j < i; j++) {
				String word = reverse.get(j);
				for (String w2 : avl.keys()) {
					if (offByOne(word, w2)) {
						words.addEdge(table.get(word), table.get(w2));
					}
				}
			}

			StdOut.println("Enter word to start from (all in lower case):");
			String start = StdIn.readString().toLowerCase();
			StdOut.println("Enter word to end at (all in lower case):");
			String end = StdIn.readString().toLowerCase();

			// need to validate that these are both actual four-letter words in the dictionary
			if (!avl.contains(start)) {
				StdOut.println (start + " is not a valid word in the dictionary.");
				System.exit(-1);
			}
			if (!avl.contains(end)) {
				StdOut.println (end + " is not a valid word in the dictionary.");
				System.exit(-1);
			}
			
			// finding shortest path and printing results (if one exists)
			BreadthFirstPaths solution = new BreadthFirstPaths(words, table.get(start));
			if (solution.hasPathTo(table.get(end))) {
				StdOut.println("WordLadder found from " + start + " to " + end + ":");
				for (int r : solution.pathTo(table.get(end))) {
					StdOut.println(reverse.get(r));
				}
			}
			else {
				StdOut.println("No WordLadder exists between " + start + " and " + end);
			}
		}
	}
		
		 

	


