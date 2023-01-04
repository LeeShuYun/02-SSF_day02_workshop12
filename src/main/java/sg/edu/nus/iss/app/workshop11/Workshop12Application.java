package sg.edu.nus.iss.app.workshop12;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop12Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop12Application.class);
	private static String portNumber = null;
	private static final String DEFAULT_PORT = "8080";

	public static void main(String[] args) {
		
		//logs debug msgs, and gives stats like port number the app is on, etc
		logger.debug("Args : " + args);
		for (String argVal : args){
			logger.debug("argVal : " + argVal);
			if(argVal.contains("--port=")){
				portNumber = argVal.substring(argVal.length()-4 , argVal.length());
				logger.debug("portNumber : " + portNumber);
			}
		}

		if (portNumber == null) {
			portNumber = System.getenv("APP_PORT");
			logger.debug("Sys ENV portNumber : " + portNumber);
		}

		//sets default if no port is specified in args
		if (portNumber == null){
			portNumber = DEFAULT_PORT;
		}

		//long way of initing the server, because we need to be able to override the port to test the override
		SpringApplication app = new SpringApplication(Workshop12Application.class);
		//for custom properties
		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		app.run(args);
		// SpringApplication.run(Workshop11Application.class, args); //this is a shortcut way but doesn't allow us to override port
	}

}
