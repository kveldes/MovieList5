package com.example.movielist5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MoviesDB.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_MOVIES_TABLE =
            "CREATE TABLE " + DbContract.MovieEntry.TABLE_NAME + " (" +
                    DbContract.MovieEntry.COLUMN_NAME_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    DbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE + TEXT_TYPE + COMMA_SEP +
                    DbContract.MovieEntry.COLUMN_NAME_MOVIE_DIRECTOR + TEXT_TYPE + COMMA_SEP +
                    DbContract.MovieEntry.COLUMN_NAME_MOVIE_DURATION + TEXT_TYPE + COMMA_SEP +
                    DbContract.MovieEntry.COLUMN_NAME_MOVIE_CATEG + TEXT_TYPE + COMMA_SEP +
                    DbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR + TEXT_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.MovieEntry.TABLE_NAME;



    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIES_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
