package cachesim.model;

/**
 * The <code>AdressLayout<code> class  Contains all methods regarding AdressLayout.
 */

public class AdressLayout
{	
	private int index;
	private int offset;
	
	/**
	 * Constructs a <code>AdressLayout<code> object
	 */
	public AdressLayout()
	{

	}
	
	/**
	 * Calculates [index] bits in the address.
	 * 
	 * @param blockCount    Number of blocks in one set for the specified Cachelayout
	 * @param associativity Number of sets in the specified Cachelayout.
	 * @return index        Number of bits used for index in specified Addresslayout.
	 */
	public String index(int blockCount)
	{
		String index = Integer.toBinaryString(blockCount-1);
		return index;
	}
	
	/**
	 * Calculates [offset] bits in the address.
	 * 
	 * @param blockSize   Size of one block in the specified Cachelayout.
	 * @return offset     Number of bits used for offset in the specified Addresslayout
	 */
	public String offset(int blockSize)
	{
		String offset = Integer.toBinaryString(blockSize-1);
		return offset;
	}
	

}


