package com.yellowmovement.site.repositories;

import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE b.title LIKE CONCAT('%',:keyword,'%') or b.content LIKE CONCAT('%',:keyword,'%') order by b.bloggedDate desc")
    List<Blog> searchBlogs(@Param("keyword") String keyword);

    @Query("SELECT b.blogger FROM Blog b GROUP BY b.blogger ORDER BY count(b.blogger) DESC")
    List<User> findBloggersList();
}
