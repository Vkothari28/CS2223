package vkothari.hw2;

import java.math.BigInteger;
import java.util.Random;

import junit.framework.TestCase;

public class TestComposite extends TestCase {
	// test a prime
	public void testConstruct() {
		Composite c = new Composite(23);
		assertEquals ("23", c.toString());
	}
	
	// test a single prime to a single power.
	public void testPower() {
		Composite c = new Composite(32);
		assertEquals ("2^5", c.toString());
	}
	
	// test a number of primes to a single power
	public void testPrimes() {
		Composite c = new Composite(2*3*5*7*11);
		assertEquals ("2 * 3 * 5 * 7 * 11", c.toString());
	}
	
	// test a number of primes to a different power
	public void testPrimesMultiples() {
		Composite c = new Composite(2*2*2*3*3*5*5*5*7*11);
		assertEquals ("2^3 * 3^2 * 5^3 * 7 * 11", c.toString());
	}
	
	// test a number of adding same number with individual primes. Only add power of 2 (already exists)
	public void testAddition() {
		Composite c = new Composite(2*3*5*7*11);
		Composite c2 = new Composite(2*3*5*7*11);
		Composite c3 = c2.add(c);
		assertEquals ("2^2 * 3 * 5 * 7 * 11", c3.toString());
	}
	
	// test a number of adding same number with individual primes, but different power levels
	public void testAdditionMore() {
		Composite c = new Composite(2*2*3*5*5*7*11);    // 23100
		Composite c2 = new Composite(2*3*3*5*7*7*11);   //  48510
		Composite c3 = c2.add(c);                        // == 71610
		assertEquals ("2 * 3 * 5 * 7 * 11 * 31", c3.toString());
		
	}
	
	// test a number of adding same number with individual primes. Only add power of 2 (already exists)
	public void testAdditionNoPowerTwo() {
		Composite c = new Composite(3*5*7*11);
		Composite c2 = new Composite(3*5*7*11);
		Composite c3 = c2.add(c);
		assertEquals ("2 * 3 * 5 * 7 * 11", c3.toString());
	}
	
	// test a number of adding same nothing in common
	public void testAdditionNothingInCommon() {
		Composite c = new Composite(2*3*11*13);     // 858
		Composite c2 = new Composite(5*7*17);       //   + 595 = 1453 = PRIME
		Composite c3 = c2.add(c);
		assertEquals ("1453", c3.toString());
	}
	
	// test a number of adding same nothing in common
	public void testAdditionNothingInCommonNonPrime() {
		Composite c = new Composite(3*11*13*23);       // 9867
		Composite c2 = new Composite(5*7*17*19);       //   + 11305 = 21172 = 2^2 * 67 * 79 
		Composite c3 = c2.add(c);
		assertEquals ("2^2 * 67 * 79", c3.toString());
	}
	
	

	// Try to random large primes and validate they add together and provide result.
	public void testStressTest() {
		BigInteger prime1 = new BigInteger(32, 64, new Random());
		BigInteger prime2 = new BigInteger(32, 64, new Random());
		Composite c1 = new Composite(prime1);
		Composite c2 = new Composite(prime2);
		
		assertEquals (prime1.toString(), c1.toString());
		assertEquals (prime2.toString(), c2.toString());
		
		Composite c3 = c1.add(c2);
		assertEquals (c3.value(), prime1.add(prime2));
	} 
	
	
	// lots of special cases when unit values are in play.
	public void testUnit() {
		Composite unit = new Composite(1);
		assertEquals ("1", unit.toString());
		assertTrue (unit.isUnit());
		assertFalse (unit.isPrime()); 
		
		Composite c1 = new Composite(2       *5*         11);
		Composite c2 = new Composite(    3        * 7        * 13 * 17);
		
		// gcd(a,b) == gcd(b,a)
		assertTrue (c1.gcd(c2).equals (c2.gcd(c1)));
		
		// when no common divisor exists, return 1
		assertEquals (new Composite(1), c1.gcd(c2)); 
		
		// GCD of (c,1) is always 1
		assertTrue (c1.gcd(unit).isUnit());
		assertTrue (unit.gcd(c1).isUnit());
		
		// handle multiply cases
		assertEquals (c1, c1.multiply(unit));
		assertEquals (c1, unit.multiply(c1));
		
		// handle addition by 1 only because it is special case
		assertEquals (new Composite(111), new Composite(110).add(unit));
		assertEquals (new Composite(111), unit.add(new Composite(110)));
	}  
	
	// lots of special cases for equality
	public void testEquals() {
		Composite comp = new Composite(2*5*11);
		assertFalse (comp.equals(null));
		assertFalse (comp.equals(new Integer(55)));

		assertTrue (comp.equals(comp));
		assertFalse (comp.equals (new Composite(1)));
		assertFalse (new Composite(1).equals(comp)); 
		
		assertFalse (comp.equals(new Composite(5*11)));       // missing factor
		assertFalse (comp.equals(new Composite(2*2*5*11)));   // different power
		assertFalse (comp.equals(new Composite(2*5*11*13)));  // extra factor
		
		// switch sides
		assertFalse (new Composite(5*11).equals(comp));       // missing factor
		assertFalse (new Composite(2*2*5*11).equals(comp));   // different power
		assertFalse (new Composite(2*5*11*13).equals(comp));  // extra factor
	}  
	
	// greatest common divisor tests
	public void testGCD() {
		Composite unit = new Composite(1);
		assertEquals ("1", unit.toString());
		
		Composite one = new Composite(2*2*2      *5*         11);
		Composite two = new Composite(2*2   * 3  *5*5  * 7        * 13 * 17);
		
		// gcd(a,b) == gcd(b,a)
		assertTrue (one.gcd(two).equals (two.gcd(one)));
		assertEquals(unit,one.gcd(unit));
		assertEquals (unit, unit.gcd(one));
		 
		assertEquals (new Composite(2*2*5), one.gcd(two));
	}
	
	// least common multiple tests
	public void testLCM() {
		Composite unit = new Composite(1);
		assertEquals ("1", unit.toString());
		
		Composite one = new Composite(2*2*2      *5*         11);
		Composite two = new Composite(2*2   * 3  *5*5  * 7        * 13 * 17);
		 
		// lcm(a,b) == lcm(b,a)
		assertTrue (one.lcm(two).equals (two.lcm(one))); 
		
		// lcm handles one
		assertEquals (one, one.lcm(unit)); 
		assertEquals (one, unit.lcm(one));
		 
		assertEquals (new Composite(2*2*2*3*5*5*7*11*13*17), one.lcm(two));
	}
	
	// test for primality
	public void testPrime() {
		Composite unit = new Composite(1); 
		assertFalse (unit.isPrime());
		
		Composite two = unit.add(unit);
		assertTrue (two.isPrime());
		
		Composite four = two.add(unit).add(unit);
		
		assertFalse (four.isPrime()); 
		
		// more than one factor.
		assertFalse (new Composite(2*3*5).isPrime());
	}
	
	// sanity checks
	public void testSanity() {
		try {
			new Composite(-1);
			fail ("shouldn't be able to construct a negative composite number.");
		} catch (Exception e) { }
	}
	
	
}

