package ir.saeidbabaei.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ir.saeidbabaei.orderservice.model.Order;
import ir.saeidbabaei.orderservice.service.OrderService;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
    	
        log.debug("Creating a new {}", order);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(order));
        
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
    	
        return ResponseEntity.ok().body(service.findAll());
        
    }
}