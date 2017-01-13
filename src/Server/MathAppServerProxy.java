package Server;

import java.io.Serializable;
import java.lang.reflect.Method;

import MessageMarshaller.*;
import RequestReply.ByteStreamTransformer;


public class MathAppServerProxy implements ByteStreamTransformer{

	
	private int operation = 0;
	
	@Override
	public byte[] transform(byte[] in) {
		
		
		Class marketClass = null;
		try {
			marketClass = Class.forName("MathAppImpl");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Marshaller marshaller = new Marshaller();
		ServerMessage m = (ServerMessage) marshaller.unmarshall(in);
		
		
		float a = 0f, b = 0f;
		double sqrt = 0 ;
		
		if(m.methodName.equals("do_add")){
			operation = 0;
			a = (Float)m.parameters[0];
			b = (Float)m.parameters[1];
			
		}
		else if(m.methodName.equals("do_sqrt")){
			operation = 1;
			sqrt = (Double)m.parameters[0];
		}
		
		Method method;
		Serializable returnValue = null;
		switch(this.operation){
		case 0:
			 try {
				method = marketClass.getMethod("do_add", new Class[] { float.class, float.class });
				returnValue = (Serializable) method.invoke(marketClass.newInstance(), new Object[]{a,b});
			} catch (Exception e) {
				e.printStackTrace();
			}
             
			break ;
		case 1:
			try {
				method = marketClass.getMethod("do_sqrt", new Class[] { double.class });
				returnValue = (Serializable) method.invoke(marketClass.newInstance(), new Object[]{sqrt});
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
