package cachesim.model;


/**
 * This Interface is used by objects that want to be notified about changes
 * in the ongoing Cachesimulation.
 * @author Kim
 *
 */
public interface Observer 
{
  /**
   * Displays a info-message when new Instructions are executed
   * 
   * @param count	      Number of instructions
   * @param newhitormiss Info about the executed Instruction (hit or miss)
   */
	void newhitormiss(int count, String newhitormiss);	
}
