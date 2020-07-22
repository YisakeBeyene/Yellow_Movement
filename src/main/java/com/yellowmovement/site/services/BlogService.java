package com.yellowmovement.site.services;

import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> searchPosts(@Param("keyword") String keyword);

    List<User> findBloggersList();

    public Blog save(Blog blog);

    public Blog save(Blog blog, MultipartFile file, User blogger);

    public Iterable<Blog> saveAll(Iterable<Blog> blog);

    Optional<Blog> findById(Long id);

    boolean existsById(Long id);

    Iterable<Blog> findAll();

    Iterable<Blog> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Blog blog);

    void deleteAll(Iterable<Blog> blog);

    void deleteAll();

    Iterable<Blog> findAll(Sort sort);

    Page<Blog> findAll(Pageable pageable);
}
