import model.Post;
import persistence.DaoJpaComment;
import persistence.DaoJpaPost;
import persistence.DaoJpaUser;
import service.PostService;

public class Main {
    public static void main(String[] args) {
        PostService postService = new PostService(new DaoJpaPost(), new DaoJpaUser(), new DaoJpaComment());

        var posteurId = postService.addUser("Posteur1");
        var commentateurId = postService.addUser("Commentateur1");
        var postId = postService.addPost(posteurId, "Mon 1er post");
        postService.addComment(commentateurId,postId,"Mon commentaire 1");

        final Post post = postService.getPost(postId);
        System.out.println(post);
    }
}
