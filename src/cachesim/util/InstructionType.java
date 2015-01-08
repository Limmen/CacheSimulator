package cachesim.util;

/**
 * The instructions that can be executed by the simulator.
 */
public enum InstructionType {
    /**
     * The store instruction stores four bytes to the specified address in main memory.
     */
    STORE,
    /**
     * The load instruction loads four bytes from the specified address in main memory.
     */
    LOAD
}
