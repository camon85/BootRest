package com.camon.boot.rest.repository;

import com.camon.boot.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by jooyong on 2015-11-11.
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    List<Customer> findAllOrderByName();
}
