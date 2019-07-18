drop database if exists blogdb;
create database blogdb;
use blogdb;

 create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null);

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `user_role`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `user`(`id`),
foreign key (`role_id`) references `role`(`id`));


create table post(
postid int auto_increment primary key,
title varchar(50) not null,
content text not null,
postDate datetime not null,
`status` boolean not null default false,
 userid int not null,
 foreign key (userid)references `user`(id)
);

create table category(
categoryid int auto_increment primary key,
`name` varchar(50) not null
);

create table post_category(
 postid int not null,
 categoryid int not null,
 foreign key (postid)references post(postid),
 foreign key (categoryid)references category(categoryid)
);


insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "password", true),
        (2,"user","password",true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_USER");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1),(1,2),(2,2);



