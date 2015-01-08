package cachesim.model;

/**
 *The <code>CacheLayout<code> class Contains all methods regarding cachelayout.
 */
public class CacheLayout 
{
	/**
	 * Constructs a <code>CacheLayout<code>.
	 */
	public CacheLayout()
	{
		
	}

	
	/**
	 * Calculates the total size of the Cache.
	 * 
	 * @param blockSize     the size of one block in the specified cachelayout.
	 * @param blockCount    the number of blocks in one set in the specified cachelayout.
	 * @param associativity the number of sets in specified cachelayout.
	 * 
	 * @return setSize  the Cachesize of the specified cachelayout
	 */
	public int cachesize(int blockSize, int blockCount, int associativity)
	{
		int cacheSize = blockCount*blockSize*associativity;
		
		return cacheSize;
	}
	
	/**
	 * Calculates setsize in the cache
	 * 
	 * @param blockSize  the size of one block in the specified cachelayout.
	 * @param blockCount the number of blocks in one set in the specified cachelayout.
	 * @return setsize   the setsize of the specified cachelayout
	 */
	public int setsize(int blockSize, int blockCount)
	{
		int setsize = blockSize * blockCount;
		return setsize;
	}
	
	
	

}
