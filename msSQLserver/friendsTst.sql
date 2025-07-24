CREATE TABLE friendsTst (
    ID INT PRIMARY KEY,
    fullName VARCHAR(100),
    emailAddr VARCHAR(100),
    phoneNumber BIGINT
);

INSERT INTO friendsTst (ID, fullName, emailAddr, phoneNumber)
VALUES 
(7, 'John Doe', 'john@example.com', 9876543210),
(8, 'Alice Smith', 'alice@example.com', 9123456789),
(9, 'Bob Johnson', 'bob@example.com', 9988776655);

select * from friendsTst;
delete from friendsTst where ID in (7,8,9);