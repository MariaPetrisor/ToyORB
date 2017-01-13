import java.io.Serializable;
import MessageMarshaller.Marshaller;
import MessageMarshaller.ServerMessage;
import Commons.*;
import RequestReply.Requestor;

public class InfoMarketClientProxy implements InfoMarket{

private String name;
private Address address;
public InfoMarketClientProxy(String name, Address address){
	this.name = name;
	this.address = address;
 }
public String get_road_info(int arg0)
{
	Marshaller marshaller = new Marshaller();
	Serializable[] parameters = new Serializable[] { arg0 };
	ServerMessage m = new ServerMessage(name, "get_road_info" , parameters, null);
	Requestor r = new Requestor();
	byte[] answer = r.deliver_and_wait_feedback(address, marshaller.marshall(m));
	m = (ServerMessage) marshaller.unmarshall(answer);
	
	return (String) m.returnedValue;
}

public double get_temp(String arg0)
{
	Marshaller marshaller = new Marshaller();
	Serializable[] parameters = new Serializable[] { arg0 };
	ServerMessage m = new ServerMessage(name, "get_temp" , parameters, null);
	Requestor r = new Requestor();
	byte[] answer = r.deliver_and_wait_feedback(address, marshaller.marshall(m));
	m = (ServerMessage) marshaller.unmarshall(answer);
	
	return (double) m.returnedValue;
}

}