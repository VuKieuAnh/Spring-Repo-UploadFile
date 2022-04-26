package com.codegym.service.customer;

import com.codegym.exception.CustomerNotFound;
import com.codegym.model.Customer;
import com.codegym.repo.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) throws CustomerNotFound {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) return customer;
        throw new CustomerNotFound();

    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }
}
