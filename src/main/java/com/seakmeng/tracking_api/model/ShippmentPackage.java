package com.seakmeng.tracking_api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShippmentPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "origin_tracking_number", unique = true, nullable = false)
	private String originTrackingNumber;
	
	@Column(name = "package_code", unique = true, nullable = false)
	private String packageCode;
	
	@Column(name = "name", nullable = false)	
	private String name;

	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "weight", nullable = false)
	private Double weight;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
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
	
	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name="updated_by", nullable=false, referencedColumnName = "id")
    private User updatedBy;
	
	@ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_id", nullable=true, referencedColumnName = "id")
    private Customer owner;
	
	public ShippmentPackage() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginTrackingNumber() {
		return originTrackingNumber;
	}

	public void setOriginTrackingNumber(String originTrackingNumber) {
		this.originTrackingNumber = originTrackingNumber;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
}
