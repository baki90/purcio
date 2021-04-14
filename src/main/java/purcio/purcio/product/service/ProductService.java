package purcio.purcio.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.product.dto.product.ProductRetrieveConditionDTO;
import purcio.purcio.product.dto.product.ProductRetrieveResponseDTO;
import purcio.purcio.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 조건에 따라 상품을 찾고, 페이징으로 반환한다.
     * @param condition 조회 조건
     * @param pageable offset, size
     * @return 상품 페이징 정보
     */
    public Page<ProductRetrieveResponseDTO> retrieveProductList(ProductRetrieveConditionDTO condition, Pageable pageable){
        return productRepository.retrieve(condition, pageable);
    }
}