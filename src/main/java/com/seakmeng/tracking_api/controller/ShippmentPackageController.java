package com.seakmeng.tracking_api.controller;

import com.seakmeng.tracking_api.exception.ResourceNotFoundException;
import com.seakmeng.tracking_api.model.ShippmentPackage;
import com.seakmeng.tracking_api.model.User;
import com.seakmeng.tracking_api.repository.ShippmentPackageRepository;
import com.seakmeng.tracking_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShippmentPackageController {

  @Autowired
  private ShippmentPackageRepository shippmentPackageRepository;
  
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/packages")
  public List<ShippmentPackage> getPackages() {
	  return shippmentPackageRepository.findShippmentPackagesByIsDeleteNative(false);
  }

  @GetMapping("/packages/{id}")
  public ResponseEntity<ShippmentPackage> getPackageById(@PathVariable(value = "id") Long id)
      throws ResourceNotFoundException {
	  ShippmentPackage item = shippmentPackageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Package not found on :: " + id));
	  
	  return ResponseEntity.ok().body(item);
  }
  
  @GetMapping("/packages/search/{keyword}")
  public ResponseEntity<List<ShippmentPackage>> searchPackages(@PathVariable(value = "keyword") String keyword)
      throws ResourceNotFoundException {
	  List<ShippmentPackage> items = shippmentPackageRepository.findByNameContaining(keyword);
	  return ResponseEntity.ok().body(items);
  }

  @PostMapping(path = "/packages", consumes = "application/json", produces = "application/json")
  public ShippmentPackage createPackage(@RequestBody ShippmentPackage packageData) {
	  User userDetails = userRepository.findByUsername(packageData.getCreatedBy().getUsername());
	  packageData.setCreatedBy(userDetails);
	  packageData.setUpdatedBy(userDetails);
	  return shippmentPackageRepository.save(packageData);
  }

  @PutMapping("/packages/{id}")
  public ResponseEntity<ShippmentPackage> updatePackage(
      @PathVariable(value = "id") Long id, @RequestBody ShippmentPackage itemDetails)
      throws ResourceNotFoundException {	  
	  ShippmentPackage item = shippmentPackageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ShippingService not found on :: " + id));
	  
	  User userDetails = userRepository.findByUsername(item.getUpdatedBy().getUsername());
	  
	  item.setStatus(itemDetails.getStatus());
	  item.setWeight(itemDetails.getWeight());
	  item.setPackageCode(itemDetails.getPackageCode());
	  item.setOriginTrackingNumber(itemDetails.getOriginTrackingNumber());
	  item.setName(itemDetails.getName());
	  item.setDescription(itemDetails.getDescription());
	  item.setPrice(itemDetails.getPrice());
	  item.setUpdatedBy(userDetails);
	  
	  final ShippmentPackage updatedItem = shippmentPackageRepository.save(item);
	  return ResponseEntity.ok(updatedItem);
  }
  
  @PutMapping("/packages/{id}/delete")
  public ResponseEntity<ShippmentPackage> deletePackage(@PathVariable(value = "id") Long id) throws Exception {
	  ShippmentPackage item = shippmentPackageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Package not found on :: " + id));

    User userDetails = userRepository.findByUsername(item.getUpdatedBy().getUsername());
    item.setIsDelete(true);
    item.setUpdatedBy(userDetails);
    
    final ShippmentPackage updatedItem = shippmentPackageRepository.save(item);
    return ResponseEntity.ok(updatedItem); 
  }
  
}
