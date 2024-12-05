package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CreateHdn;
import org.example.test.dto.CreateOrder;
import org.example.test.dto.UpdateOrder;
import org.example.test.service.OrderService;
import org.example.test.util.OrderStatus;
import org.example.test.util.Uri;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    final OrderService orderService;

    @PostMapping(value = Uri.order)
    public ResponseEntity CreatOrder(@RequestParam Integer userId, @RequestBody CreateOrder rq) {
        orderService.createOrder(userId, rq);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @GetMapping(value = Uri.order)
    public ResponseEntity GetOrder(@RequestParam Integer userId, @RequestParam OrderStatus status) {
        UpdateOrder rs = orderService.getOrder(userId,status);
        return new ResponseEntity(rs, HttpStatusCode.valueOf(HttpStatus.OK.value()));


    }
    @PutMapping(value = Uri.order)
    public ResponseEntity UpdateOrder(@RequestParam Integer userId, @RequestBody UpdateOrder rq) {
         orderService.updateOrder(userId,rq);
        return new ResponseEntity( HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @PatchMapping(value = Uri.order)
    public ResponseEntity UpdateOrderStatus(@RequestParam Integer userId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(userId,status);
        return new ResponseEntity( HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @DeleteMapping(value = Uri.orderItem)
    public ResponseEntity Delete(@RequestParam Integer orderId ) {
        orderService.deleteOrderItem(Long.valueOf(orderId));
        return new ResponseEntity( HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

}
