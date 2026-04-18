package com.hcl.hackaton.service;

import java.util.List;

import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.model.CustomerDetails;

public interface CustomerService {

    List<Customer> getAllCustomers();

    CustomerDetails getCustomerById(Long id);

    CustomerDetails createCustomer(CustomerDetails customerDetails);

    CustomerDetails updateCustomer(Long id, CustomerDetails customerDetails);

    void deleteCustomer(Long id);
}
