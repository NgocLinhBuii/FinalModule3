create database c0324m4;
use c0324m4;


create table james(
	account varchar(50) primary key,
    password varchar(50)
);

create table class(
	id int primary key auto_increment,
    name varchar(50)
);

create table student(
	id int primary key auto_increment,
    name varchar(50),
    gender bit,
    email varchar(50),
    birthday date,
    point double,
    account varchar(50),
    class_id int,
    foreign key(account) references james(account),
    foreign key(class_id) references class(id)
);

create table intructor (
	id int primary key auto_increment,
    name varchar(50),
	birthday date,
	salary int
);

create table class_intrustor(
	intructor_id int,
    class_id int,
    primary key(intructor_id,class_id),
    foreign key (class_id) references class(id),
	foreign key (intructor_id) references intructor(id)
);
