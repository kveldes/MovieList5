package com.example.movielist5;

import android.provider.BaseColumns;

public class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() { }

    /* Inner class that defines the table contents */
    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_NAME_MOVIE_DIRECTOR = "movie_director";
        public static final String COLUMN_NAME_MOVIE_DURATION = "movie_duration";
        public static final String COLUMN_NAME_MOVIE_CATEG = "movie_categ";
        public static final String COLUMN_NAME_MOVIE_YEAR = "movie_year";
    }

}
