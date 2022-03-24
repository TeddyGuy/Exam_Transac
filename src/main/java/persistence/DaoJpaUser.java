package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.PostUser;

public class DaoJpaUser implements DAO<PostUser>{
    protected final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate.exe");
    @Override
    public long save(PostUser postUser) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(em.contains(postUser)){
            em.merge(postUser);
        }
        else{
            em.persist(postUser);
        }
        em.getTransaction().commit();
        em.close();
        return postUser.getId();
    }

    public PostUser find(long postUserId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final PostUser postUser = em.find(PostUser.class, postUserId);

        em.getTransaction().commit();
        em.close();
        return postUser;
    }
}
