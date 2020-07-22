package com.yellowmovement.site.repositories;

import com.yellowmovement.site.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%',:keyword,'%') or p.category LIKE CONCAT('%',:keyword,'%') or p.content LIKE CONCAT('%',:keyword,'%') order by p.postedDate desc")
    List<Post> searchPosts(@Param("keyword") String keyword);

    @Query("SELECT p.category FROM Post p GROUP BY p.category ORDER BY count(p.category) DESC")
    List<String> findCategoriesList();

}
