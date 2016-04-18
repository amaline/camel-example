package gov.fdic.source.services;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;


@Component
public class ServiceRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		Processor helloProcessor=sayHelloProcessor();
		//Processor tokenProcessor=tokenDetailsProcessor();
       
        rest("/hello").description("say hello.")
        	.produces("application/json")
        	.get().description("Get hello.")
        	.route()
        	.process(helloProcessor);
 		
/*        rest("/tokenDetails").description("Get oauth token details")
    	   .produces("application/json")
    	   .get().description("Returns the oauth2 token details for the current access token.")
    	   .route()
    	   .process(tokenProcessor);
    	   */
	}
	
	public Processor sayHelloProcessor() {
		
		return new Processor() {
			public void process(Exchange exchange) {
				HttpServletRequest request=(HttpServletRequest)exchange.getIn().getHeader(Exchange.HTTP_SERVLET_REQUEST);
				Principal user=request.getUserPrincipal();
				exchange.getIn().setBody(user);
			}
		};
	}
	
	public Processor tokenDetailsProcessor() {
		
		return new Processor() {
			public void process(Exchange exchange) {
				exchange.getIn().setBody( getTokenDetails());
			}
		};
	}
	
	public Map<String, Object> getUserInfo() {
		  Authentication auth      = SecurityContextHolder.getContext().getAuthentication();
	      String         name      = auth.getName();

	      Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
	      StringBuffer buf=new StringBuffer();
	      for (GrantedAuthority authority: authorities) {
	    	  buf.append(authority.getAuthority() + ",");
	      }
	      if (buf.length() > 0) {
	    	  buf.deleteCharAt(buf.length());
	      }
	      
	      Map<String, Object> map= new HashMap<String,Object>();
	      map.put("name", name);
	      map.put("authorities", buf.toString());

	      return map;
	}
	
 
	public Map<String,Object> getTokenDetails() {
		OAuth2Authentication auth =
			  (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
              
			  OAuth2AuthenticationDetails details=(OAuth2AuthenticationDetails)auth.getDetails();

			  @SuppressWarnings("unchecked")
			  Map<String,Object> map=(Map<String, Object>)details.getDecodedDetails();
			  if (map == null) {
				  map = new HashMap<String,Object>();
				  map.put("type", details.getTokenType());
				  map.put("value", details.getTokenValue());
			  }

              return map;
	}

}
