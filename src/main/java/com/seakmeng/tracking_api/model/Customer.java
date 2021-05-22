package com.seakmeng.tracking_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", unique = true, nullable = false)
   private Long id;

   @Column(name = "username", nullable = false)
   private String username;

   @Column(name = "password", nullable = false)
   private String password;

   @Column(name = "email", nullable = false)
   private String email;
   
   @Column(name = "telephone", nullable = false)
   private String telephone;
   
   @Column(name = "address", nullable = false)
   private String address;

   @CreationTimestamp
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "created_at", nullable = false)
   private Date createdAt;

   @Column(name = "created_by", nullable = false)
   @CreatedBy
   private String createdBy;

   @UpdateTimestamp
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "updated_at", nullable = false)
   private Date updatedAt;

   @Column(name = "updated_by", nullable = false)
   @LastModifiedBy
   private String updatedBy;
   
   @OneToMany(cascade = CascadeType.ALL, targetEntity = ShippmentPackage.class)
   @JoinColumn(name = "customer_id")
   private List<ShippmentPackage> shippmentPackageList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
	   return password;
	}

	public void setPassword(String password) {
      this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<ShippmentPackage> getPackageList() {
		return shippmentPackageList;
	}

	public void setPackageList(List<ShippmentPackage> shippmentPackageList) {
		this.shippmentPackageList = shippmentPackageList;
	}

}
