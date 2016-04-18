package gov.fdic.source.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
        from("timer:hello?period={{timer.period}}")
        .transform(method("activityBean", "respond"))
        .to("stream:out");
 		
        
	}

}
