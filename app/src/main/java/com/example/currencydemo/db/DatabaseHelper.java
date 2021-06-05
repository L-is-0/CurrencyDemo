package com.example.currencydemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String DB_NAME = "currency.db"; // Declare database name
    private static final int version = 1; // Declare database version
    public static DatabaseHelper mInstance = null;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, version);
    }

    public synchronized static DatabaseHelper getInstance(Context context) {
        // use the application context, which will ensure that you
        // don't accidentally an Activity's context
        if (mInstance == null) {
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlite3 = "CREATE TABLE `tocurrency` (\n  `id` varchar(255) PRIMARY KEY NOT NULL ,\n  `name` varchar(255) NOT NULL,\n  `symbol` varchar(255) NOT NULL\n)";
        try {
            db.execSQL(sqlite3);
        } catch (SQLException e) {
            Log.d(TAG, "created db failed " + e.getMessage());
            //TODO: NEED TO CATCH THE ERROR AND REBUILD DB
        }

        try {
            insertData("tocurrency", db, true);
        } catch (Exception insertException) {
            Log.d(TAG, "Insert fail for table: " + "tocurrency");
            Log.d(TAG, insertException.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Prepare the sql commands for inserting data into local sqlite db
    public void insertData(String table, SQLiteDatabase db, boolean rebuildingWholeDatabase) throws Exception {
        // Sql commands for inserting the data
        ContentValues values = new ContentValues();
        String response = "[\n" +
                "  {\n" +
                "    \"id\": \"BTC\",\n" +
                "    \"name\": \"Bitcoin\",\n" +
                "    \"symbol\": \"BTC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"ETH\",\n" +
                "    \"name\": \"Ethereum\",\n" +
                "    \"symbol\": \"ETH\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"XRP\",\n" +
                "    \"name\": \"XRP\",\n" +
                "    \"symbol\": \"XRP\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"BCH\",\n" +
                "    \"name\": \"Bitcoin Cash\",\n" +
                "    \"symbol\": \"BCH\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"LTC\",\n" +
                "    \"name\": \"Litecoin\",\n" +
                "    \"symbol\": \"LTC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"EOS\",\n" +
                "    \"name\": \"EOS\",\n" +
                "    \"symbol\": \"EOS\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"BNB\",\n" +
                "    \"name\": \"Binance Coin\",\n" +
                "    \"symbol\": \"BNB\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"LINK\",\n" +
                "    \"name\": \"Chainlink\",\n" +
                "    \"symbol\": \"LINK\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"NEO\",\n" +
                "    \"name\": \"NEO\",\n" +
                "    \"symbol\": \"NEO\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"ETC\",\n" +
                "    \"name\": \"Ethereum Classic\",\n" +
                "    \"symbol\": \"ETC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"ONT\",\n" +
                "    \"name\": \"Ontology\",\n" +
                "    \"symbol\": \"ONT\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"CRO\",\n" +
                "    \"name\": \"Crypto.com Chain\",\n" +
                "    \"symbol\": \"CRO\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"CUC\",\n" +
                "    \"name\": \"Cucumber\",\n" +
                "    \"symbol\": \"CUC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"USDC\",\n" +
                "    \"name\": \"USD Coin\",\n" +
                "    \"symbol\": \"USDC\"\n" +
                "  }\n" +
                "]";
        JSONArray array = new JSONArray(response);
        if (array.length() > 0) {
            db.beginTransaction();
            try {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    Iterator<String> iter = object.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        values.put(key, object.getString(key));
                    }
                    // Insert the new row, returning the primary key value of the new row
                    db.insertOrThrow(table, "null", values);
                    values.clear();
                    Log.d(TAG, "Inserted into " + table + " successfully");

                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }
}
