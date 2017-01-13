package Commons;

import java.io.Serializable;


public class AddressImpl implements Address, Serializable 
{
	private String destinationId;
	private int portNr;
	public AddressImpl(String theDest, int thePort)
	{
		destinationId = theDest;
		portNr = thePort;
	}
	public String dest()
	{
		return destinationId;
	}
	public int port()
	{
		return portNr;
	}
	
}