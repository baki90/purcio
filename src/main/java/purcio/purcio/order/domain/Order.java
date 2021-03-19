package purcio.purcio.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.product.domain.Product;
import purcio.purcio.user.domain.Address;
import purcio.purcio.user.domain.User;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="product_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 구매자

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>(); // 구매한 상품

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime purchaseTime; // 구매한 시간

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Address address; // 배송지

    public void addProduct(Product product){
        products.add(product);
       // product.setOrder(this);
    }
    @Builder
    public Order(User user, List<Product> products, LocalDateTime purchaseTime, OrderType orderType, Address address) {
        this.user = user;
        this.products = products;
        this.purchaseTime = purchaseTime;
        this.orderType = orderType;
        this.address = address;
    }

    public static Order createOrder(User user, Address address, Product... products){
        Order order = new Order();
        order.user = user;
        order.address = address;

        for(Product product : products){
            order.addProduct(product);
        }

        order.orderType = OrderType.ORDER;
        order.purchaseTime = LocalDateTime.now();

        return order;
    }

}
