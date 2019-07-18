drop database if exists superherodbTest;
create database superherodbTest;
use superherodbTest;
create table superhero(
id int auto_increment primary key,
`name` varchar(30),
`desc` varchar(30),
superpower varchar(30)
);

create table `organization`(
id int auto_increment primary key,
`name` varchar(30),
`desc` varchar(30),
address varchar(30),
contact  varchar(30)
);

create table super_org(
superheroid int,
organizationid int,
 foreign key (superheroid) references superhero(id),
 foreign key (organizationid) references `organization`(id)
);
create table location(
id integer PRIMARY KEY auto_increment,
`name` varchar(30),
`desc` varchar(30),
 address varchar(30)
);
create table sighting(
id int auto_increment primary key,
`date`  datetime,
locationid int,
foreign key (locationid) references location(id)
);

create table super_sighting(
superheroid int,
sightingid int,
 foreign key (superheroid) references superhero(id),
 foreign key (sightingid) references sighting(id)
);


