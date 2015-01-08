package cachesim.model;

import java.util.ArrayList;
import cachesim.DTO.InstructionDTO;

/**
 * The <code>InstructionResult<code> class
 * contains methods to simulate the result of a LOAD or STORE instruction.
 */

class InstructionResult 
{
private int misses;
private int hits;

/**
 * Constructs a <code>InstructionResult<code> object.
 */
InstructionResult()
{
	
}

/**
 * Method that calculates whether the Instruction hit or miss.
 * If the instruction missed and there were a block loaded at the same position
 * as the newly loaded block, the old block and the instruction that loaded
 *  the old block is removed.
 *  
	 * @param blockNo            block number for this instruction
	 * @param setNo              set number for this instruction
	 * @param address            the address(hex) for specified instruction
	 * @param instructionOffset  offset for this instruction
	 * @param offsetlength       number of bits used for offset
	 * @param InstructionDTO     Data needed for instructionsimulation.
	 * @return hitormiss         Indicates wether instruction hit or missed the cache.
 */

int hitormiss(int blockNo, int setNo, int address, int instructionOffset,InstructionDTO InstructionDTO)
{
	ArrayList<Integer> storedInstructions = InstructionDTO.getInstructions();
	ArrayList<Integer> loadedOffsets = InstructionDTO.getLoadedOffsets();
	ArrayList<Integer> storedBlockNo = InstructionDTO.getBlockNo();
	ArrayList<Integer> storedSetNo = InstructionDTO.getSetNo();
	int blockSize = InstructionDTO.getBlockSize();
	int offsetlength = InstructionDTO.getoffsetlength();
	
	int miss = 1;
	int hit = 2;
	String hexadress = Integer.toHexString(address);
	int intadress = Integer.parseInt(hexadress, 16);
	
	for (int i = 0; i < storedInstructions.size(); i++)
	{
		int instruction = storedInstructions.get(i);
		int loadedblockOffset = loadedOffsets.get(i);
		int temp = blockSize - loadedblockOffset;
		int upper = instruction + temp;
		int lower = instruction - loadedblockOffset;
		
		if(lower <= intadress && intadress <= (upper))
		{
			for(int j = 0; j< storedBlockNo.size(); j++)
			{
		int temp1 = storedBlockNo.get(j);
		int temp2 = storedSetNo.get(j);
		if (temp1 == blockNo && temp2 == setNo)
		{
			hits = hits +1;
			return hit;
		}
			}
		}
	}
	
	for (int i = 0; i<storedBlockNo.size();i++)
	{
		int temp1 = storedBlockNo.get(i);
		int temp2 = storedSetNo.get(i);
		if(temp1 == blockNo && temp2 == setNo)
		{
			storedBlockNo.remove(i);
			storedSetNo.remove(i);
			storedInstructions.remove(i);
			loadedOffsets.remove(i);
		}

	}
	misses = misses+1;
	return miss;
	
}

/**
 * Calculates missrate.
 * 
 * @return missrate      missrate of executed instructions
 */
double missRate()
{
	int total = misses + hits;
	double missRate = Math.round(misses * 100.0/total);
	return missRate;
}
/**
 * Calculates hitrate
 * 
 * @return hitrate       hitrate of executed instructions.
 */
double hitRate()
{
	int total = misses + hits;
	double hitRate = Math.round(hits * 100.0/total);
	return hitRate;
}


}
