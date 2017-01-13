package ToyORB;


import java.lang.reflect.Constructor;

import Commons.*;
import MessageMarshaller.*;
import RequestReply.Replyer;
import RequestReply.Requestor;
import Server.*;


public class ToyORBImpl
{
    public static String NamingAddress="localhost"; 
    public static int NamingPort=5000;
    private static Replyer rep;
    
    
    private static Replyer getReplier() {
    	Address address = new AddressImpl("localhost",0);

        if (rep == null) {
                rep = new Replyer(address.port());
                
                Thread newThread = new Thread(new Runnable() {
                        public void run() {
                                while (true) 
                                {
                                	rep.receive_transform_and_send_feedback(ServerProxyGeneric.getInstance());
                                }

                        }
                });
                newThread.start();
        }
        return rep;
}

    public static void register(Object o , String name)
    {
    	
            Requestor r = new Requestor();
            Marshaller marsh = new Marshaller();
            
            ServerProxyGeneric.getInstance().register(name, o);
            Address a = (AddressImpl) getReplier().getAddress();
            NamingMessage m = new  NamingMessage("register", name, a);

            byte[] answer = r.deliver_and_wait_feedback(new AddressImpl( "localhost", NamingPort), marsh.marshall(m));
            
            if(answer == null){
            	System.out.println("Eroare la inregistrare \n");
            }
    }
    
    
    public static Object getObjectReference(String name)  
    {

	    Requestor req = new Requestor();
	    
	    Marshaller marshaller = new Marshaller();
	
	    NamingMessage m = new  NamingMessage("getAddress", name, null);
	
	    byte[] answer = req.deliver_and_wait_feedback(new AddressImpl(NamingAddress, NamingPort), marshaller.marshall(m));
	
	    m = (NamingMessage) marshaller.unmarshall(answer);
	
	    String clName = m.name + "ClientProxy";
	    
	    Class<?> proxyClass;
	    Object objectReference = null;
	    try {
	            proxyClass = Class.forName(clName);
	            Constructor<?> constructor = proxyClass.getConstructor(new Class[] {String.class, Address.class });
	
	            objectReference = constructor.newInstance(m.name, m.address);
	    } catch (ClassNotFoundException e) {
	    	    e.printStackTrace();
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
	    return objectReference;

}
	
	
}