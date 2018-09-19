package com.weq.adtech.ads.tracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weq.adtech.ads.tracking.handlers.WeqAdsClicksHandler;
import com.weq.adtech.ads.tracking.handlers.WeqAdsDeliveryHandler;
import com.weq.adtech.ads.tracking.handlers.WeqAdsInstallsHandler;
import com.weq.adtech.ads.tracking.handlers.WeqAdsStatisticsHandler;
import com.weq.adtech.ads.tracking.model.Click;
import com.weq.adtech.ads.tracking.model.Delivery;
import com.weq.adtech.ads.tracking.model.Install;

/**
 * Represent application configuration class
 * @author Sayed Hamed
 * said352013@gmail.com
 */

@Configuration
public class ApplicationConfig {

	/**
	 * Weq ads tracking handler.
	 *
	 * @return the weq ads delivery handler
	 */
	@Bean
	public WeqAdsDeliveryHandler<Delivery> weqAdsTrackingHandler() {

		return new WeqAdsDeliveryHandler<>();
	}

	/**
	 * Weq ads clicks handler.
	 *
	 * @return the weq ads clicks handler
	 */
	@Bean
	public WeqAdsClicksHandler<Click> weqAdsClicksHandler() {

		return new WeqAdsClicksHandler<>();
	}

	/**
	 * Weq ads installs handler.
	 *
	 * @return the weq ads installs handler
	 */
	@Bean
	public WeqAdsInstallsHandler<Install> weqAdsInstallsHandler() {

		return new WeqAdsInstallsHandler<>();
	}

	/**
	 * Weq ads statistics handler.
	 *
	 * @return the weq ads statistics handler
	 */
	@Bean
	public WeqAdsStatisticsHandler weqAdsStatisticsHandler() {

		return new WeqAdsStatisticsHandler();
	}
}
