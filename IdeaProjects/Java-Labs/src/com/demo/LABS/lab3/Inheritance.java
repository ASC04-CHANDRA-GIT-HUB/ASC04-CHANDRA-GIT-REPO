package com.demo.LABS.lab3;

class Inheritance {
    private String movieName;
    private String producedBy;
    private String directedBy;
    private double duration; // in hours
    private int year;
    private String category;

    private static int moviesCount = 0;
    private final String movieId;

    public Inheritance(String movieName, String producedBy) {
        this.movieName = movieName;
        this.producedBy = producedBy;
        moviesCount++;
        this.movieId = movieName + "_" + moviesCount;
    }

    public Inheritance(String movieName, String producedBy, String directedBy, double duration, int year, String category) {
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
        System.out.println("Movie ID: " + movieId);
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
}

//SpecialMovie
class SpecialMovie extends Inheritance {
    private String soundEffectsTechnology;
    private String visualEffectsTechnology;

    public SpecialMovie(String movieName, String producedBy, String directedBy, double duration, int year, String category,
                        String soundEffectsTechnology, String visualEffectsTechnology) {
        super(movieName, producedBy, directedBy, duration, year, category);
        this.soundEffectsTechnology = soundEffectsTechnology;
        this.visualEffectsTechnology = visualEffectsTechnology;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Sound Effects Technology: " + soundEffectsTechnology);
        System.out.println("Visual Effects Technology: " + visualEffectsTechnology);
    }
}

//InternationalMovie
class InternationalMovie extends Inheritance {
    private String country;
    private String language;

    public InternationalMovie(String movieName, String producedBy, String directedBy, double duration, int year, String category,
                              String country, String language) {
        super(movieName, producedBy, directedBy, duration, year, category);
        this.country = country;
        this.language = language;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Country: " + country);
        System.out.println("Language: " + language);
    }
}

class MovieTest {
    public static void main(String[] args) {
        Inheritance m1 = new Inheritance("Badava Rascal", "Daali Pictures");
        m1.acceptDetails("Shankar Guru", 2.13, 2023, "Rom-Com");

        Inheritance m2 = new Inheritance("Salaar - The Cease Fire", "Hombale Films", "Prashant Neel", 2.55, 2023, "Epic neo-noir action thriller");

        Inheritance m3 = new Inheritance("Bajirao Mastani", "Eros Entertainment");
        m3.acceptDetails("Sanjay Leela Bhansali", 2.38, 2015, "Historical Epic");

        SpecialMovie s1 = new SpecialMovie("Avengers: Endgame", "Marvel Studios", "Russo Brothers", 3.02, 2019, "Superhero",
                "Dolby Atmos", "ILM VFX");

        InternationalMovie i1 = new InternationalMovie("Parasite", "Barunson E&A", "Bong Joon-ho", 2.12, 2019, "Thriller",
                "South Korea", "Korean");

        System.out.println("\nMovies Details:\n");

        m1.displayDetails();
        System.out.println();
        m2.displayDetails();
        System.out.println();
        m3.displayDetails();
        System.out.println();
        s1.displayDetails();
        System.out.println();
        i1.displayDetails();
        System.out.println();

        System.out.println("Total movies in the library: " + Inheritance.getMoviesCount());
    }
}
