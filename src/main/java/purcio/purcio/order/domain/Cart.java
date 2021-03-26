package purcio.purcio.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart")
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
        user.setCart(this);
    }

    public void addProduct(CartProduct cartProduct){
        cartProducts.add(cartProduct);
        cartProduct.setCart(this);
        update();
    }

    public void removeProduct(Long cartProductId){
        // TODO: 설렉트 쿼리가 너무 많이 나감. 성능 개선 필요.
        cartProducts.removeIf(c -> c.getId() == cartProductId);
        update();
    }

    public static Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);

        return cart;
    }
}
