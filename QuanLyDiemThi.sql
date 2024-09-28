create database QuanLyDiemThi;
use QuanLyDiemThi;
create table HocSinh(
	MaHS varchar(50) primary key,
    TenHS varchar(50),
    NgaySinh date,
    Lop varchar(50),
    GT varchar(50)
);

create table MonHoc (
	MaMH varchar(50) primary key,
    TenMH varchar(50),
    MaGV varchar(50)
);

CREATE TABLE BangDiem(
 MaHS VARCHAR(20),
 MaMH VARCHAR(20),
 DiemThi INT,
 NgayKT DATETIME,
 PRIMARY KEY (MaHS, MaMH),
 FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS),
 FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH)
);

CREATE TABLE GiaoVien(
 MaGV VARCHAR(20) PRIMARY KEY,
 TenGV VARCHAR(20),
 SDT VARCHAR(10)
);

ALTER TABLE MonHoc ADD CONSTRAINT FK_MaGV FOREIGN KEY (MaGV) REFERENCES GiaoVien(MaGV);

select * from bangdiem;