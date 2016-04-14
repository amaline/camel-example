package gov.fdic.source.services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.stereotype.Component;

import gov.fdic.source.beans.SupervisoryActivity;

@Component
public class SupervisoryActivities extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
       
        RestDefinition rest = rest("/supervisoryActivity").description("Retrieves a supervisory activity.");
        
        	rest
        	  .get("/{id}")
        	    .produces("application/json")
        	    .description("Returns a Supervisory Activity of a particular {id}.")
        	    .outType(SupervisoryActivity.class)
        	  .route()
        	    .to("direct:supervisoryActivity");
        	
        	
        	rest
        	  .post("/new")
        	    .consumes("application/json")
        	    .produces("application/json")
        	    .description("Create a new Supervisory Activity")
        	    .type(SupervisoryActivity.class)
        	  .route()
        	    .to("direct:newSupervisoryActivity")
        	  ;
 		
        from("direct:supervisoryActivity")
          .transform(constant("If I had it, you could have it!"));;
        
        from("direct:newSupervisoryActivity")
          .to("mock:createActivity");
	}

}
