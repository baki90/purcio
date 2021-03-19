package purcio.purcio.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.user.domain.User;

import javax.persistence.*;


/**
 * 사용자가 좋아요를 눌러 놓은 상품
 * (즐겨찾기)
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartProduct {

    @Id
    @GeneratedValue
    @Column(name="heart_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public HeartProduct(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
