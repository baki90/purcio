package purcio.purcio.product.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import purcio.purcio.product.dto.product.ProductRetrieveConditionDTO;
import purcio.purcio.product.dto.product.ProductRetrieveResponseDTO;
import purcio.purcio.product.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 조건에 따라 상품을 찾고, 페이징으로 반환한다.
     * @param condition 조회 조건
     * @param pageable page, size 입력
     * @return 상품 페이징 정보
     */
    @GetMapping("/api/product")
    public Page<ProductRetrieveResponseDTO> retrieveProductList(
            ProductRetrieveConditionDTO condition, Pageable pageable){

        return productService.retrieveProductList(condition, pageable);
    }
}
