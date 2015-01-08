package cachesim.view;

import java.awt.BorderLayout;  
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cachesim.controller.*;
import cachesim.util.*;

import java.util.Date;

import cachesim.DTO.InstructionDTO;
import cachesim.model.Observer;
/**
 * Contains the entire simulator gui.
 */
public class MainPanel extends JPanel implements Observer{
    private CachePanel currentCachePanel;
    private Controller contr;
    
    /**
     * Creates a new <code>MainPanel</code> with all its child panels.
     */
    public MainPanel(Controller contr) {
    	this.contr = contr;
        createGui();
    }

    /**
     * Replaces the cache panel with a new panel that shows the specified cache layout.
     * 
     * CALL THIS METHOD TO CHANGE THE CACHE LAYOUT!! 
     *
     * @param blockSize     The size of a block in the cache.
     * @param blockCount    The number of blocks in each set in the cache.
     * @param associativity The number of sets in the cache.
     */
    void changeCacheLayout(int blockSize, int blockCount, int associativity) {
        remove(currentCachePanel);
        currentCachePanel = new CachePanel(blockSize, blockCount, associativity);
        add(currentCachePanel, BorderLayout.CENTER);
        revalidate();	
    }

    /**
     * Maps the cache set with the specified number to the specified address in main memory.
     * This method does not check if the set is free, the model should do that. It is unspecified
     * what happens if the set is not free. 
     * 
     * CALL THIS METHOD TO USE A SET! 
     *
     * @param setNo   The number of the set that shall be used.
     * @param address The address in main memory to which a free set is mapped.
     */
    void useCacheSet(int setNo, int address) {
        currentCachePanel.useSet(setNo, address);
    }

    /**
     * Indicate that there was a cache miss. 
     * 
     * CALL THIS METHOD TO DISPLAY A MISS!! 
     */
    void miss() {
        currentCachePanel.miss();
    }

    /**
     * Indicate that new data was loaded in the specified cache block. 
     * 
     * CALL THIS METHOD TO SHOW THAT A NEW VALUE WAS LOADED!! 
     *
     * @param setNo   The set where the new value was loaded.
     * @param blockNo The block where the new value was loaded.
     */
    void newValueInCache(int setNo, int blockNo) {
        currentCachePanel.newValueInCache(setNo, blockNo);
    }

    /**
     * Indicate the the specified word was read/written from/to the cache. 
     * 
     * CALL THIS METHOD TO SHOW THAT A VALUE WAS FOUND!! 
     *
     * @param setNo   The set that contains the word.
     * @param blockNo The block that contains the word
     * @param offset  The offset in the block to word.
     */
    void hit(int setNo, int blockNo, int offset) {
        currentCachePanel.hit(setNo, blockNo, offset);
    }
    
    private void createGui() {
        setLayout(new BorderLayout());
        add(new SettingsPanel(this), BorderLayout.NORTH);
        currentCachePanel = new CachePanel();
        add(currentCachePanel, BorderLayout.CENTER);
        add(new ExecutePanel(this), BorderLayout.SOUTH);
        

    }

    /**
     * Method to Initialize the program and the simulation.
     * Asks user for nickname.
     */    
    public void simulateCache()
   {
    	contr.makeNewSimulation();
    	
        String nick = new NickNameDialog().getNickName();
        
        JFrame frame = new JFrame("Cache Simulator - " + nick);
        frame.setContentPane(new MainPanel(contr));
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
        
        contr.storeNickName(nick);
        contr.addObserver(this);
    } 
    /**
     * This method is called when the user clicks the "Apply" button.
     * This method call controller to get cachesize. It also calls controller
     * to store index, offset, blocksize, cachelayout(setsize, cachesize).
     * 
     * @param blockSize     Size of one block in the cache
     * @param blockCount    Number of blocks in one set
     * @param associativity Number of sets in the cache
     * @return cachesize    Cachesize of specified cachelayout
     */
    int applySettings(int blockSize, int blockCount, int associativity)
    {
    	int cachesize = 0;
    	changeCacheLayout(blockSize, blockCount, associativity);
    	contr.index(blockCount);
    	contr.offset(blockSize);
    	
    	cachesize = contr.cachesize(blockSize, blockCount, associativity);
    	int setsize = contr.setsize(blockSize, blockCount);
    	if((cachesize + setsize)!= 0)
    	contr.storeCacheLayout(cachesize, setsize, associativity); 
    	contr.storeblockSize(blockSize);
    		
    	return cachesize;
    }
    
