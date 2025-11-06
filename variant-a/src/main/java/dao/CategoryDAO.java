 import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import ma.variante.model.Category;

import java.util.List;

public class CategoryDAO {
    private static final EntityManager em = Persistence.createEntityManagerFactory("serviceA_PU").createEntityManager();

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public List<Category> findPaged(int page, int size) {
        int first = Math.max(0, page) * Math.max(1, size);
        return em.createQuery("SELECT c FROM Category c", Category.class)
                .setFirstResult(first)
                .setMaxResults(Math.max(1, size))
                .getResultList();
    }

    public long countAll() {
        return em.createQuery("SELECT COUNT(c) FROM Category c", Long.class).getSingleResult();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public void save(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    public Category update(Long id, Category update) {
        em.getTransaction().begin();
        Category managed = em.find(Category.class, id);
        if (managed == null) {
            em.getTransaction().rollback();
            return null;
        }
        managed.setCode(update.getCode());
        managed.setName(update.getName());
        em.getTransaction().commit();
        return managed;
    }

    public boolean delete(Long id) {
        em.getTransaction().begin();
        Category managed = em.find(Category.class, id);
        if (managed == null) {
            em.getTransaction().rollback();
            return false;
        }
        em.remove(managed);
        em.getTransaction().commit();
        return true;
    }
}