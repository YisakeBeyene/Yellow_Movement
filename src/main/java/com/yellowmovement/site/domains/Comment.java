package com.yellowmovement.site.domains;

import com.yellowmovement.site.security.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Data
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long commentId;

    @OneToOne
    private User commenter;

    @NotNull
    @NotBlank(message = "Please say something first")
    private String content;

    private Date commentedDate;

    @PrePersist
    void commentedDate() {
        this.commentedDate = new Date();
    }

    public String dateString(){
        SimpleDateFormat format = new SimpleDateFormat("h:mm a - EEE, MMM d");
        return format.format(this.commentedDate);
    }

}
