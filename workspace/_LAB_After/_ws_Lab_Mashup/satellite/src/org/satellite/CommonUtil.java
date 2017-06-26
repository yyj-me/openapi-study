package org.satellite;

import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class CommonUtil {
	public static String convertObjectToJson(Object obj) throws Exception {
	      StringWriter sw = new StringWriter();   // serialize
	      ObjectMapper mapper = new ObjectMapper(); 
	      MappingJsonFactory jsonFactory = new MappingJsonFactory();
	      JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
	      mapper.writeValue(jsonGenerator, obj);
	      sw.close();
	      
	      return sw.getBuffer().toString();
	}
}
