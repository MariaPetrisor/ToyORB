
import ToyORB.ToyORBImpl;

public class InfoMarketClient
{
	
	public static  void main(String[] args)
	{
		
		
		try {
			
            InfoMarket IS = (InfoMarket) ToyORBImpl.getObjectReference("InfoMarket");
            System.out.println("InfoMarket temperature in Timisoara = "+IS.get_temp("Timisoara"));
            System.out.println("InfoMarket road info for road_ID 1 = "+IS.get_road_info(1));
		} catch (Exception e) {
            e.printStackTrace();

    }
    

			
	}
	
	
}