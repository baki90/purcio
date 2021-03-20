package purcio.purcio.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.product.domain.Product;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_product")
public class OrderProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문

    private int orderPrice; //주문 가격
    private int count; //주문 수량

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void addCount(int count) {
        this.count += count;
    }
    public void removeCount(int count) {
        this.count -= count;
    }

    @Builder
    public OrderProduct(Product product, Order order, int orderPrice, int count) {
        this.product = product;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
