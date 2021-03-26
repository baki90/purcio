package purcio.purcio.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.order.domain.Delivery;
import purcio.purcio.order.domain.Order;
import purcio.purcio.order.domain.OrderProduct;
import purcio.purcio.order.dto.order.OrderCreateReqDTO;
import purcio.purcio.order.repository.OrderRepository;
import purcio.purcio.product.domain.Product;
import purcio.purcio.product.repository.ProductRepository;
import purcio.purcio.user.domain.User;
import purcio.purcio.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    /** Repository */
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * N개의 상품을 주문합니다.
     * @param userId
     * @param reqDto 배송 정보 & 상품 주문 정보 포함
     * @return 주문 아이디
     */
    @Transactional
    public ResponseDTO<Object> createOrder(Long userId, OrderCreateReqDTO reqDto) {

        // 1. 유저 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자를 찾을 수 없습니다."));

        // 2. 맵을 바탕으로 상품 조회
        // TODO: 상품 조회 후 예외 처리 필요
        Map<Long, Integer> orderProductMap = reqDto.getOrderProductDTOs();
        List<Product> products = productRepository.findAllById(
                orderProductMap.keySet());

        // 3. 배송지 생성
        Delivery delivery = Delivery.createDelivery(reqDto.getAddress());
        List<OrderProduct> orderProducts = new ArrayList<>();

        // 4. 각 상품별로 주문 리스트를 생성하기
        products.stream().forEach(p->{
            orderProducts.add(OrderProduct.createOrderProduct(p, p.getPrice()
                    ,orderProductMap.get(p.getId())));
        });

        // 5. 주문 생성
        Order order = Order.createOrder(user, delivery, orderProducts);

        // 6. 저장
        orderRepository.save(order);

        return ResponseDTO.builder()
                .message("주문이 완료되었습니다.")
                .content(order.getId())
                .build();

    }

    /**
     * 주문 내역을 취소합니다.
     * @param orderId
     * @return 주문 아이디
     */
    @Transactional
    public ResponseDTO<Object> cancelOrder(Long orderId){

        // 주문 내역 조회
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NoSuchElementException("해당하는 주문 내역을 찾을 수 없습니다.")
        );

        // 주문 취소
        order.cancelOrder();

        return ResponseDTO.builder()
                .message("주문이 취소되었습니다.")
                .content(order.getId())
                .build();
    }

}
