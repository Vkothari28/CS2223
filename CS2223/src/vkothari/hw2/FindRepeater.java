package vkothari.hw2;

import java.util.LinkedList;

import junit.framework.TestCase;

/**
 * Modify the find method of this class to use a linked list to locate a repeater in an array,
 * that is, a value which is duplicated at least n/2 + 1 times in an array of size n.
 * 
 * Copy this class into your USERID.hw2 package and complete.
 */
public class FindRepeater extends TestCase {

	/** Do not modify this class. Use it as is. */
	static class Node {
		int value;
		int count;
		
		Node next;
		
		Node (int value) { 
			this.value = value;
			this.count = 1;
		}
		
		public String toString() { return "(" + value + "@" + count + ")"; }
	}
	
	/**
	 * If not found, throw exception.
	 */
	public static int find(int[] array) {
		LinkedList<Node> newlist = new LinkedList<Node>();

		int arrlength = array.length;

		int numofappearances = (arrlength/2)+1;

		int count = 1;


		for (int number = 0; number < arrlength; number++)

		{

		Node node = new Node(array[number]);

		newlist.add(node);

		}


		for (int val = 0; val < arrlength; val++)


		{

		count = 1;

		for(int value = val +1;value<arrlength;value++)

		{

		if(newlist.get(val).value == newlist.get(value).value)

		{

		count++;

		if(count >= numofappearances)

		{

		return newlist.get(val).value;

		}

		}

		}

		}



		throw new RuntimeException ("No Repeater found.");

		}
	// you can run these test cases. NO need to modify.
	public void testCases() {
		assertEquals (5, find(new int[]{ 1, 2, 3, 5, 5, 5, 5, 5}));
		assertEquals (5, find(new int[]{ 5, 5, 5, 5, 5, 1, 2, 3}));
		assertEquals (5, find(new int[]{ 1, 5, 2, 5, 3, 5, 5, 5}));
		assertEquals (1, find(new int[]{ 1, 1}));
		assertEquals (1, find(new int[]{ 1, 2, 1}));
		
		try {
			find(new int[]{ 1, 2, 1, 2});
			fail ("no repeater exists in this list.");
		} catch (RuntimeException re) {
			
		}
		
		try {
			find(new int[]{ 4, 4, 4, 4, 3, 3, 3, 3});
			fail ("no repeater exists in this list where one ALMOST exists.");
		} catch (RuntimeException re) {
			
		}
		
		try {
			find(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9});
			fail ("no repeater exists in this list.");
		} catch (RuntimeException re) {
			
		}
		
		try {
			find(new int[]{ 1 });
			fail ("no repeater in a single-element list.");
		} catch (RuntimeException re) {
			
		}
	}
}
