package com.yellowmovement.site.domains;

import com.yellowmovement.site.security.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Data
@Entity
@Table(name="blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long blogId;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    @Lob
    private String content;

    private String image;

    private Date bloggedDate;

    @PrePersist
    void postedDate() {
        this.bloggedDate = new Date();
    }

    public String dateString(){
        SimpleDateFormat format = new SimpleDateFormat("h:mm a - EEE, MMM d");
        return format.format(this.bloggedDate);
    }

    @ManyToOne
    private User blogger;

    @ManyToMany(targetEntity = Comment.class)
    List<Comment> comments = new ArrayList<>() ;
}
