package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String postData;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private PostUser postUser;

    @OneToMany(mappedBy = "post")
    private List<PostComment> comments;

    public void addComment(PostComment postComment){
        comments.add(postComment);
    }
}
