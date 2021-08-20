CREATE KEYSPACE IF NOT EXISTS
p1 with REPLICATION = {'class': 'SimpleStrategy','replication_factor':'1'};

CREATE TABLE IF NOT EXISTS p1.customerAccount (
customer_id int PRIMARY KEY,
first_name text,
last_name text,
phone_no text,
email text,
address text,
address2 text,
city text,
state text,
zip text);

CREATE TABLE IF NOT EXISTS p1.orderAccount (
order_id int PRIMARY KEY,
customer_id int,
comment text,
sales_associate text,
order_date text,
products text,
total_amt float);

INSERT INTO p1.customerAccount
 (customer_id, first_name, last_name, phone_no, email, address,
 address2, city, state, zip) values
 (0, 'Bobby', 'Bilyeu','415-311-8021', 'bilyeubobby@sfstate.edu',
'812 Noriega St','', 'San Francisco', 'CA', '94122');


INSERT INTO p1.orderAccount
(order_id, customer_id, comment, sales_associate, order_date,
products, total_amt) values
(0, 0, 'ACCOMMODATION NOTES: Park in guest driveway.', 'Christopher Jose',
'8/20/2021', '{Purple Original Mattress: [Twin, New]}', 649 );




