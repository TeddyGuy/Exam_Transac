package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.PostComment;

public class DaoJpaComment implements DAO<PostComment>{
    protected final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate.exe");
    @Override
    public long save(PostComment postComment) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(postComment);

        em.getTransaction().commit();
        em.close();
        return postComment.getId();
    }
}
