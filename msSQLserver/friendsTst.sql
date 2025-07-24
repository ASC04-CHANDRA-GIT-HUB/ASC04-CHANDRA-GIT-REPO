CREATE TABLE friendsTst (
    ID INT PRIMARY KEY,
    fullName VARCHAR(100),
    emailAddr VARCHAR(100),
    phoneNumber BIGINT
);

INSERT INTO friendsTst (ID, fullName, emailAddr, phoneNumber)
VALUES 
(1, 'John Doe', 'john@example.com', 9876543210),
(2, 'Alice Smith', 'alice@example.com', 9123456789),
(3, 'Bob Johnson', 'bob@example.com', 9988776655);
