package com.weq.adtech.ads.tracking.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.weq.adtech.ads.tracking.model.Delivery;

/**
 * The Interface DeliveryRepository.
 * @author sayed Hamed
 */

/**db.deliveries.createIndex({delivery_id : 1});  */
@Repository 
public interface DeliveryRepository extends CrudRepository<Delivery, String> {
	
	@Query("{delivery_id: ?0}")
	public Delivery findDelivery(String deliveryId);

	@Query("{click_id: ?0}")
	public Delivery findDeliveryByClickId(String clickId);
}
