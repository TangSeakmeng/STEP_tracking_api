package com.seakmeng.tracking_api.model;

import javax.persistence.Embeddable;

@Embeddable
public class ShippingTrackingLinkId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long shippingId;
	private Long trackingProgressId;
	
	public ShippingTrackingLinkId() {
		
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public Long getTrackingProgressId() {
		return trackingProgressId;
	}

	public void setTrackingProgressId(Long trackingProgressId) {
		this.trackingProgressId = trackingProgressId;
	}
	
}
