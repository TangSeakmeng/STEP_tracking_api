package com.seakmeng.tracking_api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShippingTracking  {

	@EmbeddedId
    private ShippingTrackingLinkId linkPk = new ShippingTrackingLinkId(); 
	
	@ManyToOne
    @MapsId("shippingId")
    private Shipping shipping;
	
    @ManyToOne
    @MapsId("trackingProgressId")
    private TrackingProgress trackingProgress;
    
    @Column(name = "tracking_order", nullable = false)
	private Integer trackingOrder;
    
    @Column(name = "datetime", nullable = false)
	private Date dateTime;
    
    @Column(name = "origin", nullable = false)
	private String origin;
    
    @Column(name = "destination", nullable = false)
	private String destination;
	
	@Column(name = "is_delete", nullable = false)
	private Boolean isDelete;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
	
	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name="created_by", nullable=false, referencedColumnName = "id")
    private User createdBy;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;
	
	@ManyToOne
    @JoinColumn(name="updated_by", nullable=false, referencedColumnName = "id")
    private User updatedBy;
    
    public ShippingTracking() {
		
	}

	public Integer getTrackingOrder() {
		return trackingOrder;
	}

	public void setTrackingOrder(Integer trackingOrder) {
		this.trackingOrder = trackingOrder;
	}

	public ShippingTrackingLinkId getLinkPk() {
		return linkPk;
	}

	public void setLinkPk(ShippingTrackingLinkId linkPk) {
		this.linkPk = linkPk;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public TrackingProgress getTrackingProgress() {
		return trackingProgress;
	}

	public void setTrackingProgress(TrackingProgress trackingProgress) {
		this.trackingProgress = trackingProgress;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "ShippingTracking [linkPk=" + linkPk + ", shipping=" + shipping + ", trackingProgress="
				+ trackingProgress + ", trackingOrder=" + trackingOrder + ", dateTime=" + dateTime + ", origin="
				+ origin + ", destination=" + destination + ", isDelete=" + isDelete + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}
	
}
