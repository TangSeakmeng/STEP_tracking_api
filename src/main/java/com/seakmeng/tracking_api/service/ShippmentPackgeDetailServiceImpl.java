package com.seakmeng.tracking_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.seakmeng.tracking_api.model.ShippmentPackage;
import com.seakmeng.tracking_api.repository.ShippmentPackageRepository;

@Service
public class ShippmentPackgeDetailServiceImpl {

	@Autowired
	private ShippmentPackageRepository shippmentPackageRepository;
	
//	public Page<ShippmentPackage> getShippmentPackages(int pageNumber, int pageSize) {
//		Pageable page = PageRequest.of(pageNumber, pageSize);
//		return shippmentPackageRepository.findAll(page);
//	}
	
	public Page<ShippmentPackage> getShippmentPackages(Pageable page) {
		return shippmentPackageRepository.findAll(page);
	}
	
}
