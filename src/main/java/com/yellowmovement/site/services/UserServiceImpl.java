package com.yellowmovement.site.services;

import com.yellowmovement.site.repositories.RoleRepository;
import com.yellowmovement.site.repositories.UserRepository;
import com.yellowmovement.site.security.Role;
import com.yellowmovement.site.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {


    String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads/profile_pictures/";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        if (user.getSex().equals("female")){
            user.setProfilePic("people_female.jpg");
        }else if (user.getSex().equals("male")) {
            user.setProfilePic("people_male.jpeg");
        }
        else{
            throw new NullPointerException();
        }
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean uploadImage(User user, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                if (!Files.exists(Paths.get(uploadDirectory))) {
                    try {
                        Files.createDirectories(Paths.get(uploadDirectory));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        return false;
                    }
                }
                Files.copy(file.getInputStream(), Paths.get(uploadDirectory, file.getOriginalFilename()));
                user.setProfilePic(file.getOriginalFilename());
                userRepository.save(user);
                return true;

            } catch (FileAlreadyExistsException e){
                user.setProfilePic(file.getOriginalFilename());
                save(user);
                return true;
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean makeAdmin(User user) {
        Role adminRole = roleRepository.findByRole("ADMIN");
        if(!user.getRoles().contains(adminRole)) {
            try {
                user.getRoles().add(adminRole);
                userRepository.save(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean makeBlogger(User user) {
        Role bloggerRole = roleRepository.findByRole("BLOGGER");
        if(!user.getRoles().contains(bloggerRole)) {
            try {
                user.getRoles().add(bloggerRole);
                userRepository.save(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean unmakeBlogger(User user) {
        Role bloggerRole = roleRepository.findByRole("BLOGGER");
        if(user.getRoles().contains(bloggerRole)){
            try{
                user.getRoles().remove(bloggerRole);
                userRepository.save(user);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);

        if(user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User with email '" + s + "' not found");
    }
}
