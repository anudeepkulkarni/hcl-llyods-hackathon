package com.hcl.hackaton.service.impl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.exception.ResourceNotFoundException;
import com.hcl.hackaton.model.CustomerDetails;
import com.hcl.hackaton.repository.CustomerRepository;
import com.hcl.hackaton.service.CustomerService;
import com.hcl.hackaton.util.EncryptionUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		customers.forEach(customer -> {
			try {
				customer.setEmail(EncryptionUtil.decrypt(customer.getEmail()));
				customer.setMobileNumber(EncryptionUtil.decrypt(customer.getMobileNumber()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return customers;
	}

	@Override
	public CustomerDetails getCustomerById(Long id) {
		Customer customerEntity = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

		try {
			customerEntity.setEmail(EncryptionUtil.decrypt(customerEntity.getEmail()));
			customerEntity.setMobileNumber(EncryptionUtil.decrypt(customerEntity.getMobileNumber()));
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception appropriately
		}

		return mapEntityToModel(customerEntity);
	}

	@Override
	public CustomerDetails createCustomer(CustomerDetails customerDetails) {
		// Encrypting sensitive fields before saving to DB
		try {
			customerDetails.setEmail(EncryptionUtil.encrypt(customerDetails.getEmail()));
			customerDetails.setMobileNumber(EncryptionUtil.encrypt(customerDetails.getMobileNumber()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Customer customerEntity = mapModelToEntity(customerDetails);
		customerRepository.save(customerEntity);
		return customerDetails;
	}

	@Override
	public CustomerDetails updateCustomer(Long id, CustomerDetails customerDetails) {
		Customer existingEntity = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

		// Encrypting sensitive fields before saving to DB
		try {
			customerDetails.setEmail(EncryptionUtil.encrypt(customerDetails.getEmail()));
			customerDetails.setMobileNumber(EncryptionUtil.encrypt(customerDetails.getMobileNumber()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		existingEntity.setCustomerName(customerDetails.getCustomerName());
		existingEntity.setCategory(customerDetails.getCategory());
		existingEntity.setOccupation(customerDetails.getOccupation());
		existingEntity.setMobileNumber(customerDetails.getMobileNumber());
		existingEntity.setEmail(customerDetails.getEmail());
		existingEntity.setDateOfBirth(customerDetails.getDateOfBirth());
		existingEntity.setAddress(customerDetails.getAddress());
		existingEntity.setLastModifiedDate(customerDetails.getLastModifiedDate());
		existingEntity.setKycStatus(customerDetails.isKycStatus());

		Customer updatedEntity = customerRepository.save(existingEntity);
		return mapEntityToModel(updatedEntity);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer existingEntity = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
		customerRepository.delete(existingEntity);
	}

	private CustomerDetails mapEntityToModel(Customer entity) {
		return CustomerDetails.builder().customerName(entity.getCustomerName()).category(entity.getCategory())
				.occupation(entity.getOccupation()).mobileNumber(entity.getMobileNumber()) // Already encrypted
				.email(entity.getEmail()) // Already encrypted
				.createdDate(entity.getCreatedDate()).dateOfBirth(entity.getDateOfBirth()).address(entity.getAddress())
				.lastModifiedDate(entity.getLastModifiedDate()).kycStatus(entity.isKycStatus()).build();
	}

	private Customer mapModelToEntity(CustomerDetails model) {
		return Customer.builder().customerName(model.getCustomerName()).category(model.getCategory())
				.occupation(model.getOccupation()).mobileNumber(model.getMobileNumber()) // Encrypt before saving
				.email(model.getEmail()) // Encrypting before saving
				.createdDate(model.getCreatedDate()).dateOfBirth(model.getDateOfBirth()).address(model.getAddress())
				.lastModifiedDate(model.getLastModifiedDate()).kycStatus(model.isKycStatus()).build();
	}

	public String fetchCaseStatus(String receiptNumber) throws Exception {
		
		return "Hello";
	}
}
