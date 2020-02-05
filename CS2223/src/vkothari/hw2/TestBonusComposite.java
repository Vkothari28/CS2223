package vkothari.hw2;

import junit.framework.TestCase;

/**
 * Used to validate the performance of BonusComposite.maximumRoot.
 * 
 * Once you have completed the implementation of BonusComposite, this class 
 * can be used "AS IS" to validate performance of maximumRoot.
 */
public class TestBonusComposite extends TestCase {

	// test for root
	public void testRoot() {
		assertEquals (new BonusComposite(1), new BonusComposite(1).maximumRoot().base);
		assertEquals (1, new BonusComposite(1).maximumRoot().power);
		
		BonusComposite comp = new BonusComposite(2*3*7); 
		assertEquals (new BonusComposite(2*3*7), comp.maximumRoot().base);
		assertEquals (1, comp.maximumRoot().power);
		
		assertEquals (new BonusComposite(2*3*7), new BonusComposite(2*2*3*3*7*7).maximumRoot().base);
		assertEquals (2, new BonusComposite(2*2*3*3*7*7).maximumRoot().power);
		
		assertEquals (new BonusComposite(2*2*3*7*17), new BonusComposite(2*2*2*2*3*3*7*7*17*17).maximumRoot().base);
		assertEquals (2, new BonusComposite(2*2*3*3*7*7).maximumRoot().power);

		// increasing # of powers
		assertEquals (new BonusComposite(2*3*3*7*7*7*7), new BonusComposite(2*2*3*3*3*3*7*7*7*7*7*7*7*7).maximumRoot().base);
		assertEquals (2, new BonusComposite(2*2*3*3*3*3*7*7*7*7*7*7*7*7).maximumRoot().power);
		
		// up then down
		assertEquals (new BonusComposite(2*3*3*7), new BonusComposite(2*2*3*3*3*3*7*7).maximumRoot().base);
		assertEquals (2, new BonusComposite(2*2*3*3*3*3*7*7).maximumRoot().power);
		
		// when unable to produce a root. At end fails.
		assertEquals (new BonusComposite(2*2*3*3*3*3*7*7*13), new BonusComposite(2*2*3*3*3*3*7*7*13).maximumRoot().base);
		assertEquals (1, new BonusComposite(2*2*3*3*3*3*7*7*13).maximumRoot().power);

		// fails near beginning...
		assertEquals (new BonusComposite(2*3*3*3*3*7*7*13), new BonusComposite(2*3*3*3*3*7*7*13).maximumRoot().base);
		assertEquals (1, new BonusComposite(2*3*3*3*3*7*7*13).maximumRoot().power);

		
	}
	
}
