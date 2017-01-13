import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.System;
class ProxyGenerator
{
	private String name ;
	private BufferedWriter output  ;
	
	public ProxyGenerator(String name) 
	{
		this.name=name;
	}
	

	
	public void createFile() throws IOException, ClassNotFoundException
	{
       
		File file = new File("src//"+name+"ClientProxy.java");
		
        output = new BufferedWriter(new FileWriter(file));
        
        output.write("import java.io.Serializable;\nimport MessageMarshaller.Marshaller;\nimport MessageMarshaller.ServerMessage;\nimport Commons.*;\nimport RequestReply.Requestor;\n");
        
        output.write("\npublic class "+name+"ClientProxy implements "+name+"{\n\nprivate String name;\nprivate Address address;\n");
        
        output.write("public "+ name + "ClientProxy(String name, Address address){\n\tthis.name = name;\n\tthis.address = address;\n }\n");
        
        String className = name+"Impl";
        
        Class<?> c = Class.forName(className);
         
        Method[] mathods = c.getDeclaredMethods();
		
	    for (Method m : mathods) 
	    { 
	    	output.write("public ");
	    	
	    	
	    	String methodName = m.getName();
	    	
	    	
	    	Class<?> methodType = m.getReturnType();
	        String method_type= methodType.getName();
	        
	        if(method_type.contains("String")){
	        	method_type = "String";
	        }
	        
	   
	        output.write(method_type+" "+methodName+"(");
	    	
	        
	    	Parameter[] parameters  = m.getParameters();
	    	String parametersForSerializable = "";
	    	
	    	
	    	if (parameters.length!=0)
	    	{
				for (int i = 0; i < parameters.length; i++) 
				{  
					String parameterName = parameters[i].getName();
					parametersForSerializable += parameterName;
			        Class<?> fieldType = parameters[i].getType();
			        String type= fieldType.getName();
			        
			        
			        if(type.contains("String")){
			        	type = "String";
			        }
			        
			        output.write(type+" "+parameterName);
					
					if((i+1)<parameters.length){
						output.write(",");
						parametersForSerializable += ", ";
					}
				}
	    	}
			output.write(")\n{\n");
			
		    output.write("\tMarshaller marshaller = new Marshaller();\n\tSerializable[] parameters = new Serializable[] { ");
		    
		    output.write(parametersForSerializable);
		    
		    output.write(" };\n\tServerMessage m = new ServerMessage(name, \"" + methodName + "\" , parameters, null);\n\tRequestor r = new Requestor();\n\t");
		    
		    output.write("byte[] answer = r.deliver_and_wait_feedback(address, marshaller.marshall(m));\n\tm = (ServerMessage) marshaller.unmarshall(answer);\n\t\n");
		    
		    output.write("\treturn ("+method_type+") m.returnedValue;\n}\n\n");

		 	    
	    	
	    }
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     
 	     output.write("}");

        
        output.close();
        
        
        
	}
	
	
}