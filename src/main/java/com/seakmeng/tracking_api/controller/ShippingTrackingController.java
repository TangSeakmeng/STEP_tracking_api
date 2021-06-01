package com.seakmeng.tracking_api.controller;

import com.seakmeng.tracking_api.exception.ResourceNotFoundException;
import com.seakmeng.tracking_api.model.Shipping;
import com.seakmeng.tracking_api.model.ShippingDetail;
import com.seakmeng.tracking_api.model.ShippingTracking;
import com.seakmeng.tracking_api.model.ShippmentPackage;
import com.seakmeng.tracking_api.model.TrackingProgress;
import com.seakmeng.tracking_api.model.User;
import com.seakmeng.tracking_api.repository.ShippingDetailRepository;
import com.seakmeng.tracking_api.repository.ShippingRepository;
import com.seakmeng.tracking_api.repository.ShippingTrackingRepository;
import com.seakmeng.tracking_api.repository.ShippmentPackageRepository;
import com.seakmeng.tracking_api.repository.TrackingProgressRepository;
import com.seakmeng.tracking_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShippingTrackingController {

  @Autowired
  private ShippingTrackingRepository shippingTrackingRepository;
  
  @Autowired
  private ShippingDetailRepository shippingDetailRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private ShippingRepository shippingRepository;
  
  @Autowired
  private ShippmentPackageRepository shippmentPackageRepository;
  
  @Autowired
  private TrackingProgressRepository trackingProgressRepository;

  @GetMapping("/shipping_tracking/{shippingId}")
  public ResponseEntity<List<ShippingTracking>> getShippingTrackingsById(
	  @PathVariable(value = "shippingId") Long shippingId)
			  throws ResourceNotFoundException {
	  List<ShippingTracking> shippingTrackings = shippingTrackingRepository.findShippingTrackingsByShippingIdNative(shippingId);
	  return ResponseEntity.ok().body(shippingTrackings);
  }
  
  @GetMapping("/shipping_tracking/{packageCode}/packageCode")
  public ResponseEntity<List<ShippingTracking>> getShippingInformationInfoByPackageCode(
	  @PathVariable(value = "packageCode") String packageCode)
			  throws ResourceNotFoundException {
	  ShippmentPackage shippmentPackage = shippmentPackageRepository.findShippmentPackageByPackageCodeNative(packageCode);
	  ShippingDetail shippingDetail = shippingDetailRepository.findShippingDetailByPackageIdNative(shippmentPackage.getId());
	  List<ShippingTracking> listShippingTracking = shippingTrackingRepository.findShippingTrackingsByShippingIdNative(shippingDetail.getShipping().getId());
	  return ResponseEntity.ok().body(listShippingTracking);
  }

  @PostMapping(path = "/shipping_tracking", consumes = "application/json", produces = "application/json")
  public ShippingTracking createShippingTracking(@RequestBody ShippingTracking shippingTracking) {
	  User userDetails = userRepository.findByUsername(shippingTracking.getCreatedBy().getUsername());
	  Shipping shipping = shippingRepository.findById(shippingTracking.getShipping().getId()).get();
	  TrackingProgress trackingProgress = trackingProgressRepository.findById(shippingTracking.getTrackingProgress().getId()).get();
	  
	  shippingTracking.setShipping(shipping);
	  shippingTracking.setTrackingProgress(trackingProgress);
	  shippingTracking.setCreatedBy(userDetails);
	  shippingTracking.setUpdatedBy(userDetails);
	  
	  return shippingTrackingRepository.save(shippingTracking);
  }

  @PutMapping("/shipping_tracking/{shippingId}/{trackingId}")
  public ResponseEntity<ShippingTracking> updateShippingTracking(
      @PathVariable(value = "shippingId") Long shippingId, 
      @PathVariable(value = "trackingId") Long trackingId,
      @RequestBody ShippingTracking shippingDetailData)
    		  throws ResourceNotFoundException {	  
	  ShippingTracking shippingTracking = shippingTrackingRepository.findShippingTrackingByShippingIdAndTrackingIdNative(shippingId, trackingId);
	  User userDetails = userRepository.findByUsername(shippingDetailData.getUpdatedBy().getUsername());
	  
	  shippingTracking.setTrackingOrder(shippingDetailData.getTrackingOrder());
	  shippingTracking.setDateTime(shippingDetailData.getDateTime());
	  shippingTracking.setDestination(shippingDetailData.getDestination());
	  shippingTracking.setOrigin(shippingDetailData.getOrigin());
	  shippingTracking.setUpdatedBy(userDetails);
	  
	  final ShippingTracking updatedShippingTracking = shippingTrackingRepository.save(shippingTracking);
	  return ResponseEntity.ok(updatedShippingTracking);
  }
  
  @PutMapping("/shipping_tracking/{shippingId}/{trackingId}/delete")
  public ResponseEntity<ShippingTracking> deleteShippingTracking(
	  @PathVariable(value = "shippingId") Long shippingId, 
      @PathVariable(value = "trackingId") Long trackingId,
      @RequestBody ShippingDetail shippingDetailData)
    		  throws Exception {
	  ShippingTracking shippingTracking = shippingTrackingRepository.findShippingTrackingByShippingIdAndTrackingIdNative(shippingId, trackingId);	
	  User userDetails = userRepository.findByUsername(shippingDetailData.getUpdatedBy().getUsername());
	  
	  shippingTracking.setIsDelete(true);
	  shippingTracking.setUpdatedBy(userDetails);
	
	  final ShippingTracking updatedShippingTracking = shippingTrackingRepository.save(shippingTracking);
	  return ResponseEntity.ok(updatedShippingTracking); 
  }
  
}

