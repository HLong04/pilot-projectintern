package com.be.pilot_project.service.impl;

import com.be.pilot_project.dto.CustomerDTO;
import com.be.pilot_project.entity.Customer;
import com.be.pilot_project.mapper.CustomerMapper;
import com.be.pilot_project.repository.CustomerRepository;
import com.be.pilot_project.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	@Override
	public List<CustomerDTO> findAll() {
		return customerRepository.findAll()
			.stream()
			.map(customerMapper::toDto)
			.toList();
	}
	
	@Override
	public CustomerDTO createCustomer(CustomerDTO customer) {
		Customer customerEntity = customerMapper.toEntity(customer);
		return customerMapper.toDto(customerRepository.save(customerEntity));
	}
	
	@Override
	public CustomerDTO updateCustomer(Integer id, CustomerDTO customer) {
		Customer existingCustomer = customerRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy customer id: " + id));
		
		existingCustomer.setCustomerName(customer.getCustomerName());
		existingCustomer.setContactName(customer.getContactName());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setPhone(customer.getPhone());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setIsLocked(customer.getIsLocked());
		
		return customerMapper.toDto(customerRepository.save(existingCustomer));
	}
	
	@Override
	public CustomerDTO getCustomer(Integer id) {
		return customerRepository.findById(id)
			.map(customerMapper::toDto)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy customer id: " + id));
	}
	
	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}
}
