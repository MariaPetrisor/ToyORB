package MessageMarshaller;

import java.io.Serializable;

public class ServerMessage implements Message 
{  
        public String name;
        public String methodName;
        public Serializable[] parameters;
        public Serializable returnedValue;

        public ServerMessage(String name, String methodName, Serializable[] parameters, Serializable returnedValue)
        {
                this.name = name;
                this.methodName = methodName;
                this.parameters = parameters;
                this.returnedValue = returnedValue;             
        }
}