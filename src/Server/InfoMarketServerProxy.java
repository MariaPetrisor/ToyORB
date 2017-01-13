package Server;

import java.io.Serializable;
import java.lang.reflect.Method;

import MessageMarshaller.*;
import RequestReply.ByteStreamTransformer;


public class InfoMarketServerProxy implements ByteStreamTransformer{

	
	private int operation = 0;
	
	@Override
	public byte[] transform(byte[] in) {
		
		
		Class marketClass = null;
		try {
			marketClass = Class.forName("InfoMarketImpl");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Marshaller marshaller = new Marshaller();
		ServerMessage m = (ServerMessage) marshaller.unmarshall(in);
		
		
		int road_ID = 0;
		String city = "";
		
		if(m.methodName.equals("get_road_info")){
			operation = 0;
			road_ID = (Integer)m.parameters[0];
			
		}
		else if(m.methodName.equals("get_temp")){
			operation = 1;
			city = (String)m.parameters[0];
		}
		
		Method method;
		Serializable returnValue = null;
		switch(this.operation){
		case 0:
			 try {
				method = marketClass.getMethod("get_road_info", new Class[] { int.class });
				returnValue = (Serializable) method.invoke(marketClass.newInstance(), new Object[]{road_ID});
			} catch (Exception e) {
				e.printStackTrace();
			}
             
			break ;
		case 1:
			try {
				method = marketClass.getMethod("get_temp", new Class[] { String.class });
				returnValue = (Serializable) method.invoke(marketClass.newInstance(), new Object[]{city});
			} catch (Exception e) {
				e.printStackTrace();
			}
            break ;
		default:
			System.out.println("Incorrect command inside Transformer!");
		}
		
		 m.returnedValue = returnValue;
		 return marshaller.marshall(m);
	}
}
