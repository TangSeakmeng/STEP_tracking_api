package com.seakmeng.tracking_api.controller;

import com.seakmeng.tracking_api.exception.ResourceNotFoundException;
import com.seakmeng.tracking_api.model.ShippingService;
import com.seakmeng.tracking_api.model.User;
import com.seakmeng.tracking_api.repository.ShippingServiceRepository;
import com.seakmeng.tracking_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShippingServiceController {

  @Autowired
  private ShippingServiceRepository shippingServiceRepository;
  
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/shipping_services")
  public List<ShippingService> getShippingServices() {
//    return shippingServiceRepository.findAll();
	  return shippingServiceRepository.findShippingServiceByIsDeleteNative(false);
  }

  @GetMapping("/shipping_services/{id}")
  public ResponseEntity<ShippingService> getShippingServicesById(@PathVariable(value = "id") Long shippingServiceId)
      throws ResourceNotFoundException {
	  ShippingService shippingService = shippingServiceRepository.findById(shippingServiceId)
            .orElseThrow(() -> new ResourceNotFoundException("ShippingService not found on :: " + shippingServiceId));
	  
	  return ResponseEntity.ok().body(shippingService);
  }
  
  @GetMapping("/shipping_services/search/{keyword}")
  public ResponseEntity<List<ShippingService>> searchShippingService(@PathVariable(value = "keyword") String keyword)
      throws ResourceNotFoundException {
	  List<ShippingService> shippingServices = shippingServiceRepository.findByNameContaining(keyword);
	  return ResponseEntity.ok().body(shippingServices);
  }

  @PostMapping(path = "/shipping_services", consumes = "application/json", produces = "application/json")
  public ShippingService createShippingService(@RequestBody ShippingService shippingService) {
	  User userDetails = userRepository.findByUsername(shippingService.getCreatedBy().getUsername());
	  shippingService.setCreatedBy(userDetails);
	  shippingService.setUpdatedBy(userDetails);
	  return shippingServiceRepository.save(shippingService);
  }

  @PutMapping("/shipping_services/{id}")
  public ResponseEntity<ShippingService> updateShippingService(
      @PathVariable(value = "id") Long shippingServiceId, @RequestBody ShippingService shippingServiceDetails)
      throws ResourceNotFoundException {	  
	  ShippingService shippingService = shippingServiceRepository.findById(shippingServiceId)
            .orElseThrow(() -> new ResourceNotFoundException("ShippingService not found on :: " + shippingServiceId));
	  
	  User userDetails = userRepository.findByUsername(shippingService.getUpdatedBy().getUsername());
	  
	  shippingService.setName(shippingServiceDetails.getName());
	  shippingService.setDescription(shippingServiceDetails.getDescription());
	  shippingService.setPrice(shippingServiceDetails.getPrice());
	  shippingService.setUpdatedBy(userDetails);
	  
	  final ShippingService updatedShippingService = shippingServiceRepository.save(shippingService);
	  return ResponseEntity.ok(updatedShippingService);
  }

//  @DeleteMapping("/shipping_service/{id}")
//  public Map<String, Boolean> deleteShippingService(@PathVariable(value = "id") Long shippingServiceId) throws Exception {
//    ShippingService shippingService = shippingServiceRepository.findById(shippingServiceId)
//            .orElseThrow(() -> new ResourceNotFoundException("ShippingService not found on :: " + shippingServiceId));
//
//    shippingServiceRepository.delete(shippingService);
//    Map<String, Boolean> response = new HashMap<>();
//    response.put("deleted", Boolean.TRUE);
//    return response;
//  }
  
  @PutMapping("/shipping_services/{id}/delete")
  public ResponseEntity<ShippingService> deleteShippingService(@PathVariable(value = "id") Long shippingServiceId) throws Exception {
    ShippingService shippingService = shippingServiceRepository.findById(shippingServiceId)
            .orElseThrow(() -> new ResourceNotFoundException("ShippingService not found on :: " + shippingServiceId));

    User userDetails = userRepository.findByUsername(shippingService.getUpdatedBy().getUsername());
    shippingService.setIsDelete(true);
    shippingService.setUpdatedBy(userDetails);
    
    final ShippingService updatedShippingService = shippingServiceRepository.save(shippingService);
    return ResponseEntity.ok(updatedShippingService); 
  }
  
}
