CREATE TABLE bookings (
    bookingId INT PRIMARY KEY,
    source VARCHAR(100),
    destination VARCHAR(100),
    ticketClass VARCHAR(50),
    numPassengers INT,
    isRoundTrip BIT
);
