CREATE SCHEMA if not exists `staff` DEFAULT CHARACTER SET utf8mb4 ;

drop table if exists staff.staff;
create table staff.staff
(
	id int not null primary key auto_increment,
    firstName varchar(30) not null,
    lastName varchar(30) not null,
    rolee VARCHAR(120) not null,
    email varchar(30) not null,
    pass varchar(30) not null
);
insert into staff (firstName,lastName,rolee,email,pass)
values("Minh","Vu","Manager","3","3"),
("Quynh","Pham","Staff","4","4");

