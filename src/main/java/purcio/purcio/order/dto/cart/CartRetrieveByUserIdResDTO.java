package purcio.purcio.order.dto.cart;

import lombok.Builder;
import lombok.Data;
import purcio.purcio.product.domain.Category;
import purcio.purcio.product.domain.Product;

@Data
public class CartRetrieveByUserIdResDTO {

    // TODO: 추후 shop name과 id 추가 예정
    private String name; // 판매 상품 이름
    private Category category; // 판매 카테고리
    private int price; // 판매되는 가격
    private int count; // 수량

    @Builder
    public CartRetrieveByUserIdResDTO(String name, Category category, int price, int count) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.count = count;
    }
}
