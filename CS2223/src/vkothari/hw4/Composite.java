package vkothari.hw4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import edu.princeton.cs.algs4.Stack;
import vkothari.hw4.AVL.Node;

/**
 * Homework 4: Data Type Exercise
 * 
 * Copy this class into your USERID.hw4 package and complete.
 * 
 * Each composite number is to be represented as a binary tree of prime factors (with value as power).
 * 
 * Yes, the Composite Problem is Back!
 */
public class Composite {

	/**
	 * Keep track of the AVL tree of factor/exponents based at this root.
	 * 
	 * Each key is a BigInteger factor; each value is a power of that factor
	 */
	AVL<BigInteger, Integer> tree = new AVL<BigInteger, Integer>();    

	/**
	 * Constructs a Composite with the specified value, which may not be one, zero or negative.
	 */
	public Composite(long val) {
		this (BigInteger.valueOf(val));
	} 

	/**
	 * Constructs a Composite from the given Pair<BigInteger,Integer> powers.
	 * 
	 * Useful to be able to create a Composite object from pre-existing factors and exponents 
	 */
	public Composite(Iterable<Pair<BigInteger,Integer>> factors) {
		for (Pair<BigInteger,Integer> pair : factors) {
			tree.put(pair.key, pair.value);
		}
	}

	/** Helper value for factorize. */
	public final static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

	/**
	 * Constructs a Composite with the specified value, which may not be zero, one or negative.
	 * 
	 */
	public Composite(BigInteger val) {
		if (val.compareTo(BigInteger.ZERO) <= 0) {
			throw new IllegalArgumentException ("Composite must be a non-negative value.");
		}
		if (val.compareTo(BigInteger.ONE) == 0) {
			throw new IllegalArgumentException ("Composite cannot be one.");
		}

		BigInteger mod = TWO;
		while (val.compareTo(BigInteger.ONE) > 0) {
			BigInteger modval = val.mod(mod);
			if (modval.equals(BigInteger.ZERO)) {
				val = val.divide(mod);
				if (tree.contains(mod)) {
					tree.put(mod, tree.get(mod) + 1);
				}
				else {
					tree.put(mod, 1);
				}
			}
			else {
				if (mod.equals(TWO)) {
					mod = mod.add(BigInteger.ONE);
				}
				else {
					mod = mod.add(TWO);
				}
			}
		}
	}


	/**
	 * Returns string representation as a sequence of factors with primes
	 * 
	 * 125 returns "5^3"
	 * 36 returns "2^2 * 3^2"
	 * 
	 * Note that spaces appear between the * and the other factors
	 */
	public String toString() {
		return (inorderHelper(tree.root, ""));
	}

	public String inorderHelper(Node n, String rsf) {
		if (n == null) { return rsf; }
		if (n.left != null) {
			rsf = inorderHelper(n.left, rsf);
		}
		if (rsf.equals("")) {
			rsf = rsf + ((BigInteger)(n.key)).toString();
			if ((int)n.value > 1) {
				rsf = rsf + "^" + (int)n.value;
			}
		}
		else {
			rsf = rsf + " * " + ((BigInteger)(n.key)).toString();
			if ((int)n.value > 1) {
				rsf = rsf + "^" + (int)n.value;
			}
		}
		if (n.right != null) {
			rsf = inorderHelper(n.right, rsf);
		}
		return rsf;
	}

	/**
	 * Determine if two composite values are equal to each other.
	 * 
	 * Hint: Since you can't know the structure of the respective AVL trees, you should
	 * get their respective pairs as an iterator
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }

		if (o instanceof Composite) {
			Composite other = (Composite) o;
			
			ArrayList<Pair<BigInteger, Integer>> a = new ArrayList<Pair<BigInteger, Integer>>();
			ArrayList<Pair<BigInteger, Integer>> b = new ArrayList<Pair<BigInteger, Integer>>();			
			Iterable<Pair<BigInteger,Integer>> newtree = other.tree.pairs();
			Iterable<Pair<BigInteger,Integer>> ourTree = tree.pairs();
			
			for (Pair<BigInteger, Integer> p : newtree) {
				a.add(p);
			}
			for (Pair<BigInteger, Integer> p : ourTree) {
				b.add(p);
			}
			if (a.size() != b.size()) {
				return false;
			}
			else {
				
				for (int i=0; i<a.size(); i++) {
				
					if (a.get(i).value != b.get(i).value || !(a.get(i)).key.equals(b.get(i).key)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/** 
	 * Return value of Composite as a single BigInteger.
	 * 
	 * Useful for testing.
	 * 
	 * @return  a single BigInteger value representing actual value of Composite.
	 */
	public BigInteger value() {
		BigInteger val = BigInteger.ONE;
		for (Pair<BigInteger, Integer> p : tree.pairs()) {
			for (int i=0; i < (int)p.value; i++) {
				val = val.multiply(p.key);
			}
		}
		return val;
	}

