package com.be.pilot_project.service.interfaces;

import com.be.pilot_project.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
	List<CustomerDTO> findAll();
	
	CustomerDTO createCustomer(CustomerDTO customer);
	
	CustomerDTO updateCustomer(Integer id, CustomerDTO customer);
	
	CustomerDTO getCustomer(Integer id);
	
	void deleteCustomer(Integer id);
}
