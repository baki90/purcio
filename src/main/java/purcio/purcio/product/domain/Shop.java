package purcio.purcio.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.user.domain.User;

import javax.persistence.*;

/**
 * 상품을 판매하는 Shop 도메인
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {
    @Id
    @GeneratedValue
    @Column(name="shop_id")
    private Long id;

    @Column(name="name" , unique=true)
    private String name; // 쇼핑몰 이름
    private String picture; // 쇼핑몰 대표 이미지
    private String shopNumber; // 전화번호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // shop 대표 관리자

    @Builder
    public Shop(String name, String picture, String shopNumber, User user) {
        this.name = name;
        this.picture = picture;
        this.shopNumber = shopNumber;
        this.user = user;
    }


    public void updateShop(String name, String picture, String shopNumber, User user) {
        if(this.name != null) this.name = name;
        if(this.picture != null) this.picture = picture;
        if(this.shopNumber != null) this.shopNumber = shopNumber;
        if(this.user != null) this.user = user;
    }

}
