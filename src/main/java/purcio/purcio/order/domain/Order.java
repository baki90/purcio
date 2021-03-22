package purcio.purcio.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.user.domain.Address;
import purcio.purcio.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 구매자

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>(); // 구매한 상품

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; // 배송지

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    @Builder
    public Order(User user, List<OrderProduct> orderProducts, OrderStatus orderStatus, Delivery delivery) {
        this.user = user;
        this.orderProducts = orderProducts;
        this.orderStatus = orderStatus;
        this.delivery = delivery;
    }

    public static Order createOrder(User user, Delivery delivery, OrderProduct... orderProducts){
        Order order = new Order();
        order.setUser(user);
        order.setDelivery(delivery);

        for(OrderProduct orderProduct : orderProducts){
            order.addProduct(orderProduct);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.create();
        return order;
    }

}