    /**
     * This method is called when the user clicks "Execute".
     * This method checks if the address in valid.
     * This method calls controller to simulate the specified instruction.
     * This method stores instruction, blockNo and setNo of newly loaded blocks.
     * 
     * @param instrType     Instructiontype (LOAD/STORE)
     * @param address       Address(hex) of specified instruction
     */
    void simulateInstruction(InstructionType instrType, int address )
    {    	
    	InstructionDTO InstructionDTO = contr.getInstructionDTO();
    	try
    	{
    		contr.checkAddress(address);
    		int blockNo = contr.blockNo(address, InstructionDTO);
    		int setNo = contr.setNo(InstructionDTO, address, blockNo);
        	int instructionOffset = contr.instructionOffset(address, InstructionDTO);

      	  	String hexadress = Integer.toHexString(address);
        	int intadress = Integer.parseInt(hexadress, 16);
            
           int missorhit = contr.missorhit(blockNo,setNo, address, instructionOffset,
        		   InstructionDTO);
        	
        	if (missorhit == 2)
        	{
        		hit(setNo, blockNo, instructionOffset);
        	}
        	 
        	if (missorhit== 1) 
        	{ 
        		useCacheSet(setNo, address);
        		contr.storeInstruction(intadress);
            	contr.storeBlockNo(blockNo);
            	contr.storeSetNo(setNo);
                contr.storeloadedOffset(instructionOffset);
        		newValueInCache(setNo, blockNo);
        		miss(); 
        	}
        	
        	contr.storeInstructionType(instrType);
        	
    	}
    	catch(Exception OperationFailedException)
    	{
    		System.out.println("Operation Failed");	
    	}
    	
    	}
    	
    
    /**
     * This method is called when the user clicks "End Simulation"
     * This method will display stored data
     */
    void Display()
    { 
    System.out.println("Stored Data");
    
    System.out.println("Date and time: ");
    Date date = new Date();
    System.out.println(date);
    System.out.println();
   
    System.out.println("User: ");	
    String User = contr.getUsers();
    System.out.println(User);
    System.out.println();
    
    System.out.println("CacheLayout");
    int cachesize = contr.getCachesize();
    System.out.println("Cachesize: " + cachesize + "bytes");
    int setsize = contr.getSetsize();
    System.out.println("Setsize: " + setsize + "bytes");
    int sets = contr.getassociativity();
    System.out.println("Number of sets: " + sets );
    
    System.out.println();
    System.out.println("AdressLayout ");
    int indexlength = contr.getIndex().length();
    System.out.println("Index:" + indexlength + "bits");
    int offsetlength = contr.getOffset().length();
    System.out.println("Offset" + offsetlength + "bits");
    
    System.out.println();
    System.out.println("Number of LOAD instructions executed: " + contr.getLoadInstructions());
    System.out.println("Number of STORE instructions executed: " + contr.getStoreInstructions());
    
    
    System.out.println();
    System.out.println("Hitrate: ");
    double hitRate = contr.hitRate();
    System.out.println(hitRate + "%");
    System.out.println("Missrate: ");
    double missRate = contr.missRate();
    System.out.println(missRate + "%");
    
    
    }
   /**
    * Displays a info-message when new Instructions are executed
    * 
    * @param count	      Number of instructions
    * @param newhitormiss Info about the executed Instruction (hit or miss)
    */
    public void newhitormiss(int count, String newhitormiss)
    {
    	System.out.println("Instruction no " + count + " was a " 
    			+ newhitormiss);
    }

    
    
    
}
