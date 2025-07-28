CREATE TABLE bookings (
    booking_id INT PRIMARY KEY,
    source VARCHAR(100),
    destination VARCHAR(100),
    ticket_class VARCHAR(50),
    no_of_passengers INT,
    round_trip BIT
);
