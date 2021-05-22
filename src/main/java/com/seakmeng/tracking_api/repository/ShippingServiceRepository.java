package com.seakmeng.tracking_api.repository;

import com.seakmeng.tracking_api.model.ShippingService;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ShippingServiceRepository extends JpaRepository<ShippingService, Long> {

	List<ShippingService> findByNameContaining(String name);
	
	@Query(value = "SELECT * FROM shipping_service a WHERE a.is_delete = ?1", nativeQuery = true)
	List<ShippingService> findShippingServiceByIsDeleteNative(Boolean is_delete);
  
}

