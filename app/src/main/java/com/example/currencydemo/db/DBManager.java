package com.example.currencydemo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.currencydemo.model.Currency;

import java.util.ArrayList;

public class DBManager {
    private static final String TAG = "DBManager";

    private static DBManager mInstance;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    private DBManager(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
        db = databaseHelper.getWritableDatabase();
    }

    public static void init(Context context) {
        if (mInstance == null) {
            mInstance = new DBManager(context);
        }
    }

    public synchronized static DBManager getInstance() {
        if (mInstance != null) {
            if (mInstance.db == null)
                mInstance.db = mInstance.databaseHelper.getWritableDatabase();
            return mInstance;
        }

        throw new IllegalArgumentException("Should call DBManager.init() first!");
    }

    public void closeAndDelete(Context context) {
        db.close();
        context.deleteDatabase(DatabaseHelper.DB_NAME);
    }

    public ArrayList<Currency> getAllCurrency() {
        ArrayList<Currency> currency_list = new ArrayList<>();
        String sql = "SELECT * FROM tocurrency";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String symbol = cursor.getString(cursor.getColumnIndex("symbol"));

                Currency mCurrency = new Currency(id, name, symbol);
                currency_list.add(mCurrency);
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d(TAG, "currency list has " + currency_list.size() + " items");
        return currency_list;
    }

    public ArrayList<Currency> getAllCurrencyDesc() {
        ArrayList<Currency> currency_list = new ArrayList<>();
        String sql = "SELECT * FROM tocurrency ORDER BY id DESC";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String symbol = cursor.getString(cursor.getColumnIndex("symbol"));

                Currency mCurrency = new Currency(id, name, symbol);
                currency_list.add(mCurrency);
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d(TAG, "currency list has " + currency_list.size() + " items");
        return currency_list;
    }
}
