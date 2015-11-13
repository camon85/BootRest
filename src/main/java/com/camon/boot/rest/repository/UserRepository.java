package com.camon.boot.rest.repository;

import com.camon.boot.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jooyong on 2015-11-13.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
