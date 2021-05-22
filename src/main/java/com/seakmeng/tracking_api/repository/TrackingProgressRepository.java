package com.seakmeng.tracking_api.repository;

import com.seakmeng.tracking_api.model.TrackingProgress;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TrackingProgressRepository extends JpaRepository<TrackingProgress, Long> {

	List<TrackingProgress> findByNameContaining(String name);
	
	@Query(value = "SELECT * FROM tracking_progress a WHERE a.is_delete = ?1", nativeQuery = true)
	List<TrackingProgress> findTrackingProgressByIsDeleteNative(Boolean is_delete);
  
}

