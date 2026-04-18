package com.hcl.hackaton.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails {

  private String customerName;
  private String category;
  private String occupation;
  private String mobileNumber;
  private String email;
  private String createdDate;
  private String dateOfBirth;
  private String address;
  private String lastModifiedDate;
  private boolean kycStatus;
}

