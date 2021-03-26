package purcio.purcio.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.order.domain.Cart;
import purcio.purcio.order.domain.Order;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
