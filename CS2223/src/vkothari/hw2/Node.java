package vkothari.hw2;

/** 
 * Represents a node (factor, power) within the linked list representation of
 * Composite. 
 * 
 * You must use this Node class as part of a LinkedList to store the Composite items.
 * Do not modify this class. 
 */
public class Node {
	long   factor;    // prime factor of a composite number
	int    power;     // the power by which prime factor is raised (> 0)
	
	Node   next;      // next factor node in the factorization

	/**
	 * Create a node for the given prime factor with the given power.
	 * 
	 * @param factor   prime factor
	 * @param power    power to raise prime factor.
	 */
	public Node (long factor, int power) {
		this.factor = factor;
		this.power = power;
	}

	/**
	 * Representation of this factor (raised to a power, or omitted if 1).
	 */
	public String toString() {
		String fs = Long.toString(factor);
		if (power == 1) {
			return fs;
		}
		return fs + "^" + power;
	}
}
