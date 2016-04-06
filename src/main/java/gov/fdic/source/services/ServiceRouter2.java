package gov.fdic.source.services;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ServiceRouter2 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
       
        rest("/bye").description("say goodbye.")
        	.produces("application/json")
        	.get().description("This service says goodbye.")
        	.route()
        	.transform(constant("Get the hell out of here."));
 		
	}

}
