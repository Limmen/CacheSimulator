package cachesim.datastore;
import java.util.ArrayList;  
import cachesim.DTO.InstructionDTO;

import cachesim.util.*;


/**<code>SimulationDB<code> Is the Interface to the Cachesimulation database.
 * All calls to the database are here
 */

public class SimulationDB
{
	private static SimulationDB SimulationDB = new SimulationDB();
	
	private ArrayList<Integer> storedInstructions = new ArrayList<Integer>();
	private ArrayList<Integer> storedBlockNo = new ArrayList<Integer>();
	private ArrayList<Integer> storedSetNo = new ArrayList<Integer>();
	private ArrayList<Integer> loadedOffsets = new ArrayList<Integer>();
	private String storedIndex;
	private String storedOffset;
	private String storedUser;
	private int storedcachesize;
	private int storedsetsize;
	private int NoLoadInstructions;
	private int NoStoreInstructions;
	private int storedblockSize;
	private int storedassociativity;
	private InstructionDTO InstructionDTO;
	
	/**
	 * Constructs a <code>SimulationDB<code>
	 */
	private SimulationDB()
	{
	
	}
	/**
	 * Returns the only instance of this singleton
	 * 
	 * @return SimulationDB    The only instance of this singleton
	 */
	public static SimulationDB getSimulationDB()
	{
		return SimulationDB;
	}
	
	/**
	 * Stores Nickname.
	 * 
	 * @param nick   The nickname specified by user. 
	 */
	public void storeNickName(String nick)
	{
		storedUser = nick;
	}
	/**
	 * Stores Index.
	 * 
	 * @param index   Index bits in the specified Adresslayout
	 */
	public void storeIndex(String index)
	{
		storedIndex = index;
	}
	
	/**
	 * Stores Offset.
	 * 
	 * @param offset  Offset bits in the specified AdressLayout.
	 */
	public void storeOffset(String offset)
	{
		storedOffset = offset;
	}	
	public void storeblockSize(int blockSize)
	{
		storedblockSize = blockSize;
	}
	/**
	 * Stores instruction in a list containing all fetched instructions.
	 * 
	 * @param address   Adress(hex) for the specified instruction.
	 */
	public void storeInstruction(int address)
	{
		storedInstructions.add(address);

		
	}
	/**
	 * Method that stores what type of instruction that was executed (LOAD/STORE)
	 * 
	 * @param instrType   Type of the executed Instruction (LOAD/STORE)
	 */
	public void storeInstructionType(InstructionType instrType)
	{
		if(instrType == InstructionType.LOAD)
		NoLoadInstructions = NoLoadInstructions +1;
		if(instrType == InstructionType.STORE)
		NoStoreInstructions = NoStoreInstructions + 1;
	}
	/**
	 * Method that stores block number in a list.
	 * 
	 * @param blockNo   Specified block number in cache
	 */
	public void storeblockNo(int blockNo)
	{
		storedBlockNo.add(blockNo);
	}
	/**
	 * Method to store set number in a list.
	 * 
	 * @param setNo    Specified set number in cache.
	 */
	public void storeSetNo(int setNo)
	{
		storedSetNo.add(setNo);
	}
	/**
	 * Method to store offset of loaded instructions.
	 * 
	 * @param loadedOffset  Offset bits of loaded instruction
	 */
	public void storeloadedOffset(int loadedOffset)
	{
		loadedOffsets.add(loadedOffset);
	}
	/**
	 * Stores CacheLayout.
	 * 
	 * @param cachesize   Cachesize for the specified Cachelayout.
	 * @param setsize     Setsize for the specified Cachelayout.
	 */
	public void storeCacheLayout(int cachesize, int setsize, int associativity)
	{
	storedcachesize = cachesize;
	storedsetsize = setsize;
	storedassociativity = associativity;
	}

	/**
	 * Method to return stored instructions.
	 * 
	 * @return Instructions List of fetched instructons
	 */
	public ArrayList<Integer> getInstruction()
	{
		return storedInstructions;
	}
	 /**
	  * Method to return stored block numbers.
	  * 
	  * @return storedBlockNo   List of stored block numbers
	  */
	public ArrayList<Integer> getBlockNo()
	{
		return storedBlockNo;
	}
	/**
	 * Method to return stored set numbers.
	 * 
	 * @return storedSetNo   List of stored set numbers.
	 */
	public ArrayList<Integer> getSetNo()
	{
		return storedSetNo;
	}
	/**
	 * Method to return stored offsetbits
	 * 
	 * @return loadedOffsets    List of loaded offsets.
	 */
	public ArrayList<Integer> getloadedOffset()
	{
		return loadedOffsets;
	}
	/**
	 * Method to return number of load instructions
	 * 
	 * @return NoLoadInstructions  Number of LOAD-instructions
	 */
	public int getLoadInstructions()
	{
		return NoLoadInstructions;
	}
	/**
	 * Method to return number of store instructions.
	 * 
	 * @return NostoreInstructions  Number of STORE-instructions
	 */
	public int getStoreInstructions()
	{
		return NoStoreInstructions;
	}
	/**
	 * Method to return stored index.
	 * 
	 * @return storedIndex  Number of bits in the address used for index.
	 */
	public String getIndex()
	{
		return storedIndex;
	}
	/**
	 * Method to return stored offset.
	 * 
	 * @storedOffset  Number of bits in the address used for offset.
	 */
	public String getOffset()
	{
		return storedOffset;
	}
	/**
	 * Method to return stored users.
	 * 
	 * @return Users   A list of stored users.
	 */
    public String getUsers()
    {
    	return storedUser;
    }
    /**
     * Method to return stored Cachesize
     * 
     * @return storedcachesize  cachesize of the stored cachelayout
     */
    public int getCachesize()
    {
    	return storedcachesize;
    	
    }
    /**
     * Method to return stored Setsize
     * 
     * @return storedsetsize  Setsize of the stored cachelayout.
     */
    public int getSetsize()
    {
    	return storedsetsize;
    }
    /**
     * Method to return blockSize
     * 
     * @return storedblockSize  blocksize of the stored cachelayout.
     */
    public int getblockSize()
    {
    	return storedblockSize;
    }
    /**
     * Method to return associativity
     * 
     * @return storedassociativity    Number of sets in cache.
     */
    public int getassociativity()
    {
    	return storedassociativity;
    }
    public void InstructionDTO()
    {
    	InstructionDTO = new InstructionDTO(storedIndex, storedOffset, storedblockSize, storedassociativity,
    			 storedBlockNo, storedSetNo, 
    			  storedInstructions, loadedOffsets);
    }
    public InstructionDTO getIntructionDTO()
    {
    	return InstructionDTO;
    }
    
	}

