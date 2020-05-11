use bookstore;
-- insert into publisher values('mohamed','30 x street ','0100212313');
--  insert into book values(1,'haha','2015-01-01' , 54 ,'science','mohamed',9,1);

-- update book set book.stock = 5 where book.isbn = 1;
-- delete from orders;
-- select * from (book inner join publisher on book.publisher_name = publisher.name) ;
delete FROM sales WHERE sales.timestamp < (now()- INTERVAL 3 MONTH) ;

