package RequestReply;

import Commons.*;

import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;


public class Requestor
{


    public byte[] deliver_and_wait_feedback(Address theDest, byte[] data) {

           // ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    	    byte[] buffer = null;
            int val;
            try {
                    Socket s = new Socket(theDest.dest() , theDest.port());

                    //deliver
                    OutputStream oStr = s.getOutputStream();
                    int n = data.length;
                    byte[] dataLength = ByteBuffer.allocate(4).putInt(n).array();
                    oStr.write(dataLength);
                    oStr.write(data);
                    oStr.flush();

                    //read feedback
                    InputStream iStr = s.getInputStream();
                    byte[] byteLength = new byte[4];
                    iStr.read(byteLength);
                    n = ByteBuffer.wrap(byteLength).getInt();
                    buffer = new byte[n];
                    iStr.read(buffer);
                    
     

                    iStr.close();
                    oStr.close();
                    s.close();
            } catch (IOException e) {
                    return null;
            }

            return buffer;
    }
}



