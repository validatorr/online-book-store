package v.hryhoryk.onlinebookstore.repository.order;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v.hryhoryk.onlinebookstore.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"orderItems.book", "user"})
    List<Order> findAllByUserId(Long userId);

    @EntityGraph(attributePaths = {"orderItems.book", "user"})
    Optional<Order> findByIdAndUserId(Long id, Long userId);
}
