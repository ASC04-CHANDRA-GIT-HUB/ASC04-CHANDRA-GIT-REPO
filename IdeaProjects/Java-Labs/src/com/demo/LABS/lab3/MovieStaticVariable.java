package com.demo.LABS.lab3;

public class MovieStaticVariable {
    private String movieName;
    private String producedBy;
    private String directedBy;
    private double duration; // in hours
    private int year;
    private String category;

    private static int moviesCount = 0;

    public MovieStaticVariable(String movieName, String producedBy) {
        this.movieName = movieName;
        this.producedBy = producedBy;
        moviesCount++;
    }

    public MovieStaticVariable(String movieName, String producedBy, String directedBy, double duration, int year, String category) {
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

    public static int getMoviesCount() {
        return moviesCount;
    }

    public static void main(String[] args) {
        MovieStaticVariable m1 = new MovieStaticVariable("Badava Rascal", "Daali Pictures");
        m1.acceptDetails("Shankar Guru", 2.13, 2023, "Rom-Com");


        MovieStaticVariable m2 = new MovieStaticVariable("Salaar - The Cease Fire", "Hombale Films", "Prashant Neel", 2.55, 2023, "Epic neo-noir action thriller");

        MovieStaticVariable m3 = new MovieStaticVariable("Bajirao Mastani", "Eros Entertainment");
        m3.acceptDetails("Sanjay Leela Bhansali", 2.38, 2015, "Historical Epic");

        System.out.println("\n Movies Details:");

        m1.displayDetails();
        System.out.println();
        m2.displayDetails();
        System.out.println();
        m3.displayDetails();
        System.out.println();

        System.out.println("\n Total movies in the library: "+MovieStaticVariable.getMoviesCount());

    }
}
