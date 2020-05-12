use bookstore;
-- insert into publisher values('mohamed','30 x street ','0100212313');
--  insert into book values(1,'haha','2015-01-01' , 54 ,'science','mohamed',9,1);

-- update book set book.stock = 5 where book.isbn = 1;
-- delete from orders;

-- select garages.LOCATION , count(garage_id) as COUNT_OF_SLOTS_IN_THE_GARAGE  from (garages left outer join garage_slots on garages.id = garage_slots.garage_id)
-- group by garage_id
-- order by COUNT_OF_SLOTS_IN_THE_GARAGE desc, location asc;
select * from book;