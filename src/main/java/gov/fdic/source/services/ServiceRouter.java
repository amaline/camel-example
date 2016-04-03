package gov.fdic.source.services;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ServiceRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
       
        rest("/hello").description("say hello.")
        	.produces("application/json")
        	.get().description("Get hello.")
        	.route()
        	.transform(constant("BOOOM!!!"));
 		
	}

}
