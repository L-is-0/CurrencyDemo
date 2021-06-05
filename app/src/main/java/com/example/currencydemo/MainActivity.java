package com.example.currencydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.currencydemo.db.DBManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDB();

    }

    private void setupDB() { // setting up the database
        DBManager.init(this);
    }
}