use bookStore;
-- insert into sales values( '2020-01-01 00:00:01', 5,'mohamed99', 1);
-- insert into sales values( '2020-01-01 00:00:05', 5,'mohamed99', 1);
-- insert into sales values( '2020-01-01 00:00:09', 5,'mohamed99', 1);
-- insert into book values(2,'zzzz','2020-01-01',65,"science" ,"mohamed",15,99);
-- insert into sales values( '2020-01-01 00:00:09', 5,'mohamed99', 2);
-- insert into sales values( '2019-11-01 00:00:09', 5,'mohamed99', 2);
-- insert into users values ('mohamed134','mohamed','mohamed' ,'mohamed123@gmail.com','12345','30 betash street','010002153463',0);
-- insert into users values ('mohamed124','mohamed','mohamed' ,'mohamed103@gmail.com','12345','30 betash street','010002153464',0);
-- insert into users values ('mohamed154','mohamed','mohamed' ,'mohamed163@gmail.com','12345','30 betash street','010002153466',0);
-- insert into users values ('mohamed164','mohamed','mohamed' ,'mohamed173@gmail.com','12345','30 betash street','010002153467',0);
-- insert into users values ('mohamed174','mohamed','mohamed' ,'mohamed193@gmail.com','12345','30 betash street','010002153468',0);
-- insert into users values ('mohamed184','mohamed','mohamed' ,'mohamed183@gmail.com','12345','30 betash street','010002153469',0);

-- insert into sales values ('2019-11-01 00:00:09', 5,'mohamed184', 2);
-- insert into sales values ('2019-11-01 00:00:09', 5,'mohamed154', 2);
-- insert into sales values ('2019-11-01 00:00:09', 5,'mohamed174', 2);
-- insert into sales values ('2019-11-01 00:00:09', 5,'mohamed164', 2);
-- insert into sales values ('2019-11-01 00:00:09', 5,'mohamed134', 2);
--  insert into sales values ('2019-11-01 00:00:00', 5,'mohamed164', 2);
--  insert into sales values ('2019-11-01 00:00:01', 5,'mohamed134', 2);
-- insert into sales values ('2019-11-01 00:00:01', 5,'mohamed124', 2);

select * from users;

 select sum(no_of_copies) as amount_sold
 ,Book_ISBN,title,pyear,price,category,publisher_name,stock_min,stock
 from (sales inner join book on Book_ISBN = isbn)
 group by Book_ISBN order by  sum(no_of_copies) desc  limit 10;