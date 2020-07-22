package com.yellowmovement.site.services;

import com.yellowmovement.site.domains.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> searchPosts(@Param("keyword") String keyword);

    List<String> findCategoriesList();

    public Post save(Post post);

    public Post save(Post post, MultipartFile file);

    public Iterable<Post> saveAll(Iterable<Post> post);

    Optional<Post> findById(Long id);

    boolean existsById(Long id);

    Iterable<Post> findAll();

    Iterable<Post> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Post post);

    void deleteAll(Iterable<Post> post);

    void deleteAll();

    Iterable<Post> findAll(Sort sort);

    Page<Post> findAll(Pageable pageable);

}
