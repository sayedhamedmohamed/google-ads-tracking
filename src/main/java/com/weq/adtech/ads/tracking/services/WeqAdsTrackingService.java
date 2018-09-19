package com.weq.adtech.ads.tracking.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.weq.adtech.ads.tracking.constants.AppConstants;
import com.weq.adtech.ads.tracking.constants.PropertyName;
import com.weq.adtech.ads.tracking.exception.BadRequestException;
import com.weq.adtech.ads.tracking.model.AdsStatistics;
import com.weq.adtech.ads.tracking.model.Click;
import com.weq.adtech.ads.tracking.model.Delivery;
import com.weq.adtech.ads.tracking.model.DeliveryCount;
import com.weq.adtech.ads.tracking.model.Fields;
import com.weq.adtech.ads.tracking.model.Install;
import com.weq.adtech.ads.tracking.model.Interval;
import com.weq.adtech.ads.tracking.model.Stats;
import com.weq.adtech.ads.tracking.model.StatsDetails;
import com.weq.adtech.ads.tracking.repository.DeliveryRepository;

/**
 * Represents ads tracking service.
 *
 * @author Sayed Hamed
 * said352013@gmail.com
 */

@Service
public class WeqAdsTrackingService {

	/** The delivery repository. */
	@Autowired
	private DeliveryRepository deliveryRepository;

	/** The logger. */
	@Autowired
	private Logger logger;

	/** The date formatter. */
	private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss SSSS");
	
	/** The mongo template. */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * This is to save the ads delivery details
	 * @param delivery Delivery
	 */
	public void saveAdsDelivery(Delivery delivery) {

		deliveryRepository.save(delivery);
	}

	/**
	 * Save ads clicks.
	 *
	 * @param provided the provided
	 * @throws BadRequestException the bad request exception
	 */
	public void saveAdsClicks(Click provided) throws BadRequestException {

		Delivery delivery = deliveryRepository.findDelivery(provided.getDeliveryId());

		if(delivery == null)
			throw new BadRequestException(AppConstants.DELIVERY_ID_NOT_FOUND);

		delivery.setClickId(provided.getClickId());

		delivery.setClickTime(provided.getTime());

		deliveryRepository.save(delivery);
	}

	/**
	 * Save ads install.
	 *
	 * @param provided the provided
	 * @throws BadRequestException the bad request exception
	 */
	public void saveAdsInstall(Install provided) throws BadRequestException {

		Delivery delivery = deliveryRepository.findDeliveryByClickId(provided.getClickId());

		if(delivery == null)
			throw new BadRequestException(AppConstants.DELIVERY_ID_NOT_FOUND);

		delivery.setInstallId(provided.getInstallId());

		delivery.setInstallTime(provided.getTime());

		deliveryRepository.save(delivery);		
	}


	/**
	 * Find stats by start and end dates.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the ads statistics
	 */
	public AdsStatistics findStatsByStartAndEndDates(String startDate, String endDate) {

		Date start = getDate(startDate);

		Date end = getDate(endDate);

		long deliveries = getCount(start, end, PropertyName.DELIVERY_ID);

		long clicks = getCount(start, end, PropertyName.CLICK_ID);

		long installs = getCount(start, end, PropertyName.INSTALL_ID);

		Stats stats = new Stats(deliveries, clicks, installs);

		Interval interval = new Interval(start, end);

		return new AdsStatistics(interval, stats);
	}


