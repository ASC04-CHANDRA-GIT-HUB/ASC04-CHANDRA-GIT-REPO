use myfriends_db;

CREATE TABLE bookings (
    bookingId INT PRIMARY KEY,
    source VARCHAR(100),
    destination VARCHAR(100),
    ticketClass VARCHAR(50),
    numPassengers INT,
    isRoundTrip BIT
);


INSERT INTO bookings (bookingId, source, destination, ticketClass, numPassengers, isRoundTrip)
VALUES 
(1, 'New York', 'Los Angeles', 'Economy', 2, 1),
(2, 'Chicago', 'Miami', 'Business', 1, 0),
(3, 'San Francisco', 'Seattle', 'First Class', 3, 1);       

select * from bookings;


