package ua.models;
public class Movie implements Comparable<Movie>{
    private int id; private String title; private int year;
    public Movie(int id,String t,int y){this.id=id;this.title=t;this.year=y;}
    public int getId(){return id;} public String getTitle(){return title;} public int getYear(){return year;}
    @Override public int compareTo(Movie o){ return Integer.compare(this.id,o.id);}
    public static java.util.Comparator<Movie> byTitle=java.util.Comparator.comparing(Movie::getTitle);
    public static java.util.Comparator<Movie> byYear=java.util.Comparator.comparingInt(Movie::getYear);
}
