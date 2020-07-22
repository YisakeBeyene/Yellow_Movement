package com.yellowmovement.site.repositories;

import com.yellowmovement.site.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
