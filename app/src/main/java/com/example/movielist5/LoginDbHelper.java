package com.example.movielist5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "IdDB.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_LOGIN_TABLE =
            "CREATE TABLE " + LoginDbContract.LoginEntry.TABLE_NAME + " (" +
             LoginDbContract.LoginEntry.COLUMN_NAME_LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
             LoginDbContract.LoginEntry.COLUMN_NAME_PIN + TEXT_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LoginDbContract.LoginEntry.TABLE_NAME;


    public LoginDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LOGIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}


