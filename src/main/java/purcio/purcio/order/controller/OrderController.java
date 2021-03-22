package purcio.purcio.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import purcio.purcio.order.dto.OrderCreateReqDTO;
import purcio.purcio.order.service.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public Long createOrder(Long userId, Long productId, @RequestBody @Valid OrderCreateReqDTO orderCreateReqDTO){
        return orderService.createOrder(userId,productId,orderCreateReqDTO);
    }

    @PutMapping("/{id}")
    public Long cancelOrder(@PathVariable("id") Long userId){
        return orderService.cancelOrder(userId);
    }
}
