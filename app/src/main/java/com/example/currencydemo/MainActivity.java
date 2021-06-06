package com.example.currencydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.currencydemo.db.DBManager;
import com.example.currencydemo.screen.DemoActivity;
import com.example.currencydemo.service.DBService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBroadCastReceiver();
        setupDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    private void registerBroadCastReceiver() {
        broadcastReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SETUP_DB");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void setupDB() {
        Intent i = new Intent(getApplicationContext(), DBService.class);
        i.setAction("SETUP_DB");
        startService(i);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Received intent action : " + intent.getAction());
            startActivity(new Intent(getApplicationContext(), DemoActivity.class));
            finish();
        }
    }


}