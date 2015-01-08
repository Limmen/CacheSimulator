package cachesim.controller;

public class OperationFailedException extends Exception 
{

	public OperationFailedException(String msg, Exception rootCause)
	{
		super("Operation Failed");
	}
}
