package com.weq.adtech.ads.tracking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.weq.adtech.ads.tracking.constants.AppConstants;
import com.weq.adtech.ads.tracking.exception.BadRequestException;
import com.weq.adtech.ads.tracking.handlers.WeqAdsClicksHandler;
import com.weq.adtech.ads.tracking.handlers.WeqAdsDeliveryHandler;
import com.weq.adtech.ads.tracking.handlers.WeqAdsInstallsHandler;
import com.weq.adtech.ads.tracking.handlers.WeqAdsStatisticsHandler;
import com.weq.adtech.ads.tracking.model.AdsStatistics;
import com.weq.adtech.ads.tracking.model.Click;
import com.weq.adtech.ads.tracking.model.Delivery;
import com.weq.adtech.ads.tracking.model.Install;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Represents Weq ads tracking controller
 * @author Sayed Hamed
 * said352013@gmail.com
 * @param <T>
 */


@Api(tags={"Operations pertaining to Weq Ads Tracker"})
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 500, message = "Error occured")})
@CrossOrigin
@RestController
@RequestMapping("/ads")
public class WeqAdsTrackingController {

	@Value("start.date")
	private String startDate;

	@Value("end.date")
	private String endDate;

	@Autowired
	private Gson gson;

	@Autowired
	private WeqAdsDeliveryHandler<Delivery> weqAdsDeliveryHandler;

	@Autowired
	private WeqAdsClicksHandler<Click> weqAdsClicksHandler;

	@Autowired
	private WeqAdsInstallsHandler<Install> weqAdsInstallsHandler;

	@Autowired
	private WeqAdsStatisticsHandler weqAdsStatisticsHandler;

	@Autowired
	private Logger logger;

	/**
	 * Adds the delivery.
	 *
	 * @param delivery the delivery
	 * @param response the response
	 */
	@PostMapping(value = "/delivery", consumes = {"application/json"})
	protected void addDelivery(@RequestBody Delivery delivery, HttpServletResponse response) {

		try {

			weqAdsDeliveryHandler.handle(delivery);
		}

		catch (Exception e) {

			response.setStatus(AppConstants.FAILURE);
		}
	}

	/**
	 * Adds the click.
	 *
	 * @param click the click
	 * @param response the response
	 */
	@ApiResponse(code = 404 , message = "Given delivery never received")
	@PostMapping(value = "/click", consumes = {"application/json"})
	protected void addClick(@RequestBody Click click, HttpServletResponse response) {

		try {

			weqAdsClicksHandler.handle(click);
		}

		catch (BadRequestException e) {

			response.setStatus(AppConstants.DELIVERY_ID_NOT_FOUND);
		}

		catch (Exception e) {

			e.printStackTrace();

			response.setStatus(AppConstants.FAILURE);
		}
	}

	/**
	 * Adds the install.
	 *
	 * @param install the install
	 * @param response the response
	 */
	@ApiResponse(code = 404 , message = "Given click never received")
	@PostMapping(value = "/install", consumes = {"application/json"})
	protected void addInstall(@RequestBody Install install, HttpServletResponse response) {

		try {

			weqAdsInstallsHandler.handle(install);
		}

		catch (BadRequestException e) {

			response.setStatus(AppConstants.CLICK_ID_NOT_FOUND);
		}

		catch (Exception e) {

			response.setStatus(AppConstants.FAILURE);
		}
	}

	/**
	 * Gets the statistics by start and end dates group by.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the statistics by start and end dates group by
	 */
	@ApiImplicitParams({@ApiImplicitParam(name = "start", value = "start", required = true, paramType = "query"),
		@ApiImplicitParam(name = "end", value = "end", required = true, paramType = "query"),
		@ApiImplicitParam(name = "group_by", value = "group_by", required = false, paramType = "query"),
		@ApiImplicitParam(name = "group_by", value = "group_by", required = false, paramType = "query")})	
	@GetMapping(value= "/statistics")
	protected String getStatisticsByStartAndEndDatesGroupBy(HttpServletRequest request, HttpServletResponse response) {

		String start = request.getParameter("start");

		String end = request.getParameter("end");

		String[] categories = request.getParameterValues("group_by");

		AdsStatistics  result = null;

		try {

			if(categories != null) {

				result = weqAdsStatisticsHandler.handle(start, end, categories);
			}

			else 

				result = weqAdsStatisticsHandler.handle(start, end);
		}

		catch (Exception e) {

			logger.info("Error occured: "+ e.getMessage());
		}

		return gson.toJson(result);
	}
}
