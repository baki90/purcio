package purcio.purcio.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.user.domain.Address;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order; // 배송지

    @Embedded
    private Address address;

    private DeliveryStatus deliveryType;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDeliveryType(DeliveryStatus deliveryType) {
        this.deliveryType = deliveryType;
    }
}
