package com.yellowmovement.site.domains;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@AllArgsConstructor
@Table(name="posts")
public class Post {

	public Post() {}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long postId;

    @NotNull
    @NotBlank(message = "The title is required.")
    private String title;

    @NotNull
    @NotBlank(message = "Please provide the content.")
    @Lob
    private String content;

    @NotNull
    @NotBlank(message = "Please provide the category.")
    private String category;

    private String image;

    private Date postedDate;

    @PrePersist
    void postedDate() {
        this.postedDate = new Date();
    }

    @ManyToMany(targetEntity = Comment.class)
    List<Comment> comments = new ArrayList<>() ;

    public String dateString(){
        SimpleDateFormat format = new SimpleDateFormat("h:mm a - EEE, MMM d");
        return format.format(this.postedDate);
    }
}

