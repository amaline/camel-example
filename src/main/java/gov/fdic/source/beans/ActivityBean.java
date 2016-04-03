package gov.fdic.source.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("activityBean")
public class ActivityBean {
	
    @Value("${greeting}")
    private String output;
    
    public String respond() {
    	return output;
    
    }
}
