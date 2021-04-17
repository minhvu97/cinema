
drop table if exists movietheater.seat;
create table movietheater.seat
(
	id int not null ,
    taken boolean default false,
    theaterid int not null,
    constraint fk_theaterid foreign key(theaterid) references movietheater.theater(id),
    primary key(id,theaterid)
);

insert into movietheater.seat(id,theaterid)
values(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),
(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),
(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),
(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),
(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),
(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),
(31,2),(32,2),(33,2),(34,2),(35,2),(36,2),(37,2),(38,2),
(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(47,2),(48,2);