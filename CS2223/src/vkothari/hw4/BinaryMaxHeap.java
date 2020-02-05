package vkothari.hw4;

/**
 * This is only here for the BONUS QUESTION. 
 * 
 * Do not attempt until the rest of assignment is done.
 * 
 * @param <Key>
 * @param <Value>
 */
public class BinaryMaxHeap<Key extends Comparable<Key>, Value> {
	Node root;

	// For a Node in a BinaryMaxHeap, it is certain only that the key
	// is greater than the keys in either the left or the right child (should they exist).
	class Node {
		Key    key;        
		Value  value;
		Node   left, right;  // left and right subtrees
		Node (Key k, Value v) { this.key = k; this.value = v; N = 1;}
		int N;
		public String toString() { return "[key=" + key + ", value=" + value + "]"; }
	}

	public boolean isEmpty() { 
		if(root == null) return true;
		else return false;
		}
	
	public int size() { 
		return(size(root));
	  
	}
	private int size(Node node) {
	    if (node == null) return(0);
	    else {
	      return(size(node.left) + 1 + size(node.right));
	    }
	  } 

	public void insert(Key k, Value v) {
		// FIX ME
	}
	
	public void printAll() { if (root != null) { printAll(root); }}
	void printAll(Node n) {
		if (n == null) { return; }
		
		printAll(n.left);
		System.out.println(n);
		printAll(n.right);
	}
	

	public Value delMax() {
		// FIX ME
		return null;
	}
	
}
