package purcio.purcio.order.dto.cart;

import lombok.Data;

@Data
public class CartAddProductReqDTO {
    private Long productId;
    private int count;
}
