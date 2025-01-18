# procedure-function
![Screenshot 2025-01-18 at 2 40 52 PM](https://github.com/user-attachments/assets/9896c0e6-9385-45c2-8ada-77b302e9125c)

### Stored Procedure Use Case: Complex Business Logic
Imagine a Spring Boot application for order management. You want to process an order by:
```
1. Validating stock levels.
2. Deducting quantities from inventory.
3. Recording the transaction in the database.
```
A stored procedure is ideal here since it can handle multiple database operations in a single call.

update_stock:
--------------
```
CREATE PROCEDURE javatechie.update_stock(
	IN productId INT,
    IN quantity INT
    )
BEGIN
	UPDATE product
    SET stockQuantity = stockQuantity - quantity
    WHERE id = productId;
END
```
get_total_price:
----------------
```
CREATE FUNCTION javatechie.get_total_price(productId INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE total DECIMAL(10,2);
    SELECT SUM(price * stockQuantity) INTO total
    FROM Product
    WHERE id = productId;

    RETURN total;
END
```
process_order:
--------------
```
CREATE PROCEDURE javatechie.process_order(IN productId INT, IN quantity INT)
BEGIN
    DECLARE availableStock INT;

    -- Get available stock and price for the product
    SELECT stockQuantity INTO availableStock
    FROM Product
    WHERE id = productId;

    -- Check if stock is sufficient
    IF availableStock >= quantity THEN
    
        -- Deduct stock quantity
        UPDATE Product
        SET stockQuantity = stockQuantity - quantity
        WHERE id = productId;

    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient stock';
    END IF;
END
```