	/** 
	 * Determine if Composite represents a prime number.
	 * 
	 * See https://en.wikipedia.org/wiki/Prime_number#Primality_of_one
	 */
	public boolean isPrime() {
BigInteger two = new BigInteger("2");

        

        if(!this.value().isProbablePrime(1))

        {

            return false;

        }  

        if(!two.equals(this.value()) && BigInteger.ZERO.equals(this.value().remainder(two)))

        {

                   return false;

        }

            return true; 
	}

	/** 
	 * Determine whether composite is simply divisible by prime number factor.
	 * 
	 * You can assume factor is a prime number. If it is not, then this method is 
	 * not responsible for the result. 
	 */
	public boolean divisibleBy(long factor) {
		return divisibleBy(BigInteger.valueOf(factor));
	}

	/** 
	 * Determine whether composite is simply divisible by prime number factor.
	 * 
	 * You can assume factor is a prime number. If it is not, then this method is 
	 * not responsible for the result. 
	 */
	public boolean divisibleBy(BigInteger factor) {
		if (!factor.isProbablePrime(25)) {
			throw new IllegalArgumentException ("Factor is not prime:" + factor);
		}
		
		if (tree.contains(factor)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Computes product of two composite numbers.
	 * 
	 * @param comp
	 * @return
	 */
	public Composite multiply(Composite comp) {
		AVL<BigInteger, Integer> storeproduct= new AVL<BigInteger, Integer>(); 
		for (Pair<BigInteger, Integer> p : tree.pairs()) {
			storeproduct.put(p.key, p.value);
		}
		
		for (Pair<BigInteger, Integer> p : comp.tree.pairs()) {
			if (storeproduct.get(p.key) != null) {
				storeproduct.put(p.key, storeproduct.get(p.key) + p.value);
			}
			else {
				storeproduct.put(p.key, p.value);
			}
		}
		
		Composite product = new Composite(2); // making new composite and swapping trees
		product.tree = storeproduct;
		return product;
	}
	

	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Greatest_common_divisor
	 * 
	 * When there is no common divisor (other than the value 1) this method returns null
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite gcd(Composite comp) {
		
		AVL<BigInteger, Integer> newtree = new AVL<BigInteger, Integer>(); 
		HashMap<BigInteger, Integer> rsf = new HashMap<>();
		for (Pair<BigInteger, Integer> p : tree.pairs()) {
			rsf.put(p.key, p.value);
		}
		for (Pair<BigInteger, Integer> p : comp.tree.pairs()) {
			Integer b = rsf.get(p.key);
			if (b != null ) {
				int power = b;
				if (p.value < b) {
					power = p.value;
				}
				newtree.put(p.key, power);
			}
		}
		if (newtree.isEmpty()) { // if we have no common divisor, return null
			return null;
		}
		Composite gcd = new Composite(2);  // make new composite & copy over tree
		gcd.tree = newtree;
		return gcd;
	}

	/**
	 * Computes least common multiple with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Least_common_multiple
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite lcm(Composite comp) {
		AVL<BigInteger, Integer> Treelcm = new AVL<BigInteger, Integer>(); 
		for (Pair<BigInteger, Integer> pair : tree.pairs()) {
			Treelcm.put(pair.key, pair.value);
		}
		for (Pair<BigInteger, Integer> pair : comp.tree.pairs()) {
			Integer b = Treelcm.get(pair.key);
			if (b != null ) {
				int power = b;
				if (pair.value > b) {
					power = pair.value;
				}
				Treelcm.put(pair.key, power);
			}
			else {
				Treelcm.put(pair.key, pair.value);
			}
		}
		if (Treelcm.isEmpty()) { 
			return null;
		}
		Composite lcm = new Composite(2);  
		lcm.tree = Treelcm;
		return lcm;
	}

	/**
	 * Return Composite number with linked list of factors in ascending order. 
	 *  
	 * @param num
	 */
	public static Composite factorize(BigInteger num) {
		return new Composite(num);
	}
}
	