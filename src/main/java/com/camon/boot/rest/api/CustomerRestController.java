package com.camon.boot.rest.api;

import com.camon.boot.rest.domain.Customer;
import com.camon.boot.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
        Page<Customer> customers = service.findAll(pageable);
        return customers;
    }

    @RequestMapping(value = "{id}", method = GET)
    Customer getCustomer(@PathVariable Integer id) {
        Customer customer = service.findOne(id);
        return customer;
    }

    @RequestMapping(method = POST)
    ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
        Customer created = service.create(customer);
        URI location = uriComponentsBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
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
