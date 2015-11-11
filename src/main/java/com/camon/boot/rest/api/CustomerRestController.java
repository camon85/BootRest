package com.camon.boot.rest.api;

import com.camon.boot.rest.domain.Customer;
import com.camon.boot.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by jooyong on 2015-11-11.
 */
@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
    @Autowired
    CustomerService service;

    @RequestMapping(method = GET)
    List<Customer> getCustomers() {
        List<Customer> customers = service.findAll();
        return customers;
    }

    @RequestMapping(value ="{id}", method = GET)
    Customer getCustomer(@PathVariable Integer id) {
        Customer customer = service.findOne(id);
        return customer;
    }
}
