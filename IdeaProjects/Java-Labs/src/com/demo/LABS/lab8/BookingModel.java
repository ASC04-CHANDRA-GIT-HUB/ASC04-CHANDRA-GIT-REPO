package com.demo.LABS.lab8;

public class BookingModel {
    private int bookingId;
    private String source;
    private String destination;
    private String ticketClass;
    private int noOfPassengers;
    private boolean roundTrip;

    public BookingModel(int bookingId, String source, String destination, String ticketClass, int noOfPassengers, boolean roundTrip) {
        this.bookingId = bookingId;
        this.source = source;
        this.destination = destination;
        this.ticketClass = ticketClass;
        this.noOfPassengers = noOfPassengers;
        this.roundTrip = roundTrip;
    }

    // Getters and setters

    public int getBookingId() {
        return bookingId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public boolean isRoundTrip() {
        return roundTrip;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s -> %s | Class: %s | Passengers: %d | RoundTrip: %s",
                bookingId, source, destination, ticketClass, noOfPassengers, roundTrip ? "Yes" : "No");
    }
}
