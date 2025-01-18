package com.javatechie.controller;

import com.javatechie.entity.Product;
import com.javatechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PutMapping("/update/{productId}/{quantity}")
    public Product updateInventory(@PathVariable Integer productId, @PathVariable Integer quantity) {
        service.updateProductStock(productId, quantity);
        return service.checkProduct(productId);
    }

    @GetMapping("/total-price/{productId}")
    public Double getTotalPrice(@PathVariable Integer productId) {
        return service.getTotalOrderPrice(productId);
    }

    // Endpoint to process an order
    @PostMapping("/process/{productId}/{quantity}")
    public String processOrder(@PathVariable("productId") int productId,
                               @PathVariable("quantity") int quantity) {
        try {
            service.processOrder(productId, quantity);
            return "Order processed successfully";
        } catch (Exception e) {
            return "Error processing order: " + e.getMessage();
        }
    }

}
