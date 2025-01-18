package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@NamedStoredProcedureQuery(
//        name = "updateStockProcedure",
//        procedureName = "update_stock",
//        parameters = {
//                @StoredProcedureParameter(mode = ParameterMode.IN, name = "product_id", type = Integer.class),
//                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sold_quantity", type = Integer.class)
//        }
//)

@NamedStoredProcedureQueries(
        {
                @NamedStoredProcedureQuery(
                        name = "updateStockProcedure",
                        procedureName = "update_stock",
                        parameters = {
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = Integer.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "quantity", type = Integer.class)
                        }
                ),
                @NamedStoredProcedureQuery(
                        name = "processOrder",
                        procedureName = "process_order",
                        parameters = {
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = Integer.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "quantity", type = Integer.class)
                        }
                )
        }
)

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private int stockQuantity;

}
