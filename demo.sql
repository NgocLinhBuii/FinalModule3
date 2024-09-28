create database demo;
use demo;

create table jame_account(
	user_name varchar(50) primary key,
    password varchar(50)
);

create table class(
	class_id int primary key auto_increment,
    class_name varchar(50)
);

create table student(
	student_id int primary key auto_increment,
    student_name varchar(50),
    student_birthday date,
    student_gender bit,
    student_email varchar(50),
    student_point double,
    user_name varchar(50),
    class_id int,
    foreign key(user_name) references jame_account(user_name),
    foreign key(class_id) references class(class_id)
);

create table instructor(
	instructor_id int primary key auto_increment,
	instructor_name varchar(50),
    instructor_birthday date,
	instructor_salary double
);

create table instructor_class(
	class_id int,
    instructor_id int,
    primary key(class_id,instructor_id),
    foreign key(class_id) references class(class_id),
    foreign key(instructor_id) references instructor(instructor_id)
);

insert into class (class_name) values ('c1121g1'), ('c1221g1'),('a0821i1'),('a0921i1');

insert into jame_account(user_name,password)
 values('cunn','12345'),('chunglh','12345'),('hoanhh','12345'),('dungd','12345'),('huynhtd','12345'),
 ('hainm','12345'),('namtv','12345'),('hieuvm','12345'),('kynx','12345'),('vulm','12345'),('anv','12345'),('bnv','12345');


insert into instructor(instructor_name,instructor_birthday, instructor_salary)
 values('tran van chanh','1985-02-03',100),('dang chi trung','1985-02-03',200),('nguyen vu thanh tien','1985-02-03',300);

 
 insert into student(student_name,student_birthday, student_gender,student_point, class_id,user_name) 
 values ('nguyen ngoc cu','1981-12-12',1,8,1,'cunn'),('le hai chung','1981-12-12',1,5,1,'chunglh'),
 ('hoang huu hoan','1990-12-12',1,6,2,'hoanhh'),('dau dung','1987-12-12',1,8,1,'dungd'),
 ('ta dinh huynh','1981-12-12',1,7,2,'huynhtd'),('nguyen minh hai','1987-12-12',1,9,1,'hainm'),
 ('tran van nam','1989-12-12',1,4,2,'namtv'),('vo minh hieu','1981-12-12',1,3,1,'hieuvm'),
 ('le xuan ky','1981-12-12',1,7,2,'kynx'),('le minh vu','1981-12-12',1,7,1,'vulm'),
 ('nguyen van a','1981-12-12',1,8,null,'anv'),('tran van b','1981-12-12',1,5,null,'bnv');

 insert into instructor_class(class_id,instructor_id) values (1,1),(1,2),(2,1),(2,2),(3,1),(3,2);
 
 -- 1. Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào (sử dụng join).
 -- Cần bảng học viên, bảng lớp
 -- sử dụng inner join
 select *
 from student
 inner join class on student.class_id = class.class_id;
 -- 2. Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào và cả các bạn đã đăng ký nhưng chưa có lớp học.
 select *
 from student
 left join class on student.class_id = class.class_id;
 -- 3. Lấy thông tin của các học viên tên “Hai” và 'Huynh’
 
 
 -- 4. Lấy ra học viên có điểm lớn hơn 5 .
 -- 5. Lấy ra học viên có họ là “nguyen”
 -- 6. Thông kế số lượng học sinh theo từng loại điểm.
 -- 7. Thông kế số lượng học sinh theo điểm và điểm phải lớn hơn 5
 -- 8. Thông kế số lượng học sinh theo điểm lớn hơn 5 và chỉ hiện thị với số lượng >= 2
 -- 9. Lấy ra danh sách học viên của lớp c1121g1 và sắp xếp tên học viên theo alphabet.
 
 -- Lấy thông tin lớp học và cho biết giảng viên nào giảng dạy
 -- Bảng cần dùng: lớp, giảng viên, lớp_giảngviên
 
 select *
 from class c
 join instructor_class ic on c.class_id = ic.class_id
 join instructor i on i.instructor_id = ic.instructor_id;
 