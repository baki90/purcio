package purcio.purcio.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.shop.domain.Shop;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue
    @Column(name="product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop; // 판매하는 샵

    private String name; // 판매 상품 이름
    private Category category; // 판매 카테고리
    private Long price; // 판매되는 가격

}
