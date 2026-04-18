package com.hcl.hackaton.entity;

import jakarta.persistence.Column;

//import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerName;
	private String category;
	private String occupation;
	private String mobileNumber;
	private String email;
	private String address;
	private String createdDate;
	private String lastModifiedDate;
	private String dateOfBirth;
	private boolean kycStatus;

}
