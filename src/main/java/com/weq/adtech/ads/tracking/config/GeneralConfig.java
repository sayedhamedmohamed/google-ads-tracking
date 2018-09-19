package com.weq.adtech.ads.tracking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Class GeneralConfig.
 *
 * @author sayedhamed
 */
@Configuration
public class GeneralConfig {
	
	@Value("${sending.date.format}")
	private String dateFormat;
	
	/**
	 * The Gson bean
	 * @return gson
	 */
	@Bean
	public Gson buildGson() {
		
		return new GsonBuilder().setDateFormat(dateFormat).create();
	}

}
