package com.seakmeng.tracking_api.controller;

import com.seakmeng.tracking_api.exception.ResourceNotFoundException;
import com.seakmeng.tracking_api.model.Shipping;
import com.seakmeng.tracking_api.model.ShippingService;
import com.seakmeng.tracking_api.model.TrackingProgress;
import com.seakmeng.tracking_api.model.User;
import com.seakmeng.tracking_api.repository.ShippingRepository;
import com.seakmeng.tracking_api.repository.ShippingServiceRepository;
import com.seakmeng.tracking_api.repository.TrackingProgressRepository;
import com.seakmeng.tracking_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShippingController {

  @Autowired
  private ShippingRepository shippingRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private ShippingServiceRepository shippingServiceRepository;
  
  @Autowired
  private TrackingProgressRepository trackingProgressRepository;

  @GetMapping("/shippings")
  public List<Shipping> getShippings() {
	  return shippingRepository.findShippingByIsDeleteNative(false);
  }

  @GetMapping("/shipping/{id}")
  public ResponseEntity<Shipping> getShippingById(@PathVariable(value = "id") Long shippingId)
      throws ResourceNotFoundException {
	  Shipping shipping = shippingRepository.findById(shippingId)
            .orElseThrow(() -> new ResourceNotFoundException("Shipping not found on :: " + shippingId));
	  
	  return ResponseEntity.ok().body(shipping);
  }

  @PostMapping(path = "/shipping", consumes = "application/json", produces = "application/json")
  public Shipping createShipping(@RequestBody Shipping shipping) {
	  User userDetails = userRepository.findByUsername(shipping.getCreatedBy().getUsername());
	  ShippingService shippingService = shippingServiceRepository.findById(shipping.getShippingService().getId()).get();
	  TrackingProgress trackingProgress =trackingProgressRepository.findById(shipping.getTrackingProgress().getId()).get();
	  
	  shipping.setShippingService(shippingService);
	  shipping.setTrackingProgress(trackingProgress);
	  shipping.setCreatedBy(userDetails);
	  shipping.setUpdatedBy(userDetails);
	  
	  return shippingRepository.save(shipping);
  }

  @PutMapping("/shipping/{id}")
  public ResponseEntity<Shipping> updateShipping(
	  @PathVariable(value = "id") Long shippingId, 
	  @RequestBody Shipping shippingData)
			  throws ResourceNotFoundException {	  
	  Shipping shipping = shippingRepository.findById(shippingId).get();
	  User userDetails = userRepository.findByUsername(shippingData.getUpdatedBy().getUsername());
	  ShippingService shippingService = shippingServiceRepository.findById(shipping.getShippingService().getId()).get();
	  TrackingProgress trackingProgress =trackingProgressRepository.findById(shipping.getTrackingProgress().getId()).get();
	  
	  shipping.setShippingControllerName(shippingData.getShippingControllerName());
	  shipping.setShippingDestination(shippingData.getShippingDestination());
	  shipping.setShippingDocumentNumber(shippingData.getShippingDocumentNumber());
	  shipping.setShippingOrigin(shippingData.getShippingOrigin());
	  
	  shipping.setShippingService(shippingService);
	  shipping.setTrackingProgress(trackingProgress);
	  shipping.setCreatedBy(userDetails);
	  shipping.setUpdatedBy(userDetails);
	  
	  final Shipping updatedShipping = shippingRepository.save(shipping);
	  return ResponseEntity.ok(updatedShipping);
  }
  
  @PutMapping("/shipping/{id}/delete")
  public ResponseEntity<Shipping> deleteShipping(
	  @PathVariable(value = "id") Long shippingId) 
		  	throws Exception {
    Shipping shipping = shippingRepository.findById(shippingId)
            .orElseThrow(() -> new ResourceNotFoundException("Shipping not found on :: " + shippingId));

    User userDetails = userRepository.findByUsername(shipping.getUpdatedBy().getUsername());
    
    shipping.setIsDelete(true);
    shipping.setUpdatedBy(userDetails);
    
    final Shipping updatedShipping = shippingRepository.save(shipping);
    return ResponseEntity.ok(updatedShipping); 
  }
  
}
