package com.camon.boot.rest.repository;

import com.camon.boot.rest.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jooyong on 2015-11-11.
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    Page<Customer> findAllOrderByName(Pageable pageable);
}
