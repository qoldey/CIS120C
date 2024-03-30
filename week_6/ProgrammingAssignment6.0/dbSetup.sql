
create database if not exists southwind;
use southwind;

drop table if exists products;

create table products (
	productID int,
	productCode char(3),
	name varchar(30),
	quantity int,
	price decimal(10,2));

delete from products where productID >= 1000; --Reset table for reruns

insert into products values (1001, 'PEN', 'Pen Red', 5000, 1.23);
insert into products values (1002, 'PEN', 'Pen Blue', 8000, 1.25);
insert into products values (1003, 'PEN', 'Pen Black', 2000, 1.25);
insert into products values (1004, 'PEC', 'Pencil 2B', 10000, 0.48);
insert into products values (1005, 'PEC', 'Pencil 2H', 8000, 0.49);

select * from products; --Display the every field of every record in the table
select name, quantity, price from products where productCode = 'PEN'; --Display only those rows that have productCode equal to 'PEN'.  Select only the name, quantity and price
select productID, productCode, name, quantity from products where quantity >= 7000; --Display the productId, productCode, name and quantity of all records with inventory greater than or equal to 7000.

