~~~sql
create database multi_test1;
create database multi_test2;

use multi_test1;
CREATE TABLE Student(
Sno char(7) PRIMARY KEY,
Sname char(10) NOT NULL,
Ssex char(2),
Sage tinyint,
Sdept char(20)
);

insert into student(Sno,Sname,Ssex,Sage,Sdept)values ('1','张三','1',18,'2021122201');
use multi_test2;
CREATE TABLE Student(
Sno char(7) PRIMARY KEY,
Sname char(10) NOT NULL,
Ssex char(2),
Sage tinyint,
Sdept char(20)
);
insert into student(Sno,Sname,Ssex,Sage,Sdept)values ('1','李四','1',20,'2021122202');

~~~

