
package cachesim.model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TestClass for AdressLayout, testing the methods: index , offset.
 * @author Kim
 *
 */
public class AdressLayoutTest {
	private static final int BLOCKSIZE = 8;
	private static final int BLOCKCOUNT = 8;
	private static AdressLayout adressLayout;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adressLayout = new AdressLayout();
	}
/**
 * test method for index and offset.
 */
	@Test
	public void test() {
		String binaryindex = adressLayout.index(BLOCKCOUNT);
	    int index = binaryindex.length();
		if(index != 3)
		fail("Wrong index, FAIL!");
		
		String binaryoffset = adressLayout.offset(BLOCKSIZE);
		int offset = binaryoffset.length();
			if(offset != 3)
			fail("Wrong offset, FAIL!");
	
	}
}
