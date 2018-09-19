package com.weq.adtech.ads.tracking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class WeqLogger.
 * @author Sayed Hamed
 */
@Configuration
public class WeqLogger {
	
	/**
	 * Logger.
	 *
	 * @return the logger
	 */
	@Bean Logger logger() {
		
		return LoggerFactory.getLogger(WeqLogger.class);
	}

}
