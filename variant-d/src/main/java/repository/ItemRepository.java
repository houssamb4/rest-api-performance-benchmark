import model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "items")
public interface ItemRepository extends JpaRepository<Item, Long> {
    @RestResource(path = "byCategory", rel = "byCategory")
    Page<Item> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
    
    // For completeness, keep a non-paginated variant if needed internally
    List<Item> findByCategoryId(Long categoryId);
}
