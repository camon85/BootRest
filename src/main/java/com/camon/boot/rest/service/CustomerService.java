package com.camon.boot.rest.service;

import com.camon.boot.rest.domain.Customer;
import com.camon.boot.rest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by jooyong on 2015-11-11.
 */
@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAllOrderByName();
    }

    public Customer findOne(Integer id) {
        return repository.findOne(id);
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}