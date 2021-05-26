package com.seakmeng.tracking_api.controller;

import com.seakmeng.tracking_api.exception.ResourceNotFoundException;
import com.seakmeng.tracking_api.model.Shipping;
import com.seakmeng.tracking_api.model.ShippingDetail;
import com.seakmeng.tracking_api.model.ShippmentPackage;
import com.seakmeng.tracking_api.model.User;
import com.seakmeng.tracking_api.repository.ShippingDetailRepository;
import com.seakmeng.tracking_api.repository.ShippingRepository;
import com.seakmeng.tracking_api.repository.ShippmentPackageRepository;
import com.seakmeng.tracking_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShippingDetailController {

  @Autowired
  private ShippingDetailRepository shippingDetailRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private ShippingRepository shippingRepository;
  
  @Autowired
  private ShippmentPackageRepository shippmentPackageRepository;

  @GetMapping("/shipping_details/{shippingId}")
  public ResponseEntity<List<ShippingDetail>> getShippingDetailsById(
	  @PathVariable(value = "shippingId") Long shippingId)
			  throws ResourceNotFoundException {
	  List<ShippingDetail> shippingDetails = shippingDetailRepository.findShippingDetailsByShippingIdNative(shippingId);
	  return ResponseEntity.ok().body(shippingDetails);
  }

  @PostMapping(path = "/shipping_detail", consumes = "application/json", produces = "application/json")
  public ShippingDetail createShippingDetail(@RequestBody ShippingDetail shippingDetail) {
	  User userDetails = userRepository.findByUsername(shippingDetail.getCreatedBy().getUsername());
	  Shipping shipping = shippingRepository.findById(shippingDetail.getShipping().getId()).get();
	  ShippmentPackage shippmentPackage = shippmentPackageRepository.findById(shippingDetail.getShippmentPackage().getId()).get();
	  
	  shippingDetail.setShipping(shipping);
	  shippingDetail.setShippmentPackage(shippmentPackage);
	  shippingDetail.setCreatedBy(userDetails);
	  shippingDetail.setUpdatedBy(userDetails);
	  
	  return shippingDetailRepository.save(shippingDetail);
  }

  @PutMapping("/shipping_detail/{shippingId}/{packageId}")
  public ResponseEntity<ShippingDetail> updateShippingDetail(
      @PathVariable(value = "shippingId") Long shippingId, 
      @PathVariable(value = "packageId") Long packageId,
      @RequestBody ShippingDetail shippingDetailData)
    		  throws ResourceNotFoundException {	  
	  ShippingDetail shippingDetail = shippingDetailRepository.findShippingDetailByShippingIdAndPackageIdNative(shippingId, packageId);
	  User userDetails = userRepository.findByUsername(shippingDetailData.getUpdatedBy().getUsername());
	  
	  shippingDetail.setUpdatedBy(userDetails);
	  
	  final ShippingDetail updatedShippingDetail = shippingDetailRepository.save(shippingDetail);
	  return ResponseEntity.ok(updatedShippingDetail);
  }
  
  @PutMapping("/shipping_detail/{shippingId}/{packageId}/delete")
  public ResponseEntity<ShippingDetail> deleteShippingDetail(
	  @PathVariable(value = "shippingId") Long shippingId, 
      @PathVariable(value = "packageId") Long packageId,
      @RequestBody ShippingDetail shippingDetailData)
    		  throws Exception {
	  ShippingDetail shippingDetail = shippingDetailRepository.findShippingDetailByShippingIdAndPackageIdNative(shippingId, packageId);	
	  User userDetails = userRepository.findByUsername(shippingDetailData.getUpdatedBy().getUsername());
	  
	  shippingDetail.setIsDelete(true);
	  shippingDetail.setUpdatedBy(userDetails);
	
	  final ShippingDetail updatedShippingDetail = shippingDetailRepository.save(shippingDetail);
	  return ResponseEntity.ok(updatedShippingDetail); 
  }
  
}
