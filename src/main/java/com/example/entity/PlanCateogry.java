package com.example.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class PlanCateogry {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catrgoryId;
	
	private String categoryName;
	
	private String activeSw;
	
	private String createdBy;
	
	private String updatedBy;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createDate;
	
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
 

}
