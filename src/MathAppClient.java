
import ToyORB.ToyORBImpl;

public class MathAppClient
{
	
	public static  void main(String[] args)
	{
		
		
		try {
			
            MathApp MA = (MathApp) ToyORBImpl.getObjectReference("MathApp");
            System.out.println("MathApp 10.3 + 11.4 = " + MA.do_add(10.3f,11.4f));
            System.out.println("MathApp sqrt(50) = "+ MA.do_sqrt(50));
		} catch (Exception e) {
            e.printStackTrace();

    }
    

			
	}
	
}