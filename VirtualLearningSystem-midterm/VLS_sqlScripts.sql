create database VLSdb;

use VLSdb;

INSERT INTO Login (UserName,Password)
VALUES 
('harsh','harsh@123'),
('admin','admin@123'),
('guest','guest@123');

INSERT INTO Course (CourseName, AuthorName, DurationHours, Availability)
VALUES
('Java Basics', 'John Doe', 15, 1),
('Spring Boot Mastery', 'Jane Smith', 20, 1),
('React Frontend', 'David Johnson', 10, 1),
('SQL Fundamentals', 'Emily Clark', 12, 0);

select * from Login;
select * from Course;

drop table Login;
drop table Course;
drop table Cart;