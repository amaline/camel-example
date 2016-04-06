package gov.fdic.source;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class SourceEnvironment implements EnvironmentAware {

	private int port;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.port=Integer.parseInt( environment.getProperty("PORT","8080") );
	}

	public int getPort() {
		return port;
	}


}
