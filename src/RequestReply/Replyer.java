package RequestReply;

import Commons.*;

import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;

public class Replyer
{
	 private ServerSocket srvS;

     public Replyer(int port) {

             try {
                     srvS = new ServerSocket(port, 1000);
             } catch (Exception e) {
                     System.err.println("ServerSocket not created!");
                     e.printStackTrace();
                     System.exit(1);
             }
     }

     public void receive_transform_and_send_feedback(ByteStreamTransformer t) {

             byte[] buffer;
             try {
                     Socket s = srvS.accept();

                     //receive
                     InputStream iStr = s.getInputStream();
                     int dataLength = 0;
                     byte[] byteLength = new byte[4];
                     iStr.read(byteLength);
                     dataLength = ByteBuffer.wrap(byteLength).getInt();
                     buffer = new byte[dataLength];
                     iStr.read(buffer);
                     
                     
                     //transform
                     byte[] data = t.transform(buffer);
                     
                     //send feedback
                     dataLength = data.length;
                     byte[] dataLen = ByteBuffer.allocate(4).putInt(dataLength).array();
                     OutputStream oStr = s.getOutputStream();
                     oStr.write(dataLen);
                     oStr.write(data);
                     oStr.flush();
                     
                     
                     
                     oStr.close();
                     iStr.close();
                     s.close();

             } catch (IOException e) {
                     System.out.println("Exceptie la receive_transform_and_feedback");
             }

     }

     public Address getAddress() {
             return new AddressImpl(srvS.getInetAddress().getHostAddress(), srvS.getLocalPort());
     }

     protected void finalize() throws Throwable {
             super.finalize();
             srvS.close();
     }
}
