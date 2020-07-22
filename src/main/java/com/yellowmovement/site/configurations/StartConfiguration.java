package com.yellowmovement.site.configurations;

import com.yellowmovement.site.repositories.RoleRepository;
import com.yellowmovement.site.security.Role;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartConfiguration {
    RoleRepository roleRepository;
    UserService userService;

    @Autowired
    public StartConfiguration(RoleRepository roleRepository, UserService userService){
        this.roleRepository=roleRepository;
        this.userService=userService;
    }

    @Bean
    public void polulateInitialRoles(){
        String [] roles = {"USER", "BLOGGER", "ADMIN"};

        System.out.println("\nChecking existence of Roles...\n");

        for (String role :
                roles) {
            if (roleRepository.findByRole(role) == null){
                System.out.println("\nAdding role "+role+"...\n");
                Role newRole = new Role(role);
                roleRepository.save(newRole);
            }
        }
    }

//    @Bean
    public void polulateInitialUsers(){

        System.out.println("\nChecking existence of Admin...\n");
        if (userService.findUserByEmail("admin1@admin.com") == null){
            System.out.println("\nRegistering New Admin...\n");
            User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin1@admin.com");
                admin.setPassword("0000");
                admin.setSex("female");
            userService.saveUser(admin);

        }

        User adminAgain = userService.findUserByEmail("admin@admin.com");
        userService.makeAdmin(adminAgain);

    }
}
