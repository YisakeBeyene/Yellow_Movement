package com.yellowmovement.site.services;

import com.yellowmovement.site.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {

    User findUserByEmail(String email);
    void saveUser(User user);

    public User save(User post);

    boolean uploadImage(User user, MultipartFile file);

    boolean makeAdmin(User user);

    boolean makeBlogger(User user);

    boolean unmakeBlogger(User user);
}
