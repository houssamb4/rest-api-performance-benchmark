import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import ma.variante.model.Category;
import ma.variante.model.Item;

import java.util.List;

public class ItemDAO {
    private static final EntityManager em = Persistence.createEntityManagerFactory("serviceA_PU").createEntityManager();

    public List<Item> findPaged(int page, int size) {
        int first = Math.max(0, page) * Math.max(1, size);
        return em.createQuery("SELECT i FROM Item i", Item.class)
                .setFirstResult(first)
                .setMaxResults(Math.max(1, size))
                .getResultList();
    }

    public long countAll() {
        return em.createQuery("SELECT COUNT(i) FROM Item i", Long.class).getSingleResult();
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findByCategoryId(Long categoryId) {
        return em.createQuery("SELECT i FROM Item i WHERE i.category.id = :cid", Item.class)
                .setParameter("cid", categoryId)
                .getResultList();
    }

    public void save(Item item) {
        em.getTransaction().begin();
        if (item.getCategory() != null && item.getCategory().getId() != null) {
            Category managedCategory = em.find(Category.class, item.getCategory().getId());
            item.setCategory(managedCategory);
        }
        em.persist(item);
        em.getTransaction().commit();
    }

    public Item update(Long id, Item update) {
        em.getTransaction().begin();
        Item managed = em.find(Item.class, id);
        if (managed == null) {
            em.getTransaction().rollback();
            return null;
        }
        managed.setName(update.getName());
        managed.setPrice(update.getPrice());
        managed.setStock(update.getStock());
        if (update.getCategory() != null && update.getCategory().getId() != null) {
            Category managedCategory = em.find(Category.class, update.getCategory().getId());
            managed.setCategory(managedCategory);
        }
        em.getTransaction().commit();
        return managed;
    }

    public boolean delete(Long id) {
        em.getTransaction().begin();
        Item managed = em.find(Item.class, id);
        if (managed == null) {
            em.getTransaction().rollback();
            return false;
        }
        em.remove(managed);
        em.getTransaction().commit();
        return true;
    }
}


