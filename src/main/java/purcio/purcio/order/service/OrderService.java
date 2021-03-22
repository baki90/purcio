package purcio.purcio.order.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.order.domain.Delivery;
import purcio.purcio.order.domain.Order;
import purcio.purcio.order.domain.OrderProduct;
import purcio.purcio.order.dto.OrderCreateReqDTO;
import purcio.purcio.order.repository.OrderRepository;
import purcio.purcio.product.domain.Product;
import purcio.purcio.product.repository.ProductRepository;
import purcio.purcio.user.domain.Address;
import purcio.purcio.user.domain.User;
import purcio.purcio.user.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    /** Repository */
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /** 상품 1개 주문 */
    @Transactional
    public Long createOrder(Long userId, Long productId, OrderCreateReqDTO reqDto) {

        // 유저와 상품 정보 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("해당하는 상품을 찾을 수 없습니다."));

        // 배송지 생성
        Delivery delivery = Delivery.createDelivery(reqDto.address);

        // 주문 생성
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product,product.getPrice(),reqDto.count);
        Order order = Order.createOrder(user, delivery, orderProduct);

        // 저장
        orderRepository.save(order);

        return order.getId();
    }

    /** 주문 내역 취소 */
    @Transactional
    public Long cancelOrder(Long orderId){

        // 주문 내역 조회
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NoSuchElementException("해당하는 주문 내역을 찾을 수 없습니다.")
        );

        // 주문 취소
        order.cancelOrder();

        return orderId;
    }

}
