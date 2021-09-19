package com.example.movielist5;

public class Movie {
    private int movie_id;
    private String movie_title;
    private String movie_director;
    private int movie_duration;
    private String movie_categ;
    private int movie_year;


    public Movie( String movie_title, String movie_director, int movie_duration, String movie_categ, int movie_year) {
        this.movie_title = movie_title;
        this.movie_director = movie_director;
        this.movie_duration = movie_duration;
        this.movie_categ = movie_categ;
        this.movie_year = movie_year;
    }

    public Movie(String movie_title, String movie_director, String movie_duration, String movie_categ, String movie_year) {
        //Integer.parseInt(toString);
        this.movie_title = movie_title;
        this.movie_director = movie_director;
        this.movie_duration = Integer.parseInt(movie_duration);
        this.movie_categ = movie_categ;
        this.movie_year = Integer.parseInt(movie_year);
    }


    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public float getMovie_duration() {
        return movie_duration;
    }

    public void setMovie_duration(int movie_duration) {
        this.movie_duration = movie_duration;
    }

    public String getMovie_categ() {
        return movie_categ;
    }

    public void setMovie_categ(String movie_categ) {
        this.movie_categ = movie_categ;
    }

    public int getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(int movie_year) {
        this.movie_year = movie_year;
    }

}
