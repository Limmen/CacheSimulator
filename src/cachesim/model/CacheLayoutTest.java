/**
 * 
 */
package cachesim.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for Cachelayout, this class will test the methods: cachesize, setsize
 * @author Kim
 *
 */
public class CacheLayoutTest {

	private static CacheLayout cachelayout;
	private static final int BLOCKSIZE = 8;
	private static final int BLOCKCOUNT = 8;
	private static final int ASSOCIATIVITY = 1;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		cachelayout = new CacheLayout();
	}

	/**
	 * @throws java.lang.Exception
	 */

	@Test
	public void test() {
		int cachesize = 
				cachelayout.cachesize(BLOCKSIZE,BLOCKCOUNT,ASSOCIATIVITY);
		if(cachesize != (BLOCKSIZE*BLOCKCOUNT*ASSOCIATIVITY))
		fail("WRONG CACHESIZE, FAIL!");
		
		int setsize = 
				cachelayout.setsize(BLOCKSIZE, BLOCKCOUNT);
		if(setsize != (BLOCKSIZE * BLOCKCOUNT))
			fail("WRONG SETSIZE, FAIL!");
	}

}
