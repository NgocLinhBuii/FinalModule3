create database student_management;

use student_management;

create table Class(
	id varchar(50),
    name varchar(50)
);

create table Teacher(
	id varchar(50),
    name varchar(50),
    age int,
    country varchar(50)
);

select * from Class;
select * from Teacher;