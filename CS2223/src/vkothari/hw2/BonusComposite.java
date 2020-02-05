package vkothari.hw2;

import java.math.BigInteger;

/**
 * Bonus Question.
 * 
 * Only attempt this problem when you have completed the full assignment.
 */
public class BonusComposite extends Composite {

	/** Represents a Composite number raised to a given power. */
	class Root {
		public final Composite base;
		public final int       power;

		public Root (Composite b, int p) {
			base = b;
			power = p;
		}
	}

	public BonusComposite(BigInteger bi) {
		super(bi);
	}

	public BonusComposite() {
		
	}

	public BonusComposite(long val) {
		this (BigInteger.valueOf(val)); 
	}
	
	/**
	 * Computes the greatest common divisor between two int values.
	 * 
	 * https://en.wikipedia.org/wiki/Euclidean_algorithm
	 *  
	 * NOTE: This is useful for maximumRoot, not for gcd(Composite).
	 */
	int gcd (int a, int b) {
		while (b != 0) {
			int t = b; 
			b = a % b; 
			a = t; 
		}
		return a;
	}
	
	/**
	 * Computes the smallest root, p, of the Composite such that p^k = this
	 * and k is the largest such exponent.
	 * 
	 * For example, if Composite = 32 = 2^5, then this function returns a Root
	 * which is equal to (2, 5). If composite = 2^6 * 3^3 * 5^3 = 216,000 then 
	 * this function returns (60, 3) since this value = 60 ^ 3.
	 * 
	 * If no such p can be found, then k=1 and you can just return (this, 1).
	 * 
	 * If composite = 2*3*7 then maximumRoot returns (42, 1).
	 * 
	 * If composite is 1, then the only root is (1, 1)
	 * 
	 * Note: gcd(int,int) will be useful
	 * 
	 * BONUS ASSIGNMENT. Only complete when done with regular assignment.
	 * 
	 * @return   the root, p, with largest power, k, such that p^k = this
	 */
	public Root maximumRoot() {
		// FIX ME.... FIX ME
		return new Root(this, 1);
	}

}
