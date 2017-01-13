package Server;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Hashtable;

import MessageMarshaller.Marshaller;
import MessageMarshaller.ServerMessage;
import RequestReply.ByteStreamTransformer;


public class ServerProxyGeneric implements ByteStreamTransformer 
{
	
	 private Hashtable<String,Object> objects = new Hashtable<String,Object>();
     private static ServerProxyGeneric instance;

     public static ServerProxyGeneric getInstance() {
             if (instance == null) {
                     instance = new ServerProxyGeneric();
             }
             return instance;
     }

     public void register(String name, Object obj) {
             objects.put(name, obj);
     }
    
     public Serializable callMethod(String name, String methodName, Serializable[] parameters)
     {
    	 
     Serializable returnValue = null;

     Object o = objects.get(name);
     if (o == null) {
             System.out.println("Nu exista un astfel de obiect " + name);
             return null;
     }

     Class<?>[] parameterTypes = new Class[parameters.length];
     for (int i = 0; i < parameters.length; i++) {
             try {
            	 	//obtinerea tipului primitiv din clasa infasuratoare
                     parameterTypes[i] = (Class<?>) parameters[i].getClass().getDeclaredField("TYPE").get(null);
                     
             } catch (Exception e) 
             {
            	 	//cazul in care obiectul nu este infasurator
                     parameterTypes[i] = parameters[i].getClass();
                     
             }

     }

     Method m = null;
     try {
             m = o.getClass().getMethod(methodName, parameterTypes);
             returnValue = (Serializable) m.invoke(o, (Object[]) parameters);
     } catch (Exception e) {
             e.printStackTrace();
     }

     return returnValue;
}

public byte[] transform(byte[] in) 
{
     Marshaller marshaller = new Marshaller();
     ServerMessage m = (ServerMessage) marshaller.unmarshall(in);
     m.returnedValue = this.callMethod(m.name, m.methodName, m.parameters);
     return marshaller.marshall(m);   
}

}