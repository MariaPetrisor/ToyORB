package MessageMarshaller;

import Commons.*;

public class NamingMessage implements Message
{
        public String operation;
        public String name;
        public Address address;

        public NamingMessage(String operation, String name, Address address) {

                this.operation = operation;
                this.name = name;
                this.address = address;

        }
}
