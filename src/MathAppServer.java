
import ToyORB.*;

public class MathAppServer
{

    public static void main(String[] args) 
    {
        
        
  		MathApp math = new MathAppImpl();
  	    
  	    
            try {
	    			
                    ToyORBImpl.register(math,"MathApp");
            		
                    
            } catch (Exception e) {
                    e.printStackTrace();
            }

    }
}