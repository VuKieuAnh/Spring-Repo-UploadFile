package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping()
    public ResponseEntity<Iterable<Customer>> findAll(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> editById(@PathVariable Long id, @RequestBody Customer customer){
        customer.setId(id);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}