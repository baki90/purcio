package purcio.purcio.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.order.dto.cart.CartAddProductReqDTO;
import purcio.purcio.order.service.CartService;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @PostMapping()
    public ResponseDTO<Object> addProductToCart(Long userId, @RequestBody CartAddProductReqDTO reqDTO){
        return cartService.addProductToCart(userId, reqDTO);
    }


    @DeleteMapping()
    public ResponseDTO<Object> removeProductInCart(Long userId, Long cartProductId){
        return cartService.removeProductInCart(userId, cartProductId);
    }


    @GetMapping("userId={id}")
    public ResponseDTO<Object> retrieveCartByUserId(@PathVariable("id") Long userId) {
        return cartService.retrieveCartByUserId(userId);
    }

}
