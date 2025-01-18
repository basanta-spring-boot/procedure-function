package com.javatechie.service;

import com.javatechie.entity.Product;
import com.javatechie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public void updateProductStock(Integer productId, Integer soldQuantity) {
        productRepository.updateStock(productId, soldQuantity);
    }

    public Double getTotalOrderPrice(int orderId) {
        return productRepository.getTotalPrice(orderId);
    }

    public Product checkProduct(int productId) {
        // Retrieve the product and read its stock
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    }


}
