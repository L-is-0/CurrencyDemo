package com.example.currencydemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
