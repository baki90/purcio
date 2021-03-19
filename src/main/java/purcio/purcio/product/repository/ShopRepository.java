package purcio.purcio.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.product.domain.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
