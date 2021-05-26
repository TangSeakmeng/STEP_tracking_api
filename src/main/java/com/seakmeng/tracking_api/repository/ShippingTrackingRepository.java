package com.seakmeng.tracking_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seakmeng.tracking_api.model.ShippingTracking;

@Repository
public interface ShippingTrackingRepository extends JpaRepository<ShippingTracking, Long> {
	
	@Query(value = "SELECT * FROM shipping_tracking a WHERE a.shipping_id = ?1", nativeQuery = true)
	List<ShippingTracking> findShippingTrackingsByShippingIdNative(Long shipping_id);
	
	@Query(value = "SELECT * FROM shipping_tracking a WHERE a.shipping_id = ?1 and a.tracking_id = ?2", nativeQuery = true)
	ShippingTracking findShippingTrackingByShippingIdAndTrackingIdNative(Long shipping_id, Long tracking_id);

}
