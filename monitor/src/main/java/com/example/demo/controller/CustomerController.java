package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerMapper.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerMapper.findById(id);
    }

    @PostMapping
    public int createCustomer(@RequestBody Customer customer) {
        return customerMapper.insert(customer);
    }

    @PutMapping("/{id}")
    public int updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerMapper.update(customer);
    }

    @DeleteMapping("/{id}")
    public int deleteCustomer(@PathVariable Integer id) {
        return customerMapper.delete(id);
    }
}
