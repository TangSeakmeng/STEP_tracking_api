package com.seakmeng.tracking_api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShippingService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = false)	
	private String name;

	@Column(name = "description", nullable = false)
	private String description;
	
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
	
	@ManyToOne
    @JoinColumn(name="updated_by", nullable=false, referencedColumnName = "id")
    private User updatedBy;
	
	public ShippingService() {

	}
	
	public ShippingService(Long id, String name, String description, Boolean isDelete, Date createdAt,
			User createdBy, Date updatedAt, User updatedBy) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ShippingService [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", isDelete=" + isDelete + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt="
				+ updatedAt + ", updatedBy=" + updatedBy + "]";
	}
	
}
