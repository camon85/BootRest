package com.camon.boot.rest.api;

import com.camon.boot.rest.domain.Customer;
import com.camon.boot.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(value = "{id}", method = GET)
    Customer getCustomer(@PathVariable Integer id) {
        Customer customer = service.findOne(id);
        return customer;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    Customer postCustomers(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @RequestMapping(value = "{id}", method = PUT)
    Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.setId(id);
        return service.update(customer);
    }

    @RequestMapping(value = "{id}", method = DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable Integer id) {
        service.delete(id);
    }
}
