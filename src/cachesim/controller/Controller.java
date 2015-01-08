package cachesim.controller;
import cachesim.datastore.SimulationDB; 
import cachesim.DTO.InstructionDTO;
import cachesim.util.InstructionType;
import cachesim.model.*;
import java.util.ArrayList; 

/**
 * This is the application's only <code>controller<code>.
 * All calls to the model pass through here. 
 */

public class Controller
{
	private InstructionSimulation Instructionsimulation;
	private AdressLayout Adresslayout;
	private CacheLayout Cachelayout;
	private SimulationDB SimulationDB;
	

	/**
	 * Constructs a <code>Controller<code> object.
	 * @param SimulationDB  A Database.
	 */
	public Controller(SimulationDB SimulationDB)
	{
		this.SimulationDB = SimulationDB;
		
	}
	
	/**
	 * Creates new CacheSimulation
	 */
    public void makeNewSimulation() 
    {
        Instructionsimulation = new InstructionSimulation();
        Adresslayout = new AdressLayout();
        Cachelayout = new CacheLayout();  
    } 
    /**
     * Call CacheLayout to get Cache-size.
     * 
     * @param blockSize     The size of one block in cache (bytes).
     * @param blockCount    The number of blocks in cache.
     * @param associativity The number of sets in cache.
     * @return cachesize    Cachesize of the specified cachelayout 
     */
    public int cachesize(int blockSize, int blockCount, int associativity)
    {
    	int cachesize = Cachelayout.cachesize(blockSize, blockCount, associativity);
    	return cachesize;
    }
    /**
     * Call CacheLayout to get Set-size
     * 
     * @param blockSize  Size of one block in the specified cachelayout
     * @param blockCount Number of blocks in one set in the specified cachelayout
     * @return setsize   Setsize of specified cachelayout
     */
    public int setsize(int blockSize, int blockCount)
    {
    	int setsize = Cachelayout.setsize(blockSize, blockCount);
    	return setsize;
    }
    /**
     * Method that call Database to store nickname.
     * 
     * @param nick   the string user entered as nickname
     */
    public void storeNickName(String nick)
    {
    SimulationDB.storeNickName(nick);
    }
    /**
     * Method that call database to store Index.
     * 
     * @param index  Index bits  in the specified addresslayout.
     */
    public void storeIndex(String index)
    {
    SimulationDB.storeIndex(index);
    }
    /**
     * Method that call database to store Offset.
     * 
     * @param offset  Offset bits in the specified addresslayout. 
     */
    public void storeOffset(String offset)
    {
    SimulationDB.storeOffset(offset);
    }


    /**
     * Method that call database to store Instruction.
     * 
     * @param  address  The address (hex) for the specified instruction.
     */
    public void storeInstruction(int address)
    {
    	SimulationDB.storeInstruction(address);
    }
    /**
     * Method that store what type of instruction that was executed
     * 
     * @param instrType    Type of Instruction (LOAD/STORE)
     */
    public void storeInstructionType(InstructionType instrType)
    {
    	SimulationDB.storeInstructionType(instrType);
    }
    
