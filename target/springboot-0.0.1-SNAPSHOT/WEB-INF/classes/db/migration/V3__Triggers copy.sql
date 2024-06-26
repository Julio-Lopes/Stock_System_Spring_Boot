-- Version: 1

DELIMITER $

CREATE TRIGGER TRG_I_PRODUTOS AFTER INSERT ON PRODUCTS
FOR EACH ROW
BEGIN
    INSERT INTO stock_product (quantity, product_id) VALUES (0, NEW.id);
END$

DELIMITER $

CREATE TRIGGER TRG_I_NOTA_ENTRADA_ITENS AFTER INSERT
ON ITEMS_INVOICE
FOR EACH ROW
BEGIN
    UPDATE stock_product
    SET quantity = quantity + NEW.quantity
    WHERE product_id = NEW.product_id;
END$

DELIMITER $

CREATE TRIGGER TRG_D_NOTA_ENTRADA_ITENS AFTER DELETE
ON ITEMS_INVOICE
FOR EACH ROW
BEGIN
    UPDATE stock_product
    SET quantity = quantity - OLD.quantity
    WHERE product_id = OLD.product_id;
END$

DELIMITER $

CREATE TRIGGER TRG_U_NOTA_ENTRADA_ITENS AFTER UPDATE
ON ITEMS_INVOICE
FOR EACH ROW
BEGIN
    UPDATE stock_product
    SET quantity = quantity - (OLD.quantity - NEW.quantity)
    WHERE product_id = NEW.product_id;
END$