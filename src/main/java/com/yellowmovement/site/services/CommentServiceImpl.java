package com.yellowmovement.site.services;

import com.yellowmovement.site.domains.Comment;
import com.yellowmovement.site.repositories.CommentRepisotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepisotory commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepisotory commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
