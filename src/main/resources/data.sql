insert into user_profile(id, email, phone_number, password)
values (101, 'szarug@gmail.com', '564564564','Asd123');

insert into user_profile(id, email, phone_number, password)
values (102, 'sebix@gmail.com', '852852852','Qwe!123');

insert into user_profile(id, email, phone_number, password)
values (103, 'jessie@gmail.com', '753357753','Zxc"321');

insert into user_details(id, first_name, last_name, birth_date, number_pesel,user_id)
values (90, 'Patryk', 'Szaruga',CURRENT_DATE,'92102245612',101);

insert into user_details(id, first_name, last_name, birth_date, number_pesel,user_id)
values (91, 'Sebix', 'Klimamaster',CURRENT_DATE,'92120187412',102);

insert into user_details(id, first_name, last_name, birth_date, number_pesel,user_id)
values (92, 'Jessie', 'Godzi',CURRENT_DATE,'92030332564',103);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (69,'Savings', 123456, 1000.00, 101);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (70,'Main', 654321, 10000.00, 102);

insert into user_account(id, account_type, reference_account_number,balance,user_id)
values (71,'Mortgage', 123215, 485231.50, 103);