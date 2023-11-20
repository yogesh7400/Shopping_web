package com.abm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.Address;
import com.abm.entity.Order;
import com.abm.entity.User;
import com.abm.exception.OrderException;
import com.abm.service.OrderService;
import com.abm.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    private UserService userService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @RequestBody Address shippingAddress) {
        try {
        	 User user = userService.findUserById(userId);

             if (user == null) {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }

            Order createdOrder = orderService.createOrder(user, shippingAddress);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/place-order/{orderId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long orderId) {
        try {
            Order placedOrder = orderService.placedOrder(orderId);
            return ResponseEntity.ok(placedOrder);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/confirm-order/{orderId}")
    public ResponseEntity<Order> confirmOrder(@PathVariable Long orderId) {
        try {
            Order confirmedOrder = orderService.confirmedOrder(orderId);
            return ResponseEntity.ok(confirmedOrder);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add similar mappings for other order status changes (e.g., ship, deliver, cancel)

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        try {
            Order order = orderService.findOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrderHistory(@PathVariable Long userId) {
        List<Order> userOrders = orderService.usersOrderHistory(userId);
        return ResponseEntity.ok(userOrders);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
