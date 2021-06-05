package com.example.currencydemo.screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencydemo.R;
import com.example.currencydemo.service.DBService;

public class DemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        setupDB();
    }

    private void setupDB(){
        Intent i = new Intent(getApplicationContext(), DBService.class);
        i.setAction("SETUP_DB");
        startService(i);
    }
}
