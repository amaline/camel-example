package gov.fdic.source;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

@Component
public class CamelConfiguration extends RouteBuilder {

	@Override
	public void configure() throws Exception {
        
        restConfiguration()
        	.component("jetty")
        	.bindingMode(RestBindingMode.json)
        	.dataFormatProperty("prettyPring","true")
        	.host("0.0.0.0")
        	.port(10000)
        	.apiProperty("cors", "true");
        
 		
	}

}
