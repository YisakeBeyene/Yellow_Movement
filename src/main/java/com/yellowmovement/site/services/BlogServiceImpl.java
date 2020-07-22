package com.yellowmovement.site.services;

import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.repositories.BlogRepository;
import com.yellowmovement.site.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    BlogRepository blogRepository;
    String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads/blog-images";

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> searchPosts(String keyword) {
        return blogRepository.searchBlogs(keyword);
    }

    @Override
    public List<User> findBloggersList() {
        return blogRepository.findBloggersList();
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog save(Blog blog, MultipartFile file, User blogger) {
        if (!file.isEmpty()) {
            try {
                if (!Files.exists(Paths.get(uploadDirectory))) {
                    try {
                        Files.createDirectories(Paths.get(uploadDirectory));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                Files.copy(file.getInputStream(), Paths.get(uploadDirectory, file.getOriginalFilename()));
                blog.setImage(file.getOriginalFilename());
                blog.setBlogger(blogger);

                blogRepository.save(blog);

            }catch (FileAlreadyExistsException e){
                blog.setBlogger(blogger);
                blog.setImage(file.getOriginalFilename());
                blogRepository.save(blog);
            }
            catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
        }

        blog.setBlogger(blogger);
        return blogRepository.save(blog);
    }

    @Override
    public Iterable<Blog> saveAll(Iterable<Blog> blog) {
        return blogRepository.saveAll(blog);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return blogRepository.existsById(id);
    }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Iterable<Blog> findAllById(Iterable<Long> ids) {
        return blogRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return blogRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

    @Override
    public void deleteAll(Iterable<Blog> blog) {
        blogRepository.deleteAll(blog);
    }

    @Override
    public void deleteAll() {
        blogRepository.deleteAll();
    }

    @Override
    public Iterable<Blog> findAll(Sort sort) {
        return blogRepository.findAll(sort);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}
