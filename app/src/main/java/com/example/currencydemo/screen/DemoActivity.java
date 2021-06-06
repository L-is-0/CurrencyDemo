package com.example.currencydemo.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.currencydemo.MainActivity;
import com.example.currencydemo.R;
import com.example.currencydemo.model.Currency;
import com.example.currencydemo.service.DBService;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {
    private static final String TAG = "DemoActivity";
    private BroadcastReceiver broadcastReceiver;
    private ArrayList<Currency> mCurrency;
    private static final String LOAD_DB_ACTION = "LOAD_DB";
    private static final String SORT_DB_ACTION = "SORT_DB";
    private CurrencyListFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        registerBroadCastReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void setupFragmentView() {
        Log.d(TAG, "setupFragmentView");
        mFragment = new CurrencyListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("currencyList", mCurrency);
        mFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_view, mFragment);
        ft.commit();
    }

    public void onLoadClicked(View view) {
        Intent i = new Intent(getApplicationContext(), DBService.class);
        i.setAction(LOAD_DB_ACTION);
        startService(i);
    }

    public void onSortClicked(View view) {
        Intent i = new Intent(getApplicationContext(), DBService.class);
        i.setAction(SORT_DB_ACTION);
        startService(i);
    }

    private void registerBroadCastReceiver() {
        broadcastReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("LOAD_DB");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive");
            if (intent != null) {
                Log.d(TAG, "Received intent action : " + intent.getAction());
                if (intent.getAction() != null)
                    if (LOAD_DB_ACTION.equals(intent.getAction())) {
                        mCurrency = intent.getParcelableArrayListExtra("currencyList");
                        setupFragmentView();
                    }
            }
        }
    }
}
