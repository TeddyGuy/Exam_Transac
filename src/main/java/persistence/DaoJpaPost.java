package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Post;

public class DaoJpaPost implements DAO<Post>{
    protected final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate.exe");
    @Override
    public long save(Post post) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(post);

        em.getTransaction().commit();
        em.close();
        return post.getId();
    }

    public void merge(Post post) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(post);

        em.getTransaction().commit();
        em.close();
    }

    public Post find(long postId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Post post = em.find(Post.class, postId);

        em.getTransaction().commit();
        em.close();
        return post;
    }

    public Post findWithComments(long postId){
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Post> query = em.createQuery(
                "select p " +
                        "from Post p left join fetch p.comments " +
                        "where p.id = :postId "
                ,Post.class);

        query.setParameter("postId", postId);

        final Post post = query.getSingleResult();

        em.getTransaction().commit();
        em.close();

        return post;
    }
}
