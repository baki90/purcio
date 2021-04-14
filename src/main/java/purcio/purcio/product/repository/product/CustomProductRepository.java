package purcio.purcio.product.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import purcio.purcio.product.dto.product.ProductRetrieveConditionDTO;
import purcio.purcio.product.dto.product.ProductRetrieveResponseDTO;

public interface CustomProductRepository {

    public Page<ProductRetrieveResponseDTO> retrieve(ProductRetrieveConditionDTO condition, Pageable pageable);
}
