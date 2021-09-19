package com.example.movielist5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    DbHelper mDbHelper;
    private Button InsertMovbutton;
    private Button VieMovbutton;
    private Button deletemovbutton;
    private Button updatemoviebutton;
    private Button searchmovbutton;
    private Button searchMovTitlebutton;
    private Button searchMovTitlePabutton;
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7,ed8,ed9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mDbHelper = new DbHelper(getApplicationContext());
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);
        ed5 = (EditText) findViewById(R.id.editText5);
        ed6 = (EditText) findViewById(R.id.editText6);
        ed7 = (EditText) findViewById(R.id.editText7);
        ed8 = (EditText) findViewById(R.id.editText8);
        ed9 = (EditText) findViewById(R.id.editText9);
        InsertMovbutton = findViewById(R.id.inMovbutton);
        VieMovbutton = findViewById(R.id.vmovbutton);
        deletemovbutton = findViewById(R.id.delmovbutton);
        updatemoviebutton = findViewById(R.id.updatebutton);
        searchmovbutton = findViewById(R.id.searchmovbutton);
        searchMovTitlebutton = findViewById(R.id.searchmovtitlebutton);
        searchMovTitlePabutton = findViewById(R.id.searchmovtitlepartialbutton);

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // CRUD OPERATIONS
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Adding new Movie
    public void addMovie(Movie movie) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_ID, movie.getMovie_id());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE, movie.getMovie_title());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_DIRECTOR, movie.getMovie_director());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_DURATION, movie.getMovie_duration());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_CATEG, movie.getMovie_categ());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR, movie.getMovie_year());

        // Inserting Row
        db.insert(DbContract.MovieEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Movies
    public List<Movie> getAllMovies() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<Movie> movieList = new ArrayList<Movie>();
        String[] projection = {
                //DbContract.MovieEntry.COLUMN_NAME_MOVIE_ID,
                DbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE,
                DbContract.MovieEntry.COLUMN_NAME_MOVIE_DIRECTOR,
                DbContract.MovieEntry.COLUMN_NAME_MOVIE_DURATION,
                DbContract.MovieEntry.COLUMN_NAME_MOVIE_CATEG,
                DbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR
        };
        Cursor c = db.query(
                DbContract.MovieEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                     // null columns means all
                null,                                     // null values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );
        while (c.moveToNext()) {
            Movie movie = new Movie(
                    c.getString(c.getColumnIndex(DbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE)),
                    c.getString(c.getColumnIndex(DbContract.MovieEntry.COLUMN_NAME_MOVIE_DIRECTOR)),
                    c.getInt(c.getColumnIndex(DbContract.MovieEntry.COLUMN_NAME_MOVIE_DURATION)),
                    c.getString(c.getColumnIndex(DbContract.MovieEntry.COLUMN_NAME_MOVIE_CATEG)),
                    c.getInt(c.getColumnIndex(DbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR)));
            movieList.add(movie);
        }
        db.close();
        return movieList;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Getting Movies Count
    public int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + DbContract.MovieEntry.TABLE_NAME;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        db.close();
        // return count
        return count;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Updating single Movie
    public int updateMovie(Movie movie) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // New value for two columns
        ContentValues values = new ContentValues();
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE, movie.getMovie_title());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_DIRECTOR, movie.getMovie_director());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_DURATION, movie.getMovie_duration());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_CATEG, movie.getMovie_categ());
        values.put(DbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR, movie.getMovie_year());
        // Which row to update, based on the ID
        String selection = DbContract.MovieEntry.COLUMN_NAME_MOVIE_ID + " =?";
        String[] selectionArgs = {String.valueOf(movie.getMovie_id())};
        int count = db.update(
                DbContract.MovieEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        db.close();
        return count;
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public void messageshow(String message) {
        AlertDialog.Builder abuilder;
        abuilder = new AlertDialog.Builder(this);
        abuilder.setTitle("Movies");
        abuilder.setMessage(message);
        abuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = abuilder.create();
        dialog.show();
    }

    public void addMve(View view) {
        Movie movie = new Movie(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), ed4.getText().toString(), ed5.getText().toString());
        addMovie(movie);
        Toast.makeText(this, "Movie added", Toast.LENGTH_LONG).show();
    }

    public void viewallMovies(View view) {
        List<Movie> MovieList = getAllMovies();
        if (MovieList != null) {
            StringBuffer buffer = new StringBuffer();
            for (Movie movie : MovieList) {
                // buffer.append(movie.getMovie_id()+"\n");
                buffer.append(movie.getMovie_title() + "\n");
                buffer.append(movie.getMovie_director() + "\n");
                buffer.append(movie.getMovie_duration() + "\n");
                buffer.append(movie.getMovie_categ() + "\n");
                buffer.append(movie.getMovie_year() + "\n\n");
            }
            messageshow(buffer.toString());
        }
    }

    public void delMovie(View view) {
        String title = ed6.getText().toString();
        deleteMovie(title);
        Toast.makeText(this, "Movie Deleted", Toast.LENGTH_LONG).show();

    }

    public void uptMovie(View view) {
        String title = ed1.getText().toString();
        String movDirector = ed2.getText().toString();
        int movDuration = Integer.parseInt(ed3.getText().toString());
        String movCategory = ed4.getText().toString();
        int movYear = Integer.parseInt(ed5.getText().toString());

        updateMovie(title, movDirector, movDuration, movCategory, movYear);
        Toast.makeText(this, "Movie Updated", Toast.LENGTH_LONG).show();

    }

    public void fByDirector(View view) {
        String movDirector = ed7.getText().toString();
        List<Movie> MovieList = getAllMovies();
        if (MovieList != null) {
            StringBuffer buffer = new StringBuffer();
            for (Movie movie : MovieList) {
                if (movie.getMovie_director().equals(movDirector)) {
                    buffer.append(movie.getMovie_title() + "\n");
                    buffer.append(movie.getMovie_director() + "\n");
                    buffer.append(movie.getMovie_duration() + "\n");
                    buffer.append(movie.getMovie_categ() + "\n");
                    buffer.append(movie.getMovie_year() + "\n\n");
                }else {
                    continue;
                }
            }
            messageshow(buffer.toString());
        }

    }

    public void fByTitle(View view) {
        String movTitle = ed8.getText().toString();
        List<Movie> MovieList = getAllMovies();
        if (MovieList != null) {
            StringBuffer buffer = new StringBuffer();
            for (Movie movie : MovieList) {
                if (movie.getMovie_title().equals(movTitle)) {
                    buffer.append(movie.getMovie_title() + "\n");
                    buffer.append(movie.getMovie_director() + "\n");
                    buffer.append(movie.getMovie_duration() + "\n");
                    buffer.append(movie.getMovie_categ() + "\n");
                    buffer.append(movie.getMovie_year() + "\n\n");
                }else {
                    continue;
                }
            }
            messageshow(buffer.toString());
        }

    }
    public void fByparsalTitle(View view) {
        String movTitle = ed9.getText().toString().toLowerCase();
        List<Movie> MovieList = getAllMovies();
        if (MovieList != null) {
            StringBuffer buffer = new StringBuffer();
            for (Movie movie : MovieList) {
                String s=movie.getMovie_title().toLowerCase();
                if (s.indexOf(movTitle)!= -1) {
                    buffer.append(movie.getMovie_title() + "\n");
                    buffer.append(movie.getMovie_director() + "\n");
                    buffer.append(movie.getMovie_duration() + "\n");
                    buffer.append(movie.getMovie_categ() + "\n");
                    buffer.append(movie.getMovie_year() + "\n\n");
                }else {
                    continue;
                }
            }
            messageshow(buffer.toString());
        }

    }
    // Deleting single Movie
    public void deleteMovie(String title) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Which row to delete, based on the Movie_Title
        db.execSQL(" DELETE FROM movies WHERE movie_title = '" + title + "'");
        db.close();
    }


    // Update single Movie
    public void updateMovie(String title, String movDirector, int movDuration, String movCategory, int movYear) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String s = "UPDATE movies  SET  movie_director =  '" + movDirector + "'  , movie_duration =  '" + movDuration + "'  , movie_categ = '" + movCategory + "' , movie_year = '" + movYear + "' WHERE movie_title = '" + title + "'";
        db.execSQL(s);
        db.close();

    }

}