package com.javatechie.repository;

import com.javatechie.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Procedure(name = "updateStockProcedure")
    void updateStock(@Param("product_id") Integer productId,
                     @Param("sold_quantity") Integer soldQuantity);

    @Query(value = "SELECT get_total_price(:productId)", nativeQuery = true)
    Double getTotalPrice(int productId);
}
