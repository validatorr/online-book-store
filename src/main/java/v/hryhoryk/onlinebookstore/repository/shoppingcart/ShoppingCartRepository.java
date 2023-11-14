package v.hryhoryk.onlinebookstore.repository.shoppingcart;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v.hryhoryk.onlinebookstore.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"user", "cartItems", "cartItems.book"})
    ShoppingCart findByUserId(Long userId);
}
