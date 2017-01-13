package ToyORB;

import java.util.TreeMap;
import Commons.*;
import MessageMarshaller.*;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

public class Naming implements ByteStreamTransformer 
{
	private static TreeMap<String, Address> objects = new TreeMap<String, Address>();

    public synchronized static void addObject(String name, Address a) {
            objects.put(name, a);
    }

    public synchronized static Address getAddress(String name) {
            return objects.get(name);
    }
    
    public byte[] transform(byte[] in) {
    	 Marshaller marsh = new Marshaller();
         NamingMessage m = (NamingMessage) marsh.unmarshall(in);
         
         Address address = null; 
         
         if(m.operation.equals("register")){
        	 Naming.addObject(m.name, m.address);
        	 String s = "ok";
        	 return s.getBytes();
         }
         
         else if(m.operation.equals("getAddress")){ 
        	 address = Naming.getAddress(m.name);
             if (address != null) {
                     m.address = address;
             }
             return marsh.marshall(m);
         }

         return null;
        }

        public static void main(String[] args)
        {
                Naming namingService = new Naming();
                Replyer r = new Replyer(5000);
                System.out.println("Naming service started!");
                while (true) {
                        r.receive_transform_and_send_feedback(namingService);
                }

        }

}
