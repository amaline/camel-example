package gov.fdic.source;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.boot.FatJarRouter;
import org.apache.camel.swagger.servlet.RestSwaggerCorsFilter;
import org.apache.camel.swagger.servlet.RestSwaggerServlet;

@Component
public class CamelConfiguration extends FatJarRouter {

	@Autowired
	SourceEnvironment myenv;
	
	@Override
	public void configure()  {
        
        restConfiguration()
        	.component("servlet")
        	.bindingMode(RestBindingMode.json)
        	.dataFormatProperty("prettyPring","true")
        	.host("0.0.0.0")
        	.port(myenv.getPort())
        	.apiProperty("cors", "true")
            // This is the context path to be used for Swagger API documentation
            .apiContextPath("api-doc").
            // Properties for Swagger
            // Title of the API
            apiProperty("api.title", "DCP SOURCE API").
            // Version of the API
            apiProperty("api.version", "0.1.0").
            // CORS (resource sharing) enablement
            apiProperty("cors", "true").
            // Use localhost for calls
            apiProperty("host", "localhost:" + myenv.getPort()).
            // Set base path
            apiProperty("base.path", "/api");;
        
 		
	}
	   @Bean
	   public ServletRegistrationBean camelServletRegistrationBean() {
	      ServletRegistrationBean registration = new ServletRegistrationBean(
	            new CamelHttpTransportServlet(), "/api/*");
	      registration.setName("CamelServlet");

	      return registration;
	   }

	   @Bean
	   public ServletRegistrationBean swaggerServletRegistrationBean() {
	      ServletRegistrationBean registration = new ServletRegistrationBean(
	            new RestSwaggerServlet(), "/api-docs/*");
	      registration.setName("SwaggerServlet");
	      registration.addInitParameter("base.path", "/api");
	      return registration;
	   }

	   @Bean
	   public FilterRegistrationBean filterRegistrationBean() {
	      FilterRegistrationBean filterBean = new FilterRegistrationBean();
	      filterBean.setFilter(new RestSwaggerCorsFilter());
	      List<String> urlPatterns = new ArrayList<String>();
	      urlPatterns.add("/api-docs/*");
	      urlPatterns.add("/api/*");
	      filterBean.setUrlPatterns(urlPatterns);
	      return filterBean;
	   }

}
