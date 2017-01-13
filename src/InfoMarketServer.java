
import ToyORB.*;

public class InfoMarketServer
{

    public static void main(String[] args) 
    {
        
        
  		InfoMarket info = new InfoMarketImpl();
  	    
  	    
            try {
	    			
                    ToyORBImpl.register(info,"InfoMarket");
            		
                    
            } catch (Exception e) {
                    e.printStackTrace();
            }

    }
}