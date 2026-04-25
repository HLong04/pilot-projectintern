package com.be.pilot_project.controller;

import com.be.pilot_project.dto.CustomerDTO;
import com.be.pilot_project.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService customerService;
	
	
	@GetMapping()
	public List<CustomerDTO> getAllCustomers() {
		return customerService.findAll();
	}
	
	@GetMapping("/{id}")
	public CustomerDTO getCustomerById(@PathVariable Integer id) {
		return customerService.getCustomer(id);
	}
	
	@PostMapping
	public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.createCustomer(customerDTO);
	}
	
	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(id, customerDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
	}
}
