package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderMapper.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderMapper.findById(id);
    }

    @PostMapping
    public int createOrder(@RequestBody Order order) {
        return orderMapper.insert(order);
    }

    @PutMapping("/{id}")
    public int updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        order.setId(id);
        return orderMapper.update(order);
    }

    @DeleteMapping("/{id}")
    public int deleteOrder(@PathVariable Integer id) {
        return orderMapper.delete(id);
    }
}
