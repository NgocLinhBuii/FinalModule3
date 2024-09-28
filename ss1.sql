-- Tao DB quan li hoc vien
create database code_gym_student;
use code_gym_student;

create table student(
	`name` varchar(50),
    dob date,
    gender bit,
    salary int
);

insert into student (name, dob, gender, salary)
values ("Bui Ngoc Linh", "2000-10-10", 1, 1000),
("Nguyen Kim Chi", "2000-10-10", 1, 1000),
("Bui van a", "2000-10-10", 1, 1000);

select * from student;

delete from student 
where name = "Bui van a";

update student 
set name = "Bui Huu Ngoc Linh"
where name = "Bui Ngoc Linh";
-- su dung bo qua che do an toan sql, k khuyen khich dung
set sql_safe_updates = 1;