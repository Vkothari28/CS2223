package vkothari.hw2;

import java.math.BigInteger;


/**
 * Homework 2, Question 3: Data Type Exercise
 * 
 * Copy this class into your USERID.hw2 package and complete.
 * 
 * Note: You should not attempt to store as a single BigInteger the value of the composite number.
 * That is, only keep track of the factor/exponents linked list only.
 */
public class Composite {
 
	/** Keep track of the linked list of factor/exponents starting with this head. */
	Node head;    
	
	/**
	 * Constructs a Composite with the specified value, which may not be one, zero or negative.
	 */
	public Composite(long val) {
		this (BigInteger.valueOf(val));
	} 

	/**
	 * Constructs a Composite with the specified value, which may not be zero or negative.
	 * 
	 * Must handle unit case properly.
	 */
	public Composite(BigInteger val) {
		 
			if (val.compareTo(BigInteger.ZERO) <= 0) {
				throw new IllegalArgumentException ("Composite must be a non-negative value.");
			}
			if (val.compareTo(BigInteger.ONE) == 0) { 
				head = null;
			}
			else {
				Composite x = factorize(val); // factorize the value and store in composite x
				head = x.head; //change the head 
			
				 }
		 }
	
	/**
	 * Helper constructor only by use within this class, for creating a new (empty) composite
	 * object. You will find this useful in multiply.
	 * 
	 * Must be private to ensure no one outside this class calls it directly. Note that the 
	 * Composite object returned is treated like the value 1.
	 */
	Composite() {

	}

	/**
	 * Returns string representation as a sequence of factors with primes
	 * 
	 * 125 returns "5^3"
	 * 36 returns "2^2 * 3^2"
	 * 
	 * 1 returns "1" (special case)
	 */
	
		public String toString() {
			String str = "";
			Node copy = head;
		
			 if (head != null) {
				str = str + (head.factor);
				if (head.power != 1) {
					str = str + "^" + (head.power);
				}
			}
			
			 else if (head == null) {
				str = str + "1";
				return str;
			}
			while (copy.next != null) { 
				copy = copy.next;
				str = str +  " * " + (copy.factor);
				if (copy.power != 1) {
					str = str + "^" + (copy.power);
				}
			}
			return str;
		}

			// a helper function to check if nodes are equal or not
		public boolean nodeEquals(Node x, Node y) {

			
			if (x.factor == y.factor && x.power == y.power) {
				return true;
			}
			if (x == null || y == null) {  // ensures valid inputs
				return false;  
			}

			
			else {
				return false;
			}
		}
		


	/**
	 * Determine if two composite values are equal to each other.
	 */
	public boolean equals (Object o) {
		
		boolean equals = true; 
		if (o == null) { return false; }
		
		if (o instanceof Composite) {
			Composite other = (Composite) o;
			
		
			Node oHead = ((Composite) o).head;
			Node tHead = this.head;
			if (oHead == null && tHead == null) { //both are unit case composites
				return true;
			}
			if (oHead == null ^ tHead == null) { //one but not both is null
				return false;
			}

			if (oHead.next == null && tHead.next == null) { //if only one node
				equals = nodeEquals(oHead, tHead);
			}

			while (oHead.next != null && tHead.next != null) { //checking each node
				if (!nodeEquals(oHead, tHead)) {
					return false;
				}
				oHead = oHead.next;
				tHead = tHead.next;
			}

			if (oHead.next == null ^ tHead.next == null) { //check if one ran out before other
				return false;
			}
		}
		else { // not a composite
			equals = false;
		}

		return equals;
	}
	
		/** 
	 * Return value of Composite as a single BigInteger.
	 * 
	 * Necessary when adding two composite numbers a+b when gcd(a,b) != a and gcd(a,b) != b.
	 * Note this is typically the case with two random numbers.
	 * 
	 * Also useful for testing.
	 * 
	 * @return  a single BigInteger value representing actual value of Composite number.
	 */
	public BigInteger value() {
		if (head == null) {
			return BigInteger.ONE;
		}
		long valH = (long)Math.pow(head.factor, head.power);
		
		Node nextfac = head;
		
		BigInteger val = BigInteger.valueOf(valH);
		while (nextfac.next != null) {
			nextfac = nextfac.next;
			long nextVal = (long)Math.pow(nextfac.factor, nextfac.power);
			val = val.multiply(BigInteger.valueOf(nextVal));
		}
		return val;
	}
	
	
	/** 
	 * Determine if Composite represents a prime number.
	 * 
	 * Note: the unit composite number (i.e., 1) is not a prime number.
	 * 
	 * See https://en.wikipedia.org/wiki/Prime_number#Primality_of_one
	 */
	public boolean isPrime() {
		
		int Facs = 0;
		
		
		boolean Prime = false;
		
		
		if (head == null) {
			return false;
		}
		
		if (Facs < 2) {
			
			
			Prime = true;
		}
		
		
		
		if (head.next == null) {
			if (head.power > 1) {
				return false;
			}
		}
		while (head.next != null) {
			
			
			
			head = head.next;
			Facs++;
			
			
			if (Facs == 2) {
				
				
				
				return false;
			}
		}
		
		return Prime;
	}

