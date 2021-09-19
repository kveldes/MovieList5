package com.example.movielist5;

import android.provider.BaseColumns;

public class LoginDbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LoginDbContract() { }

    /* Inner class that defines the table contents */
    public static class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "ids";
        public static final String COLUMN_NAME_LOGIN_ID = "pin_id";
        public static final String COLUMN_NAME_PIN = "pin";
    }

}
