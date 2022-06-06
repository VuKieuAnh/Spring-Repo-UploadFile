package com.codegym.repo;

import com.codegym.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByFirstNameIsContaining(String name);
    Iterable<Customer> findAllByFirstNameIsContainingOrLastNameContaining(String name, String name2);
    Iterable<Customer> findAllByOrderByFirstName();
}
