import java.io.Serializable;
import MessageMarshaller.Marshaller;
import MessageMarshaller.ServerMessage;
import Commons.*;
import RequestReply.Requestor;

public class MathAppClientProxy implements MathApp{

private String name;
private Address address;
public MathAppClientProxy(String name, Address address){
	this.name = name;
	this.address = address;
 }
public float do_add(float arg0,float arg1)
{
	Marshaller marshaller = new Marshaller();
	Serializable[] parameters = new Serializable[] { arg0, arg1 };
	ServerMessage m = new ServerMessage(name, "do_add" , parameters, null);
	Requestor r = new Requestor();
	byte[] answer = r.deliver_and_wait_feedback(address, marshaller.marshall(m));
	m = (ServerMessage) marshaller.unmarshall(answer);
	
	return (float) m.returnedValue;
}

public double do_sqrt(double arg0)
{
	Marshaller marshaller = new Marshaller();
	Serializable[] parameters = new Serializable[] { arg0 };
	ServerMessage m = new ServerMessage(name, "do_sqrt" , parameters, null);
	Requestor r = new Requestor();
	byte[] answer = r.deliver_and_wait_feedback(address, marshaller.marshall(m));
	m = (ServerMessage) marshaller.unmarshall(answer);
	
	return (double) m.returnedValue;
}

}