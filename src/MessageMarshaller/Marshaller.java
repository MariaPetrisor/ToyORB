package MessageMarshaller;

import java.io.*;
import java.lang.String;

public class Marshaller
{
	public byte[] marshall(Message m) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = null;
        
                try {
					output = new ObjectOutputStream(bytes);
					output.writeObject(m);
	                output.flush();
	                output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                
        return bytes.toByteArray();
}

public Message unmarshall(byte[] b) {
        ByteArrayInputStream bytes = new ByteArrayInputStream(b);
        ObjectInputStream input = null;
        Message m = null;
        try {
                input = new ObjectInputStream(bytes);
                m = (Message) input.readObject();
                input.close();
        } catch (IOException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return m;
}


public byte[] marshal(Message theMsg)
{

    if(theMsg instanceof NamingMessage){

        String m = " :1:";
        m = m + ((NamingMessage)theMsg).operation + ":";
        m = m + ((NamingMessage)theMsg).name + ":";
        m = m + ((NamingMessage)theMsg).address;

        byte b[] = new byte[m.length()];
        b = m.getBytes();
        b[0] = (byte)m.length();
        return b;
    }
    else if(theMsg instanceof ServerMessage){

        String m = " :2:";
        m = m + ((ServerMessage)theMsg).name + ":";
        m = m + ((ServerMessage)theMsg).methodName + ":";
        m = m + ((ServerMessage)theMsg).parameters + ":";
        m = m + ((ServerMessage)theMsg).returnedValue;

        byte b[] = new byte[m.length()];
        b = m.getBytes();
        b[0] = (byte)m.length();
        return b;
    }
    
    return null;
}

}

