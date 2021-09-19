package com.example.movielist5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private int initialPin = 123;
    private Button LoginPinBtn;
    private Button LoginSetPinBtn;
    private EditText PinEtxt;
    LoginDbHelper loginDbHelper;
    int lastPin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDbHelper = new LoginDbHelper(this);

        LoginPinBtn = findViewById(R.id.inMovbutton);
        LoginSetPinBtn = findViewById(R.id.button2);
        PinEtxt = findViewById(R.id.logineditText);

    }


    public void login(View view) {
        if ((PinEtxt.getText().toString().equals("123"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            startActivity(intent);
            Toast.makeText(this, "You have Login", Toast.LENGTH_LONG).show();
        } else {
            latestPin();
        }

    }

    public void setPin(View view) {
        Pin pin1 = new Pin(PinEtxt.getText().toString());
        addPin(pin1);
        Toast.makeText(this, "PIN is Set", Toast.LENGTH_LONG).show();
    }

    // Adding new Pin
    public void addPin(Pin pin) {
        // Gets the data repository in write mode
        SQLiteDatabase dbLogin = loginDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(LoginDbContract.LoginEntry.COLUMN_NAME_PIN, pin.getPin());
            // Inserting Row
            dbLogin.insert(LoginDbContract.LoginEntry.TABLE_NAME, null, values);
            dbLogin.close(); // Closing database connection

    }


    public void latestPin(){
        String s;
        SQLiteDatabase newdb = loginDbHelper.getReadableDatabase();
        Cursor c = newdb.rawQuery(" SELECT pin FROM ids ORDER BY pin_id DESC  LIMIT 1 ", null);
        c.moveToFirst();
        s=c.getString(c.getColumnIndex("pin"));
        newdb.close();
        if ((PinEtxt.getText().toString().equals(s))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            startActivity(intent);
            Toast.makeText(this, "You have Login", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Something Wrong Happen", Toast.LENGTH_LONG).show();
        }
    }



}
