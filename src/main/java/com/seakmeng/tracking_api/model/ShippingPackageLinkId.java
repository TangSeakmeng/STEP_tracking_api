package com.seakmeng.tracking_api.model;

import javax.persistence.Embeddable;

@Embeddable
public class ShippingPackageLinkId implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;

//	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "shipping_id")
//    private Shipping shipping;
//
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "package_id")
//    private ShippmentPackage shippmentPackage;
	
	private Long shippingId;
	private Long shippmentPackageId;
	
	public ShippingPackageLinkId() {
		
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public Long getShippmentPackageId() {
		return shippmentPackageId;
	}

	public void setShippmentPackageId(Long shippmentPackageId) {
		this.shippmentPackageId = shippmentPackageId;
	}
    
}
