package com.seakmeng.tracking_api.controller;

import com.seakmeng.tracking_api.exception.ResourceNotFoundException;
import com.seakmeng.tracking_api.model.TrackingProgress;
import com.seakmeng.tracking_api.model.User;
import com.seakmeng.tracking_api.repository.TrackingProgressRepository;
import com.seakmeng.tracking_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrackingProgressController {

  @Autowired
  private TrackingProgressRepository trackingProgressRepository;
  
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/tracking_progresses")
  public List<TrackingProgress> getTrackingProgresses() {
	  return trackingProgressRepository.findTrackingProgressByIsDeleteNative(false);
  }

  @GetMapping("/tracking_progresses/{id}")
  public ResponseEntity<TrackingProgress> getTrackingProgressById(@PathVariable(value = "id") Long trackingProgressId)
      throws ResourceNotFoundException {
	  TrackingProgress trackingProgress = trackingProgressRepository.findById(trackingProgressId)
            .orElseThrow(() -> new ResourceNotFoundException("TrackingProgress not found on :: " + trackingProgressId));
	  
	  return ResponseEntity.ok().body(trackingProgress);
  }
  
  @GetMapping("/tracking_progresses/search/{keyword}")
  public ResponseEntity<List<TrackingProgress>> searchTrackingProgresses(@PathVariable(value = "keyword") String keyword)
      throws ResourceNotFoundException {
	  List<TrackingProgress> trackingProgresses = trackingProgressRepository.findByNameContaining(keyword);
	  return ResponseEntity.ok().body(trackingProgresses);
  }

  @PostMapping(path = "/tracking_progresses", consumes = "application/json", produces = "application/json")
  public TrackingProgress createTrackingProgress(@RequestBody TrackingProgress trackingProgress) {
	  User userDetails = userRepository.findByUsername(trackingProgress.getCreatedBy().getUsername());
	  trackingProgress.setCreatedBy(userDetails);
	  trackingProgress.setUpdatedBy(userDetails);
	  return trackingProgressRepository.save(trackingProgress);
  }

  @PutMapping("/tracking_progresses/{id}")
  public ResponseEntity<TrackingProgress> updateTrackingProgress(
      @PathVariable(value = "id") Long trackingProgressId, @RequestBody TrackingProgress trackingProgressDetails)
      throws ResourceNotFoundException {	  
	  TrackingProgress trackingProgress = trackingProgressRepository.findById(trackingProgressId)
            .orElseThrow(() -> new ResourceNotFoundException("TrackingProgress not found on :: " + trackingProgressId));
	  
	  User userDetails = userRepository.findByUsername(trackingProgress.getUpdatedBy().getUsername());
	  
	  trackingProgress.setName(trackingProgressDetails.getName());
	  trackingProgress.setDescription(trackingProgressDetails.getDescription());
	  trackingProgress.setOrderProgress(trackingProgressDetails.getOrderProgress());
	  trackingProgress.setUpdatedBy(userDetails);
	  
	  final TrackingProgress updatedTrackingProgress = trackingProgressRepository.save(trackingProgress);
	  return ResponseEntity.ok(updatedTrackingProgress);
  }
  
  @PutMapping("/tracking_progresses/{id}/delete")
  public ResponseEntity<TrackingProgress> deleteTrackingProgress(@PathVariable(value = "id") Long trackingProgressId) throws Exception {
	  TrackingProgress trackingProgress = trackingProgressRepository.findById(trackingProgressId)
            .orElseThrow(() -> new ResourceNotFoundException("ShippingService not found on :: " + trackingProgressId));

    User userDetails = userRepository.findByUsername(trackingProgress.getUpdatedBy().getUsername());
    trackingProgress.setIsDelete(true);
    trackingProgress.setUpdatedBy(userDetails);
    
    final TrackingProgress updatedTrackingProgress = trackingProgressRepository.save(trackingProgress);
    return ResponseEntity.ok(updatedTrackingProgress); 
  }
  
}
