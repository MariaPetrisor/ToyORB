
public class InfoMarketImpl implements InfoMarket
{


	public String get_road_info(int road_ID) {
			if(road_ID == 1){
				return "Polei";
			}
			else if(road_ID == 2){
				return "Conditii de ploaie";
			}
			else {
				return "Ok";
			}
				
		}

	
	public double get_temp(String city){
		 if(city.equals("Timisoara")){
			 return 28.9;
		 }
		 else if(city.equals("Bucuresti")){
			 return 27.6;
		 }
		 else{
			 return 28;
		 }
	}
}
