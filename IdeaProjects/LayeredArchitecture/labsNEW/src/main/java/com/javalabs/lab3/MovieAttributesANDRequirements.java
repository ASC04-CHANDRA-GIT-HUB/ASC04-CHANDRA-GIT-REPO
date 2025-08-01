package com.javalabs.lab3;

public class MovieAttributesANDRequirements {
    private String movieName;
    private String producedBy;
    private String directedBy;
    private double duration; // in hours
    private int year;
    private String category;

    public MovieAttributesANDRequirements(String movieName, String producedBy) {
        this.movieName = movieName;
        this.producedBy = producedBy;
    }

    public MovieAttributesANDRequirements(String movieName, String producedBy, String directedBy, double duration, int year, String category) {
        this(movieName, producedBy);
        this.directedBy = directedBy;
        this.duration = duration;
        this.year = year;
        this.category = category;
    }

    public void acceptDetails(String directedBy, double duration, int year, String category) {
        this.directedBy = directedBy;
        this.duration = duration;
        this.year = year;
        this.category = category;
    }
    public void displayDetails() {
        System.out.println("Movie Name: " + movieName);
        System.out.println("Produced By: " + producedBy);
        System.out.println("Directed By: " + (directedBy != null ? directedBy : "N/A"));
        System.out.println("Duration: " + (duration > 0 ? duration + " hours" : "N/A"));
        System.out.println("Year: " + (year > 0 ? year : "N/A"));
        System.out.println("Category: " + (category != null ? category : "N/A"));
    }

    public static void main(String[] args) {
        MovieAttributesANDRequirements m1 = new MovieAttributesANDRequirements("Badava Rascal", "Daali Dhananjaya");
        m1.acceptDetails("Shankar Guru", 2.13, 2023, "Rom-Com");
        m1.displayDetails();

        System.out.println();

        MovieAttributesANDRequirements m2 = new MovieAttributesANDRequirements("Salaar - The Cease Fire", "Vijay Kiragandur", "Prashant Neel", 2.55, 2023, "Epic neo-noir action thriller");
        m2.displayDetails();

    }
}
