package cachesim.model;
import cachesim.util.InstructionType; 
import cachesim.DTO.InstructionDTO;

import java.util.ArrayList; 
import java.util.List;
/**
 * The <code>InstructionSimulation<code> class
 * contains methods to simulate a LOAD or STORE instruction in the cache.
 */

public class InstructionSimulation 
{
	private int count;
	private List<Observer> observers = new ArrayList<Observer>();
	private InstructionResult InstructionResult;
	
	/**
	 * Constructs a <code>InstructionSimulation<code> object.
	 */
	public InstructionSimulation()
	{
		InstructionResult();
	}
	/**
	 * Creates a <code>InstructionResult<code> object.
	 */
	void InstructionResult()
	{
		InstructionResult = new InstructionResult();
	}
	/**
	 * Add a observer (view) to list of observers
	 * 
	 * @param observer  Some object that want to be notified with state-updates in the simulation
	 */
	public void addObserver(Observer observer)
	{
	
		observers.add(observer);
	}
	/**
	 * Notifies Observers that some state have been updated (Instructions in this case)
	 * 
	 * @param count      Number of the Instruction
	 * @param hitormiss	 Informative message about the executed instruction (Hit or miss)
	 */
	private void notifyObservers(int count, String hitormiss)
	{
		for (Observer observer : observers)
		{
			observer.newhitormiss(count, hitormiss);
		}
	}
	/**
	 * Method that check if the address is valid
	 * 
	 * @param address					Address for a specified Instruction
	 * @throws IllegalAdressException	Throws exception if address is not valid
	 */
	public void checkAddress(int address) throws IllegalAdressException
	{
		if ((address & 3) != 0)
		{
			throw new IllegalAdressException(address);
		}
	}
	/**
	 * Calculates to what set number in cache the new block should be loaded.
	 * 
     * @param blockNo             Blocknumber for specified instruction
     * @param address             Adress for specified instruction
     * @param InstructionDTO      Data needed for Instruction simulation
     * @return setNo              Setnumber for specified instruction.
	 */
	public static int setNo(InstructionDTO InstructionDTO, int adress, int blockNo)
	{
		int setNo = 0;
		int set = 0;

		
		String hexadress = Integer.toHexString(adress);
		int intadress = Integer.parseInt(hexadress, 16);
		
		ArrayList<Integer> storedBlockNo = InstructionDTO.getBlockNo();
		ArrayList<Integer> storedSetNo = InstructionDTO.getSetNo();
		ArrayList<Integer> storedInstructions = InstructionDTO.getInstructions();
		ArrayList<Integer> loadedOffsets = InstructionDTO.getLoadedOffsets();
		int blockSize = InstructionDTO.getBlockSize();
		int associativity = InstructionDTO.getassociativity();
		
		for (int i = 0; i< storedBlockNo.size(); i++)
		{
			int block = storedBlockNo.get(i);
			 set = storedSetNo.get(i);
			int instruction = storedInstructions.get(i);
			int loadedblockOffset = loadedOffsets.get(i);
			int temp = blockSize - loadedblockOffset;
			int upper = instruction + temp;
			int lower = instruction - loadedblockOffset;

			if(block == blockNo && lower <= intadress && intadress <= upper )
				return set;
			
			if(block == blockNo)
					{
						setNo = set +1;
						if(setNo  > (associativity-1))
						setNo = 0;
						return setNo;
					}
			return set;
			}
		
		return set;
	}
	
	/**
	 * Calculates to what block number in cache the new block should be loaded.
	 * 
	 * @param adress          The adress(hex) for specified instruction
	 * @param InstructionDTO  Data needed for instructionsimulation
	 * @return blockNo        blocknumber in cache where data should be loaded or read.
	 */
	public int blockNo(int adress, InstructionDTO InstructionDTO)
	{
		String index = InstructionDTO.getIndex();
		String offset= InstructionDTO.getOffset();
		int indexbinary = Integer.parseInt(index, 2);
		int offsetlength = offset.length();
		
		
		String hexadress = Integer.toHexString(adress);
		int intadress = Integer.parseInt(hexadress, 16);
		
		int blockNo = (intadress >> offsetlength) & (indexbinary);
		return blockNo;
		
	}
	 /**
	  * Calculates offset for the executed instruction.
	  * 
	  * @param adress             Adress for specified instruction
	  * @param InstructionDTO     Data needed for instructionSimulation
	  * @return instructionOffset Offset for executed instruction
	  */
	public int instructionOffset(int adress, InstructionDTO InstructionDTO)
	{
		String offset = InstructionDTO.getOffset();
		int offsetbinary = Integer.parseInt(offset,2);
		String hexadress = Integer.toHexString(adress);
		int intadress = Integer.parseInt(hexadress, 16);
		
		int instructionOffset = (intadress) & (offsetbinary);
		
		return instructionOffset;
		
	}
	/**
	 * Method that calls InstructonResult to find  whether the Instruction
	 * hit or miss.
	 *  
	 * @param blockNo            block number for this instruction
	 * @param setNo              set number for this instruction
	 * @param address            the address(hex) for specified instruction
	 * @param instructionOffset  offset for this instruction
	 * @param InstructionDTO     Data needed for instructionsimulation.
	 * @return hitormiss         Indicates wether instruction hit or missed the cache.
	 */
	public int hitormiss(int blockNo, int setNo, int address, int instructionOffset,
			InstructionDTO InstructionDTO)
	{
		int hitormiss = InstructionResult.hitormiss(blockNo,setNo, address, instructionOffset,
	    		   InstructionDTO);
		
		count = count +1;
		String newhitormiss = "HIT";
		if (hitormiss == 1)
			newhitormiss = "MISS";
		notifyObservers(count, newhitormiss);
	return hitormiss;	
	}
	/**
	 * Method that calls InstructionResult to find missrate
	 * 
	 * @return missrate      missrate of executed instructions
	 */
	public double missRate()
	{
		return InstructionResult.missRate();
	}
	/**
	 * Method that calls InstructionResult to find hitrate
	 * 
	 * @return hitrate       hitrate of executed instructions.
	 */
	public double hitRate()
	{	
		return InstructionResult.hitRate();
	}
	

		
}
