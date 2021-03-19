package purcio.purcio.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.shop.domain.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
