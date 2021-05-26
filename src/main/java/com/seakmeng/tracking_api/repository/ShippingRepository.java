package com.seakmeng.tracking_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seakmeng.tracking_api.model.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

	@Query(value = "SELECT * FROM shipping a WHERE a.is_delete = ?1", nativeQuery = true)
	List<Shipping> findShippingByIsDeleteNative(Boolean is_delete);
	
}
