package purcio.purcio.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.product.domain.Product;
import purcio.purcio.product.domain.Shop;
import purcio.purcio.product.repository.product.CustomProductRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {
}
