package cachesim.model;

public class IllegalAdressException extends Exception
{
private int address;

	
public IllegalAdressException(int address)
{
	super("Attempt to fetch Illegal Address: " + address + " Address needs to be divisible with 4");
	this.address = address;
}
		
}
