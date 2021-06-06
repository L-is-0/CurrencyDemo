package com.example.currencydemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.currencydemo.db.DBManager;
import com.example.currencydemo.model.Currency;

import java.util.ArrayList;

public class DBService extends IntentService {
    private static final String TAG = "IntentService";
    private static final String SET_UP_DB_ACTION = "SETUP_DB";
    private static final String LOAD_DB_ACTION = "LOAD_DB";
    private static final String SORT_DB_ACTION = "SORT_DB";

    public DBService() {
        super("DBService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent");
        if (intent != null) {
            final String action = intent.getAction();
            if (action != null) {
                switch (action) {
                    case SET_UP_DB_ACTION:
                        setupDB();
                        break;
                    case LOAD_DB_ACTION:
                        loadDB();
                        break;
                    case SORT_DB_ACTION:
                        sortDB();
                        break;
                }
            }
        }
    }

    private void setupDB() { // setting up the database
        DBManager.init(this);
        Intent intent1 = new Intent(SET_UP_DB_ACTION);
        intent1.putExtra("msg", "Completed action : " + SET_UP_DB_ACTION);
        sendBroadcast(intent1);
    }

    private void loadDB() {
        ArrayList<Currency> mCurrency = DBManager.getInstance().getAllCurrency();
        Intent intent = new Intent(LOAD_DB_ACTION);
        intent.putExtra("msg", "Completed action : " + LOAD_DB_ACTION);
        intent.putParcelableArrayListExtra("currencyList", mCurrency);
        sendBroadcast(intent);
    }

    private void sortDB() {
        ArrayList<Currency> mCurrency = DBManager.getInstance().getAllCurrencyDesc();
        Intent intent2 = new Intent(LOAD_DB_ACTION);
        intent2.putExtra("msg", "Completed action : " + LOAD_DB_ACTION);
        intent2.putParcelableArrayListExtra("currencyList", mCurrency);
        sendBroadcast(intent2);
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
