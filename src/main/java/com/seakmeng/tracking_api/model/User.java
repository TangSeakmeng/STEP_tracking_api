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
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", unique = true, nullable = false)
   private Long id;

   @Column(name = "username", nullable = false)
   private String username;

   @Column(name = "password", nullable = false)
   private String password;

   @Column(name = "first_name", nullable = false)
   private String firstName;

   @Column(name = "last_name", nullable = false)
   private String lastName;

   @Column(name = "email_address", nullable = false)
   private String email;
   
   @Column(name = "is_admin", nullable = false)
   private Boolean isAdmin;

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
   
   @OneToMany(cascade = CascadeType.ALL, targetEntity = ShippingService.class)
   @JoinColumn(name = "created_by")
   private List<ShippingService> shippingServiceCreatedList;

   @OneToMany(cascade = CascadeType.ALL, targetEntity = ShippingService.class)
   @JoinColumn(name = "updated_by")
   private List<ShippingService> shippingServiceUpdatedList;
   
   @OneToMany(cascade = CascadeType.ALL, targetEntity = TrackingProgress.class)
   @JoinColumn(name = "created_by")
   private List<TrackingProgress> trackingProgressCreatedList;

   @OneToMany(cascade = CascadeType.ALL, targetEntity = TrackingProgress.class)
   @JoinColumn(name = "updated_by")
   private List<TrackingProgress> trackingProgressUpdatedList;

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

	public String getFirstName() {
       return firstName;
	}

	public void setFirstName(String firstName) {
       this.firstName = firstName;
	}

	public String getLastName() {
       return lastName;
	}

	public void setLastName(String lastName) {
       this.lastName = lastName;
	}

	public String getEmail() {
       return email;
	}

	public void setEmail(String email) {
       this.email = email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
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

   @Override
   public String toString() {
       return "User{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", createdAt=" + createdAt +
               ", createdBy='" + createdBy + '\'' +
               ", updatedAt=" + updatedAt +
               ", updatedby='" + updatedBy + '\'' +
               '}';
   }

}
