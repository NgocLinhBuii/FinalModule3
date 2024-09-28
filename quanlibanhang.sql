create database quanlibanhang;
use quanlibanhang;

create table customer(
	cID int auto_increment primary key,
    cName varchar(50),
    cAge date
);

create table Oder(
	oID int auto_increment primary key,
    cID int,
    oDate date,
    ototalprice int,
    foreign key(cID) references customer(cID)
);

create table product(
	pID int auto_increment primary key,
    pname varchar (50),
    pprice int
);

create table oderdetail(
	oID int,
    pID int,
    odQTY varchar(50),
    primary key (oID,pID),
    foreign key (oID) references Oder(oID),
    foreign key (pID) references product(pID)
);