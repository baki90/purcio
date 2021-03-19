package purcio.purcio.product.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.user.domain.User;

import javax.persistence.*;

/**
 * 사용자가 좋아요를 눌러 놓은 상점
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartShop {

    @Id
    @GeneratedValue
    @Column(name="heart_shop_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Builder
    public HeartShop(User user, Shop shop) {
        this.user = user;
        this.shop = shop;
    }
}
