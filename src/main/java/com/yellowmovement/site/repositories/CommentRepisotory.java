package com.yellowmovement.site.repositories;

import com.yellowmovement.site.domains.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepisotory extends CrudRepository<Comment, Long> {
}
