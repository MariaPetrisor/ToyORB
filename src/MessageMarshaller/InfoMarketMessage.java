package MessageMarshaller;

import java.io.Serializable;

public class InfoMarketMessage implements Message 
{  
        public String methodName;
        public Serializable[] parameters;
        public Serializable returnedValue;

        public InfoMarketMessage(String methodName, Serializable[] parameters, Serializable returnedValue)
        {
                this.methodName = methodName;
                this.parameters = parameters;
                this.returnedValue = returnedValue;             
        }
}