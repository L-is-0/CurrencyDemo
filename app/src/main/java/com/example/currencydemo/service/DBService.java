package com.example.currencydemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.currencydemo.db.DBManager;

public class DBService extends IntentService {
    private static final String TAG = "IntentService";
    private static final String SET_UP_DB_ACTION = "SETUP_DB";

    public DBService() {
        super("DBService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent");
        if (intent != null) {
            final String action = intent.getAction();
            if (SET_UP_DB_ACTION.equals(action)) {
                setupDB();
            }
        }
    }

    private void setupDB() { // setting up the database
        DBManager.init(this);
    }

    @Override
    public void onCreate() {
        Log.i("myIntentService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("myIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("myIntentService", "onDestroy");
        super.onDestroy();
    }
}
