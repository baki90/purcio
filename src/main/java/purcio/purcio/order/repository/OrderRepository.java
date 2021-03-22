package purcio.purcio.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
