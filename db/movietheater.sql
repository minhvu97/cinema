CREATE SCHEMA if not exists `movietheater` DEFAULT CHARACTER SET utf8mb4 ;

drop table if exists `theater`;
create table `movietheater`.`theater`
(
	ID int primary key auto_increment,
    cumrap varchar(50),
    thanhpho varchar(50),
    rate int
);

insert into `theater`(cumrap,thanhpho,rate)
values
("BHD","Hà Nội",3),
("MegaStar","Hà Nội",3),
("MegaStar","TpHCM",5),
("Lotte","Hải Phòng",4),
("CGV","TpHCM",4),
("Galaxy","Vũng Tàu",2),
("MegaStar","Hà Nội",5);