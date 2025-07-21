create database myfriends_db;
use myfriends_db;

create table friends(
id int primary key,
friendname varchar(50),
hobbies text
);

--insert friend
insert into friends(id,friendname,hobbies)
values (1,'baahubali','sword fight');

insert into friends(id,friendname,hobbies)
values (2,'devasena','archery'),(4,'bichhal dev','gadha yuddha');

--updated by id
update friends
SET hobbies = 'Photography, Chess'
WHERE id = 3;

--search
Select * from friends where id>1 and friendname like 'b%' and hobbies like '_a%';
select * from friends;

--delete
DELETE FROM friends
WHERE id = 2;

drop table friends;
