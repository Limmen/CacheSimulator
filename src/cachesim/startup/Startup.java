package cachesim.startup;
import cachesim.controller.*;  
import cachesim.datastore.*;
import cachesim.view.*;




	/**
	 * The start sequence of the entire application.
	 */
	public class Startup {

	    /**
	     * The application's Main method.
	     * 
	     * @param args This application takes no command line arguments
	     */
	    public static void main(String[] args) {
	    	
	    	Controller contr = new Controller(SimulationDB.getSimulationDB());
	        new MainPanel(contr).simulateCache();
	       
	    }
	    
	}


		