package com.seakmeng.tracking_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seakmeng.tracking_api.model.ShippingDetail;

@Repository
public interface ShippingDetailRepository extends JpaRepository<ShippingDetail, Long> {

	@Query(value = "SELECT * FROM shipping_detail a WHERE a.shipping_id = ?1", nativeQuery = true)
	List<ShippingDetail> findShippingDetailsByShippingIdNative(Long shipping_id);
	
	@Query(value = "SELECT * FROM shipping_detail a WHERE a.shipping_id = ?1 and a.shippment_package_id = ?2", nativeQuery = true)
	ShippingDetail findShippingDetailByShippingIdAndPackageIdNative(Long shipping_id, Long package_id);
	
	@Query(value = "SELECT * FROM shipping_detail a WHERE a.shippment_package_id = ?1", nativeQuery = true)
	ShippingDetail findShippingDetailByPackageIdNative(Long package_id);
	
}