    /**
     * Method that call database to store CacheLayout
     * 
     * @param cachesize  The cachesize for the specified cachelayout
     * @param setsize    The setsize for the specified cachelayout.
     */
    public void storeCacheLayout(int cachesize, int setsize, int associativity)
    {
    	SimulationDB.storeCacheLayout(cachesize, setsize, associativity);
    }
    /**
     * Method that calls database to store blocksize
     * 
     * @param blockSize    Size of one block in cache
     */
    public void storeblockSize(int blockSize)
    {
    	SimulationDB.storeblockSize(blockSize);
    }
    /**
     * Method that calls database to store block number
     * 
     * @param blockNo   Number of a block in cache
     */
    public void storeBlockNo(int blockNo)
    {
    	SimulationDB.storeblockNo(blockNo);
    }
    /**
     * Method that calls database to store set number.
     * 
     * @param setNo   Number of a set in cache.
     */
    public void storeSetNo(int setNo)
    {
    	SimulationDB.storeSetNo(setNo);
    }
     /**
      * Method that calls database to store loaded offset
      * 
      * @param loadedOffset    Offset of instruction that have been loaded to Cache.
      */
    public void storeloadedOffset(int loadedOffset)
    {
    	SimulationDB.storeloadedOffset(loadedOffset);
    }
    /**
     * Method that call database to get index.
     * 
     * @return Index    Index bits in specified adresslayout.
     */
    public String getIndex()
    {
    	String Index = SimulationDB.getIndex();
    	return Index;
    }
    /**
     * Method that call database to get offset.
     * 
     * @return Offset  Offset bits in specified adresslayout.
     */
    public String getOffset()
    {
    	String Offset = SimulationDB.getOffset();
    	return Offset;
    }
    /**
     * Method that call database to get Instructions.
     * 
     * @return Instructions  List of stored Instructions
     */
    public ArrayList<Integer> getInstruction()
    {
    	 return SimulationDB.getInstruction();	
    }
    /**
     * Method that call database to get Number of load instructions
     * 
     * @return NoLoadInstructions   Number of load instructions stored.
     */
    public int getLoadInstructions()
    {
    	return SimulationDB.getLoadInstructions();
    }
    /**
     * Method that call database to get number of store instructions.
     * 
     * @return NoStoreInstructions   Number of store instructions stored.
     */
    public int getStoreInstructions()
    {
    	return SimulationDB.getStoreInstructions();
    }
    /**
     * Method that call database to get a list of users
     * 
     * @return Users   List of stored users.
     */
    public String getUsers()
    {
    	String User = SimulationDB.getUsers();
    	return User;
    }
    /**
     * Method that call database to get Cachesize
     * 
     * @return cachesize  stored Cachesize
     */
    public int getCachesize()
    {
    	return SimulationDB.getCachesize();
    }
    /**
     * Method that call database to get Setsize
     * 
     * @return Setsize   stored Setsize
     */
    public int getSetsize()
    {
    	return SimulationDB.getSetsize();
    }
    /**
     * Method that call database to get blockSize.
     * 
     * @return blockSize  Size of one block
     */
    public int getblockSize()
    {
    	return SimulationDB.getblockSize();
    }
    /**
     * Method that call database to get associativity
     * 
     * @return associativity  Numbers of set in cache.
     */
    public int getassociativity()
    {
    	return SimulationDB.getassociativity();
    }
    /**
     * Method that calls database to get stored Block Numbers
     * 
     * @return storedBlockNo    Stored block numbers of instructions.
     */
    public ArrayList<Integer> getBlockNo()
    {
    	return SimulationDB.getBlockNo();
    }
    /**
     * Method that calls database to get stored Set numbers.
     * 
     * @return storedSetNo  Stored Set numbers of instructions.
     */
    public ArrayList<Integer> getSetNo()
    {
    	return SimulationDB.getSetNo();
    }
    /**
     * Method that calls database to get offset of loaded instructions
     * 
     * @return loadedOffsets   List of offsets for loaded instructions
     */
    public ArrayList<Integer> getloadedOffset()
    {
    	return SimulationDB.getloadedOffset();
    }

