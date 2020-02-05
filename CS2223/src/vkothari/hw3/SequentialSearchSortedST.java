package vkothari.hw3;

/**
 * The declaration of SequentialSearchST now declares that each key can be compared
 * with each other key via the Comparable interface
 * 
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchSortedST<Key extends Comparable<Key>, Value> {
	int N;           // number of key-value pairs
	Node first;      // the linked list of key-value pairs

	// Nodes now store (key and value)
	class Node {
		Key   key;
		Value value;
		Node  next;

		public Node (Key key, Value val, Node next)  {
			this.key  = key;
			this.value  = val;
			this.next = next;
		}
	}

	public int size()                 { return N;  }
	public boolean isEmpty()          { return size() == 0; }
	public boolean contains(Key key)  { return get(key) != null; }

	
	/**
	 * Be sure to modify this method to stop once you have found a key that is
	 * larger than the key you are looking for.
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.value;
        }
        return null;
    }
		
		


	/**
	 * Be sure to modify this method to insert the key into its proper place
	 * in ascending sorted order.
	 * 
	 * @param key
	 * @return
	 */
	public void put(Key key, Value val) {
		if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }
	

	/**
	 * Can short-circuit this method once you hit a key value that is larger 
	 * than the target key being deleted.
	 * 
	 * @param key
	 */
	public void delete(Key key) {
		first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }
}