	/** 
	 * Determine if Composite represents the unit number 1.
	 */
	public boolean isUnit() {
		
		if (head == null) {
			return true;
		}
		else {
					
		return (value() == BigInteger.valueOf(1));
		}
	}
	

	/**
	 * Computes sum of two composite numbers.
	 * 
	 * For full credit, performance of code must be directly proportional to N and M (where
	 * N is number of factors in 'this' and M is number of factors in comp) PLUS the 
	 * extra cost of factoring the BigInteger (this + comp)/gcd(this,comp).
	 *  
	 * In other words, for full credit, your code must attempt to do the following:
	 *   a) Find a common factor to both and then add together the remaining terms. 
	 * 
	 * (2^3 * 3^2 * 5 * 7) + (2^2 * 5^2 * 11) =  2520 + 1100
	 * 
	 * In the above, the common factor is (2^2 * 5) which is extracted, leaving behind 
	 * the remainder of this (2 * 3^2 * 7) and the remainder of comp (5 * 11).
	 * 
	 * (2^2 * 5) * ( 2 * 3^2 * 7 + 5*11)      = 20 * ( 126 + 55 )
	 * 
	 * The following logic can be used to reflect the result above. That is, once you
	 * are able to compute the 'common' Composite number, you multiply it by the 
	 * Composite value which is the result of adding the two remainders together, each
	 * converted first to a BigInteger.
	 * 
	 * common.multiply(factorize(remainderComp.value().add(remainder.value())));
	 * 
	 * @param comp
	 * @return
	 */
	public Composite add(Composite comp) {
		Composite sum = new Composite();
		BigInteger compVal = comp.value();
		BigInteger thisVal = this.value();

		Composite gcd = gcd(comp);

		BigInteger remainderC = compVal.divide(gcd.value());
		BigInteger remainder = thisVal.divide(gcd.value());

		sum = gcd.multiply(factorize(remainderC.add(remainder)));

		return sum;
	}

	public Composite findRemainder(Composite comp, Composite divisor) {
		//create copies to avoid modifying 
		Composite compCopy = new Composite();
		compCopy.head = comp.head;
		Composite divisorCopy = new Composite();
		divisorCopy.head = divisor.head;
		Composite result = new Composite();
		Node divHead = divisorCopy.head;
		Node compHead = compCopy.head;

		//base cases
		if (compHead == null || divHead == null) {
			return compCopy;
		}

		while (divHead != null) { // make inverse, then multiply
			divHead.power = -divHead.power;
			divHead = divHead.next;
		}

		result = compCopy.multiply(divisorCopy);

		return result;
	}


	/**
	 * Computes product of two composite numbers.
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in 
	 * self and M is the number of factors in comp.
	 * 
	 * Simply returns 'this' when asked to multiply by 1 (the unit Composite number).
	 * Alternatively, if the unit composite number is asked to be multiplied by another
	 * composite number, then that number is simply returned.
	 * 
	 * @param comp
	 * @return
	 */
	public Composite multiply(Composite comp) {
		//copy pointers in case that we are squaring it
		if (this.equals(comp)) { //squaring
			Composite result = new Composite();
			result.head = new Node(head.factor, head.power*2);
			Node nextNode = head.next;
			Node curr = result.head;
			while (nextNode != null) {
				curr.next = new Node(0,0);
				curr = curr.next;
				curr.factor = nextNode.factor; // grab next node
				curr.power = nextNode.power * 2; // multiply by 2
				nextNode = nextNode.next;
			}

			return result;
		}

		if (comp.head == null) {
			return this;
		}
		if (this.head == null) {
			return comp;
		}

		//iterate through each linkedList, grabbing all factors from each composite
		while (comp.head != null) {
			//compare factors
			long compFactor = comp.head.factor;
			long ourFactor = head.factor;
			if (compFactor == ourFactor) {
				head.power = head.power + comp.head.power; //add powers if factor is same
				comp.head = comp.head.next; //remove the value from comp
				return multiply(comp); //recursive call, multiply the rest of comp
			}
			if (compFactor < ourFactor) { //less, just insert it at the beginning of our head
				Node newHead = new Node(compFactor, comp.head.power);
				newHead.next = head;
				head = newHead;
				comp.head = comp.head.next;
			}
			if (compFactor > ourFactor) {
				Node next = head;
				Node prev = next;
				while (compFactor > ourFactor && next.next != null) {
					prev = next;
					next = next.next;
					ourFactor = next.factor;
				}
				if (next.next == null) { //comp is longer, copy it 
					next.next = comp.head;
					return this;
				}
				if (compFactor == ourFactor) { //found equal power
					next.power = next.power + comp.head.power; //move exp, recursive call
					comp.head = comp.head.next;
					return multiply(comp);
				} 
				if (compFactor < ourFactor) { //we could not find a matching factor
					Node insert = new Node(compFactor, comp.head.power);
					insert.next = head.next;
					prev.next = insert;
					comp.head = comp.head.next;
					return multiply(comp);
				}
			}

		}

		return this;
	}
	
