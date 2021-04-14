package purcio.purcio.product.dto.product;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import purcio.purcio.product.domain.Category;

@Data
@NoArgsConstructor
public class ProductRetrieveResponseDTO {
    private String name; // 판매 상품 이름
    private Category category; // 판매 카테고리
    private int price; // 판매되는 가격
    private int hearts;


    public ProductRetrieveResponseDTO(String name, Category category, int price, int hearts) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.hearts = hearts;
    }
}
