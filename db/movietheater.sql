CREATE SCHEMA if not exists `movietheater` DEFAULT CHARACTER SET utf8mb4 ;

drop table if exists `movietheater`.`theater`;
create table `movietheater`.`theater`
(
	ID int primary key auto_increment,
	cumrap varchar(50),
	thanhpho varchar(50),
	phim varchar(50),
    suatchieu varchar(50)
);

insert into `movietheater`.`theater`(cumrap,thanhpho,phim,suatchieu)
values
("BHD","Hà Nội","Bố già","22h"),
("MegaStar","Hà Nội","King Kong","3h"),
("MegaStar","TpHCM","Gọdzilla","5h"),
("Lotte","Hải Phòng","Avengers","4h"),
("CGV","TpHCM","Kiều","4h"),
("Galaxy","Vũng Tàu","Trạng Tí","2h"),
("MegaStar","Hà Nội","Tom and Jerry","14h");