	/**
	 * Find stats by start and end dates group by.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param category the category
	 * @return the ads statistics
	 */
	public AdsStatistics findStatsByStartAndEndDatesGroupBy(String startDate, String endDate, String[] category) {

		Fields fields;

		List<StatsDetails> data = new ArrayList<>();

		Date start = getDate(startDate);

		Date end = getDate(endDate);

		if(category.length < 2) {

			List<DeliveryCount> deliveriesList = getCountGroupBy(start, end, PropertyName.DELIVERY_ID, category[0]);

			List<DeliveryCount> clicksList = getCountGroupBy(start, end, PropertyName.CLICK_ID, category[0]);

			List<DeliveryCount> installsList = getCountGroupBy(start, end, PropertyName.INSTALL_ID, category[0]);

			for(DeliveryCount one : deliveriesList) {

				long deliveries = one.getCount() ;

				long clicks = getCountByCategory(clicksList, one.getBrowser());

				long installs = getCountByCategory(installsList, one.getBrowser());

				fields = new Fields(one.getBrowser(), null);

				Stats stats = new Stats(deliveries, clicks, installs);

				StatsDetails statsDetails = new StatsDetails(fields, stats);

				data.add(statsDetails);
			}
		}

		else {

			List<DeliveryCount> deliveriesList = getCountMultiGroupBy(start, end, PropertyName.DELIVERY_ID, category);

			List<DeliveryCount> clicksList = getCountMultiGroupBy(start, end, PropertyName.CLICK_ID, category);

			List<DeliveryCount> installsList = getCountMultiGroupBy(start, end, PropertyName.INSTALL_ID, category);

			for(DeliveryCount one : deliveriesList) {

				long deliveries = one.getCount() ;

				long clicks = getCountByCategory(clicksList, one.getBrowser());

				long installs = getCountByCategory(installsList, one.getBrowser());

				fields = new Fields(one.getBrowser(), one.getOs());

				Stats stats = new Stats(deliveries, clicks, installs);

				StatsDetails statsDetails = new StatsDetails(fields, stats);

				data.add(statsDetails);
			}
		}
		
		Interval interval = new Interval(start, end);

		return new AdsStatistics(interval, data);

	}

	/**
	 * Gets the count.
	 *
	 * @param start the start
	 * @param end the end
	 * @param property the property
	 * @return the count
	 */
	private long getCount(Date start, Date end, String property) {

		long count = 0;

		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where(PropertyName.TIME).gte(start).lte(end)),
				Aggregation.unwind(property),
				Aggregation.group().count().as(PropertyName.COUNT));

		AggregationResults<DeliveryCount> result = mongoTemplate.aggregate(aggregation, Delivery.class, DeliveryCount.class);

		return result.getMappedResults().isEmpty()  ? count : result.getMappedResults().get(0).getCount();
	}


	/**
	 * Gets the count group by.
	 *
	 * @param start the start
	 * @param end the end
	 * @param property the property
	 * @param category the category
	 * @return the count group by
	 */
	private List<DeliveryCount> getCountGroupBy(Date start, Date end, String property, String category) {

		Aggregation agg = Aggregation.newAggregation(
				Aggregation.match(Criteria.where(PropertyName.TIME).gte(start).lte(end).and(property).ne(null)),
				Aggregation.group(category).count().as(PropertyName.COUNT),
				Aggregation.project(PropertyName.COUNT).and(category).previousOperation());

		//Convert the aggregation result into a List
		AggregationResults<DeliveryCount> groupResults = mongoTemplate.aggregate(agg, Delivery.class, DeliveryCount.class);

		return groupResults.getMappedResults();
	}

	/**
	 * Gets the count multi group by.
	 *
	 * @param start the start
	 * @param end the end
	 * @param property the property
	 * @param category the category
	 * @return the count multi group by
	 */
	private List<DeliveryCount> getCountMultiGroupBy(Date start, Date end, String property, String[] category) {

		Aggregation agg = Aggregation.newAggregation(
				Aggregation.match(Criteria.where(PropertyName.TIME).gte(start).lte(end).and(property).ne(null)),
				Aggregation.group(category[0], category[1]).count().as(PropertyName.COUNT),
				Aggregation.project(PropertyName.COUNT, category[0], category[1]));

		//Convert the aggregation result into a List
		AggregationResults<DeliveryCount> groupResults = mongoTemplate.aggregate(agg, Delivery.class, DeliveryCount.class);

		return groupResults.getMappedResults();
	}

	/**
	 * Gets the date.
	 *
	 * @param providedDate the provided date
	 * @return the date
	 */
	public Date getDate(String providedDate) {

		Date date = null;

		try {

			date = df.parse(providedDate);
		}

		catch(Exception e) {

			logger.error("Date parsing error", e.getMessage());
		}

		return  date;
	}


	/**
	 * Gets the count by category.
	 *
	 * @param data the data
	 * @param category the category
	 * @return the count by category
	 */
	private Long getCountByCategory(List<DeliveryCount> data, String category){

		long count = 0;

		for(DeliveryCount one : data){

			if(one.getBrowser().equals(category))
				return one.getCount();
		}

		return count;
	}
}
