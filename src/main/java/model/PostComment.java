package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "commentateur_id")
    private PostUser commentateur;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Post post;

}
