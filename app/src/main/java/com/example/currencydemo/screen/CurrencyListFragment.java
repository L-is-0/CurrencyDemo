package com.example.currencydemo.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencydemo.R;
import com.example.currencydemo.adapter.CurrencyAdapter;
import com.example.currencydemo.model.Currency;

import java.util.ArrayList;

public class CurrencyListFragment extends Fragment {
    private static final String TAG = "CurrencyListFragment";
    private RecyclerView rvCurrency;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_currencylist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCurrency = view.findViewById(R.id.rv_currency);
//        ArrayList<Currency> mCurrency;
//        Bundle bundle = getArguments();
//        if (bundle != null && bundle.getParcelableArrayList("currencyList") != null) {
//            mCurrency = bundle.getParcelableArrayList("currencyList");
//        } else {
//            mCurrency = new ArrayList<>();
//            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
//            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
//            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
//            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
//            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
//        }
//
//        CurrencyAdapter currencyAdapter = new CurrencyAdapter(mCurrency, getContext());
//        RecyclerView rvCurrency = view.findViewById(R.id.rv_currency);
//        rvCurrency.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
//        rvCurrency.setAdapter(currencyAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "fragment on start");
        ArrayList<Currency> mCurrency;
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getParcelableArrayList("currencyList") != null) {
            mCurrency = bundle.getParcelableArrayList("currencyList");
        } else {
            mCurrency = new ArrayList<>();
            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
            mCurrency.add(new Currency("ABC", "ABC.come", "ABC"));
        }

        CurrencyAdapter currencyAdapter = new CurrencyAdapter(mCurrency, getContext());

        rvCurrency.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvCurrency.setAdapter(currencyAdapter);
    }
}
