package com.weq.adtech.ads.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Represents the start point of Weq ads tracker
 * @author Sayed Hamed
 * said352013@gmail.com
 */

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(Startup.class);

		try {

			SpringApplication.run(Startup.class, args);

			logger.info("Application has started successfully");
		}
		
		catch (Exception e) {

			logger.error("Error occured while starting application", e);		
		}
	}
}
