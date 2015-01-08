package cachesim.DTO;

import java.util.ArrayList;
/**
 * This is a DTO-class (Data transfer object).
 * This DTO class contains data needed for InstructionSimulation.
 * @author Kim
 *
 */
public class InstructionDTO 
{
private String index;
private String offset;
private int offsetlength;

private int blockSize;
private int associativity;
private ArrayList<Integer> storedBlockNo;
private ArrayList<Integer> storedSetNo;
private ArrayList<Integer> storedInstructions;
private ArrayList<Integer> loadedOffsets;

/**
 * Constructs a <code>InstructionDTO<code> object.
 * 
 * @param index                index of specified AdressLayout
 * @param offset			   offset of specified AdressLayout
 * @param blockSize			   blockSize of specified CacheLayout
 * @param associativity		   associativity of specified CacheLayout
 * @param storedBlockNo		   Blocknumbers of loaded blocks
 * @param storedSetNo          Setnumbers of loaded blocks 
 * @param storedInstructions   Adress of stored instructions
 * @param loadedOffsets		   Offset of loaded instructions
 */
 public InstructionDTO(String index, String offset, int blockSize, int associativity,
		 ArrayList<Integer> storedBlockNo, ArrayList<Integer> storedSetNo, 
		 ArrayList<Integer> storedInstructions, ArrayList<Integer> loadedOffsets)
{
	 this. index = index;
	 this.offset = offset;
	 this.blockSize = blockSize;
	 this.associativity = associativity;
	 this.storedBlockNo = storedBlockNo;
	 this.storedSetNo = storedSetNo;
	 this.storedInstructions = storedInstructions;
	 this.loadedOffsets = loadedOffsets;
}
 /**
  * Method to return index
  * 
  * @return index  index of specified AdressLayout
  */
 public String getIndex()
 {
	 return index;
 }
 /**
  * Method to return offset
  * 
  * @return offset of specified AdressLayout
  */
 public String getOffset()
 {
	 return offset;
 }
 /**
  * Method to return offsetlength
  * 
  * @return offsetlength  Number of bits used for offset in specified AdressLayout
  */
 public int getoffsetlength()
 {
	 offsetlength = offset.length();
	 return offsetlength;
 }
 /**
  * Method to return blockSize
  * 
  * @return blockSize    blocksize of specified CacheLayout
  */
 public int getBlockSize()
 {
	 return blockSize;
 }
 /**
  * Method to return associativity
  * 
  * @return associativity
  */
 public int getassociativity()
 {
	 return associativity;
 }
 /**
  * Method to return blocknumbers of loaded blocks
  * 
  * @return storedBlockNo    Blocknumbers of loaded blocks
  */
 public ArrayList<Integer> getBlockNo()
 {
	 return storedBlockNo;
 }
 /**
  * Method to return setnumber of loaded blocks
  * 
  * @return storedSetNo  setnumber of loaded blocks
  */
 public ArrayList<Integer> getSetNo()
 {
	 return storedSetNo;
 }
 /**
  * Method to return storedInstructions
  * 
  * @return storedInstructions   Adress of stored instructions
  */
 public ArrayList<Integer> getInstructions()
 {
	 return storedInstructions;
 }
 /**
  * Method to return offset of executed instructions
  * 
  * @return loadedOffsets  Offset of loaded instructions
  */
 public ArrayList<Integer> getLoadedOffsets()
 {
	 return loadedOffsets;
 }
 
}

