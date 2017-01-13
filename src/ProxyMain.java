import java.io.IOException;

public class ProxyMain
{
	
	public static  void main(String[] args) throws ClassNotFoundException, IOException
	{	

				ProxyGenerator m1 = new ProxyGenerator("InfoMarket");
	    		m1.createFile();
	    		ProxyGenerator m2 = new ProxyGenerator("MathApp");
	    		m2.createFile();
	    		System.out.println("Files created \n");
	    			
	}
}