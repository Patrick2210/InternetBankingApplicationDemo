insert into user_profile(id, first_name, last_name, birth_date, number_pesel, email, phone_number, password)
values (101, 'Patryk', 'Szaruga',CURRENT_DATE,'92102245612','szarug@gmail.com', '564564564','Asd123');

insert into user_profile(id, first_name, last_name, birth_date, number_pesel, email, phone_number, password)
values (102, 'Sebix', 'Klimamaster',CURRENT_DATE,'92120187412', 'sebix@gmail.com', '852852852','Qwe!123');

insert into user_profile(id, first_name, last_name, birth_date, number_pesel, email, phone_number, password)
values (103, 'Jessie', 'Godzi',CURRENT_DATE,'92030332564', 'jessie@gmail.com', '753357753','Zxc"321');

INSERT INTO user_details (id,address, address_home_number, address_flat_number, correspondence_address,
correspondence_address_home_number, correspondence_address_flat_number, post_code, city,user_id)
VALUES (101,'Ulica Przykładowa', '123', 'A', 'Ulica Przykładowa', '456', 'B', '00-000', 'Warszawa',101);

INSERT INTO user_details (id,address, address_home_number, address_flat_number, correspondence_address,
correspondence_address_home_number, correspondence_address_flat_number, post_code, city,user_id)
VALUES (102,'Inna Ulica', '456', 'C', 'Inna Ulica', '789', 'D', '01-234', 'Kraków',102);

INSERT INTO user_details (id,address, address_home_number, address_flat_number, correspondence_address,
correspondence_address_home_number, correspondence_address_flat_number, post_code, city,user_id)
VALUES (105,'Nowa Ulica', '444', '4', 'Nowa Ulica', '555', '5', '04-567', 'Szczecin',103);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (69,'Savings', 123456, 1000.00, 101);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (70,'Main', 654321, 10000.00, 102);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (71,'Mortgage', 123215, 485231.50, 103);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (73,'Main', 852654, 4896.49, 101);