package com.seakmeng.tracking_api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShippingDetail {

	@EmbeddedId
    private ShippingPackageLinkId linkPk = new ShippingPackageLinkId(); 
	
	@ManyToOne
    @MapsId("shippingId")
    private Shipping shipping;
	
    @ManyToOne
    @MapsId("shippmentPackageId")
    private ShippmentPackage shippmentPackage;
	
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
    
    public ShippingDetail() {
		
	}

	public ShippingPackageLinkId getLinkPk() {
		return linkPk;
	}

	public void setLinkPk(ShippingPackageLinkId linkPk) {
		this.linkPk = linkPk;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public ShippmentPackage getShippmentPackage() {
		return shippmentPackage;
	}

	public void setShippmentPackage(ShippmentPackage shippmentPackage) {
		this.shippmentPackage = shippmentPackage;
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
		return "ShippingDetail [linkPk=" + linkPk + ", shipping=" + shipping + ", shippmentPackage=" + shippmentPackage
				+ ", isDelete=" + isDelete + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt="
				+ updatedAt + ", updatedBy=" + updatedBy + "]";
	}
	
}
