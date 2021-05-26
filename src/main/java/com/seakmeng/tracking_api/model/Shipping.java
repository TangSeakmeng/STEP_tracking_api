package com.seakmeng.tracking_api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "shipping_date", nullable = false)	
	private Date shippingDate;

	@ManyToOne(targetEntity = ShippingService.class)
    @JoinColumn(name="shipping_service_id", nullable=false, referencedColumnName = "id")
    private ShippingService shippingService;
	
	@ManyToOne(targetEntity = TrackingProgress.class)
    @JoinColumn(name="tracking_progress_id", nullable=false, referencedColumnName = "id")
	private TrackingProgress trackingProgress;
	
	@Column(name = "shipping_controller_name", nullable = false)
	private String shippingControllerName;
	
	@Column(name = "shipping_document_number", nullable = false)
	private String shippingDocumentNumber;
	
	@Column(name = "shipping_origin", nullable = false)
	private String shippingOrigin;
	
	@Column(name = "shipping_destination", nullable = false)
	private String shippingDestination;
	
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
	
	public Shipping() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public TrackingProgress getTrackingProgress() {
		return trackingProgress;
	}

	public void setTrackingProgress(TrackingProgress trackingProgress) {
		this.trackingProgress = trackingProgress;
	}

	public String getShippingControllerName() {
		return shippingControllerName;
	}

	public void setShippingControllerName(String shippingControllerName) {
		this.shippingControllerName = shippingControllerName;
	}

	public String getShippingDocumentNumber() {
		return shippingDocumentNumber;
	}

	public void setShippingDocumentNumber(String shippingDocumentNumber) {
		this.shippingDocumentNumber = shippingDocumentNumber;
	}

	public String getShippingOrigin() {
		return shippingOrigin;
	}

	public void setShippingOrigin(String shippingOrigin) {
		this.shippingOrigin = shippingOrigin;
	}

	public String getShippingDestination() {
		return shippingDestination;
	}

	public void setShippingDestination(String shippingDestination) {
		this.shippingDestination = shippingDestination;
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
		return "Shipping [id=" + id + ", shippingDate=" + shippingDate + ", shippingService=" + shippingService
				+ ", trackingProgress=" + trackingProgress + ", shippingControllerName=" + shippingControllerName
				+ ", shippingDocumentNumber=" + shippingDocumentNumber + ", shippingOrigin=" + shippingOrigin
				+ ", shippingDestination=" + shippingDestination + ", isDelete=" + isDelete + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}
	
}
