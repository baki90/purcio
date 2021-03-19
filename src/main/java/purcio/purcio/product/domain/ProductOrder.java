package purcio.purcio.product.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.product.domain.Product;
import purcio.purcio.user.domain.Address;
import purcio.purcio.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ProductOrder {

    @Id
    @GeneratedValue
    @Column(name="product_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 구매자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 구매한 상품

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime purchaseTime; // 구매한 시간
    private Address address; // 배송지

    @Builder
    public ProductOrder(User user, Product product, LocalDateTime purchaseTime, Address address) {
        this.user = user;
        this.product = product;
        this.purchaseTime = purchaseTime;
        this.address = address;
    }
}
