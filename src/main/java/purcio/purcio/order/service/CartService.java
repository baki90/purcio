package purcio.purcio.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.order.domain.Cart;
import purcio.purcio.order.domain.CartProduct;
import purcio.purcio.order.dto.cart.CartAddProductReqDTO;
import purcio.purcio.order.dto.cart.CartRetrieveByUserIdResDTO;
import purcio.purcio.order.repository.CartRepository;
import purcio.purcio.product.domain.Product;
import purcio.purcio.product.repository.ProductRepository;
import purcio.purcio.user.domain.User;
import purcio.purcio.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    /**
     * 상품을 장바구니에 추가한다.
     * @param userId
     * @return 장바구니 아이디
     */
    @Transactional
    public ResponseDTO<Object> addProductToCart(Long userId, CartAddProductReqDTO reqDTO) {

        // 1. 유저 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자를 찾을 수 없습니다."));

        // 2. 상품 조회
        Product product = productRepository.findById(reqDTO.getProductId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 상품을 찾을 수 없습니다.")
        );

        // TODO: 상품의 재고에 따라서 주문 가능 여부 확인 필요

        // 3. CartProduct 생성
        CartProduct cartProduct = CartProduct.createCartProduct(product, reqDTO.getCount());

        // 4. 유저의 장바구니 조회 후 상품 추가
        Cart cart = user.getCart();
        cart.addProduct(cartProduct);

        // 5. 저장
        cartRepository.save(cart);

        return ResponseDTO.builder()
                .message("상품이 장바구니에 추가되었습니다.")
                .content(cart.getId())
                .build();
    }


    /** 상품을 장바구니에서 제거한다.
     * @return null
     */
    @Transactional
    public ResponseDTO<Object> removeProductInCart(Long userId, Long cartProductId){
        // 1. 유저 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자를 찾을 수 없습니다."));

        // 2. 유저의 카트 조회 후, 삭제
        Cart cart = user.getCart();
        cart.removeProduct(cartProductId);

        return ResponseDTO.builder()
                .message("상품이 장바구니에서 제거되었습니다.")
                .build();
    }

    /**
     * 유저의 아이디를 바탕으로 장바구니를 조회한다.
     * @param userId
     * @return
     */

    public ResponseDTO<Object> retrieveCartByUserId(Long userId){

        // 1. 유저 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자를 찾을 수 없습니다."));

        Cart cart = user.getCart();
        List<CartRetrieveByUserIdResDTO> retrieveDTOList = new ArrayList<>();

        cart.getCartProducts().stream().forEach(o -> {
            Product product = o.getProduct();
            retrieveDTOList.add(CartRetrieveByUserIdResDTO.builder()
                    .count(o.getCount())
                    .name(product.getName())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .build());
        });

        return ResponseDTO.builder()
                .message("조회가 완료되었습니다.")
                .content(retrieveDTOList)
                .build();
    }


}
