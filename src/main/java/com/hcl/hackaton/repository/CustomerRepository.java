package com.hcl.hackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.hackaton.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


	Optional<Customer> findByMobileNumber(String mobileNumber);

	Optional<Customer> findByEmail(String email);

}
