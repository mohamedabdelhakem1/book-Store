use bookstore;

DELIMITER $$
create trigger negative_amount before update on book 
for each row
	begin
        DECLARE errorMessage VARCHAR(255);
		SET errorMessage = CONCAT('only amount of ',OLD.stock,'  are available from this book ' ,old.title);
		if new.stock < 0 then
        SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = errorMessage;
    END IF;
END $$
DELIMITER ;

DELIMITER $$
create trigger place_orders after update on book
for each row
	begin
		if new.stock < old.stock_min then
			insert into orders values (old.stock_min +10 - new.stock, old.isbn); 
    END IF;
END $$
DELIMITER ;


DELIMITER $$
create trigger confirm_orders before delete on orders
for each row
	begin
		update book set book.stock = book.stock + old.quantity where book.isbn = old.book_isbn;
	END $$
DELIMITER ;

 