    /**
     * Method that calls InstructionSimulation to calculate set number for specified instruction
     * 
     * @param blockNo             Blocknumber for specified instruction
     * @param address             Adress for specified instruction
     * @param InstructionDTO      Data needed for Instruction simulation
     * @return setNo              Setnumber for specified instruction.
     */
    public int setNo(InstructionDTO InstructionDTO, int address, int blockNo)
    {
    	 return Instructionsimulation.setNo(InstructionDTO, address, blockNo);	
    }
    /**
     * Method that call InstructionSimulation to find block number in cache.
     * 
     * @param adress         The adress(Hex) for the specified Instruction
     * @param InstructionDTO Data needed for InstructionSimulation    
     * @return blockNo       Block-number in the cache.
     */
    public int blockNo(int adress, InstructionDTO InstructionDTO)
    {
    	return Instructionsimulation.blockNo(adress, InstructionDTO);    	
    }
	 /**
	  * Method that call InstructionSimulation to find offset for the executed instruction.
	  * 
	  * @param adress             Adress for specified instruction
	  * @param InstructionDTO     Data needed for instructionsimulation
	  * @return instructionOffset Offset for executed instruction
	  */
	 public int instructionOffset(int adress, InstructionDTO InstructionDTO)
	 {
		 int instructionOffset = Instructionsimulation.instructionOffset
				 (adress, InstructionDTO);
		 return instructionOffset;
	 }
    /**
     * Method that call AdressLayout to find index, and also stores it in Database.
     * 
     * @param blockCount    Number of blocks in the specified Cachelayout
     * @param associativity Number of sets in the specified Cachelayout.
     * @return index        Index bits in specified AdressLayout
     */
    public String index(int blockCount)
    {
    	String index = Adresslayout.index(blockCount);
    	storeIndex(index);
    	return index;
    }
    /**
     * Method that call AdressLayout to find offset, and also stores it in database.
     * 
     * @param blockSize   Size of one block in the specified CacheLayout.
     * @return offset     Offset bits in specified AdressLayout
     */
    public String offset(int blockSize)
    {
    	String offset = Adresslayout.offset(blockSize);
    	storeOffset(offset);
    	return offset;
    }
	/**
	 * Method that call Instructionsimulation to calculate whether the Instruction hit or miss.
	 * If the instruction missed and there were a block loaded at the same position
	 * as the newly loaded block, the old block and the instruction that loaded
	 *  the old block is removed.
	 *  
	 *
	 * @param blockNo            block number for this instruction
	 * @param setNo              set number for this instruction
	 * @param address            the address(hex) for specified instruction
	 * @param InstructionDTO     Data needed for instructionsimulation.
	 * @return hitormiss         Indicates wether instruction hit or missed the cache.
	**/
    public int missorhit(int blockNo, int setNo, int address, int instructionOffset,
 		   InstructionDTO InstructionDTO)
    {	
    	return Instructionsimulation.hitormiss(blockNo,setNo, address, instructionOffset,
    				InstructionDTO);
    }
    /**
     * Method that call InstructionSimulation to find hitrate.
     * 
     * @return hitrate   hitrate of executed instructions.
     */
    public double hitRate()
    {
    	return Instructionsimulation.hitRate();
    }
    /**
     * Method that call InstructionSimulation to find missrate.
     * 
     * @return missrate    missrate of executed instructions.
     */
    public double missRate()
    {
    	return Instructionsimulation.missRate();
    }

    /**
     * Method that call database to return InstructionDTO
     * 
     * @return	InstructionDTO     Object containing data for instructionsimulation
     */
    public InstructionDTO getInstructionDTO()
    {
    	SimulationDB.InstructionDTO();
    	return SimulationDB.getIntructionDTO();
    }
    /**
     * Method that  call Instructionsimulation to add a observer object
     * @param observer	Some object that want to be notified with state-updates in the simulation
     */
	public void addObserver(Observer observer)
	{
		Instructionsimulation.addObserver(observer);
	}

	/**
	 * Method that call Instructionsimulation to check if the address is is valid.
	 * 
	 * @param address						Address for a specified instruction.
	 * @throws OperationFailedException		Throws OperationFailedException if address was not valid	
	 */
	public void checkAddress(int address) throws OperationFailedException
	{
		try{
		Instructionsimulation.checkAddress(address);
		}
		catch(IllegalAdressException IllegalAdressException){
    		throw new OperationFailedException("Could not get blockNo",
    	IllegalAdressException);
    	}
	}
	
	
}
