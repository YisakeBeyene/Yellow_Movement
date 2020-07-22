package com.yellowmovement.site.repositories;

import com.yellowmovement.site.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(String role);

}
