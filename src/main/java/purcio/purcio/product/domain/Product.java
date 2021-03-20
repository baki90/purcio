package purcio.purcio.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.exception.NotEnoughStockException;
import purcio.purcio.common.model.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop; // 판매하는 샵

    private String name; // 판매 상품 이름
    private Category category; // 판매 카테고리
    private Long price; // 판매되는 가격
    private int stockQuantity; // 재고



    /** 비즈니스 메소드 */
    // 재고 수량 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("재고가 부족합니다.");
        }

        this.stockQuantity = restStock;
    }

    @Builder
    public Product(Shop shop, String name, Category category, Long price, int stockQuantity) {
        this.shop = shop;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
