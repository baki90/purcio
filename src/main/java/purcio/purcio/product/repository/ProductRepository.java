package purcio.purcio.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.product.domain.Product;
import purcio.purcio.product.domain.Shop;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
