use myfriends_db;

CREATE TABLE EMPLOYEES (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,  -- Auto-generated ID
    first_name VARCHAR(255) NOT NULL,     -- First name of employee
    last_name VARCHAR(255) NULL,          -- Last name of employee
    email_id VARCHAR(255) NULL            -- Email address
);
drop table EMPLOYEES;