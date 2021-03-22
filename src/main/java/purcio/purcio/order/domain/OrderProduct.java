package purcio.purcio.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
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

   public void setCount(int count){
        this.count = count;
   }

    public static OrderProduct createOrderProduct(Product product, int orderPrice, int count){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(orderPrice);
        orderProduct.setCount(count);

        product.removeStock(count); // 재고 감소
        return orderProduct;
   }

    // 상품 재고 원복
    public void cancel(){
        getProduct().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


}
