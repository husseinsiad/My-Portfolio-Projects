drop database if exists guesGameDb;
create database guesGameDb;
use guesGameDb;

create table Game (
id integer primary key auto_increment,
answer varchar(20) not null,
status boolean default false
);

create table Round (
id integer primary key auto_increment,
`time` datetime not null,
gues varchar(20) not null,
result varchar(20) not null,
gameid integer not null,
foreign key (gameid) references Game(id)
);
