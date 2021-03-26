package purcio.purcio.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.order.dto.order.OrderCreateReqDTO;
import purcio.purcio.order.service.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseDTO<Object> createOrder(Long userId, @RequestBody @Valid OrderCreateReqDTO orderCreateReqDTO){
        return orderService.createOrder(userId,orderCreateReqDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO<Object> cancelOrder(@PathVariable("id") Long userId){
        return orderService.cancelOrder(userId);
    }
}