	// a helper function for GCD
	public BigInteger Help(BigInteger x, BigInteger y) {
		if (y == BigInteger.ZERO) {
			return x;
		}
		else {
			return Help(y, x.mod(y));
		}
	}


	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Greatest_common_divisor
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in 
	 * self and M is the number of factors in comp.
	 * 
	 * The greatest common divisor of (comp,1) is 1
	 * 
	 * When there is no common divisor (other than the value 1) this method returns
	 * a unit composite number.
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite gcd(Composite comp) {
	
		Node Head1 = head;
		Node Head2 = comp.head;

		if (Head1 == null || Head2 == null) { //if either are unit composite
			return new Composite();
		}
		else {
			BigInteger thisVal = this.value();
			BigInteger otherVal = comp.value();
			BigInteger gcd = Help(thisVal, otherVal);
			return new Composite(gcd);			
		}
	}
	
	
	
	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Least_common_multiple
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in 
	 * self and M is the number of factors in comp.
	 * 
	 * The least common multiple of (comp,1) is comp.
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite lcm(Composite comp) {
		
		if (head == null && comp.head == null) { // both are composite
			return new Composite();
		}
		else if (head == null) { 
			return comp;
		}
		else if (comp.head == null) {
			return this;
		}
		else {
			BigInteger lcm = this.value().multiply(comp.value().divide(gcd(comp).value()));
			return new Composite(lcm);
		}
	}
	
	
	/** Helper value for factorize. */
	public final static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

	/**
	 * Return Composite number with linked list of factors in ascending order. 
	 *  
	 * @param num
	 * @return
	 */
	public static Composite factorize(BigInteger num) {
		Node head = null;
		BigInteger mod = TWO;
		BigInteger workingNum = num;
		BigInteger total = BigInteger.ZERO;
		long numVal = num.longValue();
		long workingnumVal = workingNum.longValue();
		long totalVal = total.longValue();
		long ValMod = mod.longValue();
		while (total.compareTo(num) < 0) {
			if (workingNum.mod(mod) == BigInteger.ZERO) {
				workingNum = workingNum.divide(mod);
				workingnumVal = workingNum.longValue();
				ValMod = mod.longValue();
				if (head == null) {
					head = new Node(ValMod, 1);
					total = total.add(mod);
					totalVal = total.longValue();
				}
				else {
					if (head.factor == ValMod) {
						head.power++;
						total = total.multiply(mod);
						totalVal = total.longValue();
					}
					else {
						Node curr = head.next;
						while (true) {
							boolean newNode = false;
							if (curr == null) {
								curr = new Node(ValMod, 1);
								total = total.multiply(mod);
								totalVal = total.longValue();
								newNode = true;
							}
							else if (curr.factor == ValMod) {
								curr.power++;
								total = total.multiply(BigInteger.valueOf(curr.factor));
								totalVal = total.longValue();
								break;
							}
							else if ((curr.next == null) && (curr.factor != ValMod)) {
								curr = new Node(ValMod, 1);
								total = total.multiply(mod);
								totalVal = total.longValue();
								newNode = true;
							}
							if (newNode) {
								Node last = head;
								while (last.next != null) {
									last = last.next;
								}
								last.next = curr;
								break;
							}
							curr = curr.next;
						}
					}
				}

			}
			else if (workingNum.mod(mod) != BigInteger.ZERO) {
				mod = mod.add(BigInteger.ONE);
				ValMod = mod.longValue();
			}
		}

		Composite compo = new Composite();
		compo.head = head;

		return compo;
	}

}
