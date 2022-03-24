package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Post;
import model.PostComment;
import model.PostUser;
import persistence.DaoJpaComment;
import persistence.DaoJpaPost;
import persistence.DaoJpaUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostService {
    DaoJpaPost postRepository;
    DaoJpaUser postUserRepository;
    DaoJpaComment postCommentRepository;


    public long addUser(String postUser) {
        return postUserRepository.save(PostUser.builder().name(postUser).build());
    }

    public long addPost(long posteurId, String post) {
        PostUser postUser = postUserRepository.find(posteurId);
        return postRepository.save(Post.builder().postUser(postUser).postData(post).build());
    }

    public void addComment(long commentateurId, long postId, String comment) {

        Post post = postRepository.findWithComments(postId);
        PostUser commentateur = postUserRepository.find(commentateurId);

        PostComment postComment = PostComment.builder().post(post).commentateur(commentateur).comment(comment).build();

        post.addComment(postComment);

        postCommentRepository.save(postComment);
        postRepository.merge(post);
    }

    public Post getPost(long postId) {
        return postRepository.findWithComments(postId);
    }
}
