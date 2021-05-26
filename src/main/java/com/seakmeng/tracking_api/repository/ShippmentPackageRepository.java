package com.seakmeng.tracking_api.repository;

import com.seakmeng.tracking_api.model.ShippmentPackage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ShippmentPackageRepository extends JpaRepository<ShippmentPackage, Long> {

	List<ShippmentPackage> findByNameContaining(String name);
	
	@Query(value = "SELECT * FROM shippment_package a WHERE a.is_delete = ?1", nativeQuery = true)
	List<ShippmentPackage> findShippmentPackagesByIsDeleteNative(Boolean is_delete);
  
}

