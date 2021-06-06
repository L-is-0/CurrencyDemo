package com.example.currencydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencydemo.R;
import com.example.currencydemo.model.Currency;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private final ArrayList<Currency> mCurrency;
    private final LayoutInflater mInflater;

    public CurrencyAdapter(ArrayList<Currency> mCurrency, Context mContext) {
        this.mCurrency = mCurrency;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyAdapter.CurrencyViewHolder holder, int position) {
        holder.tvName.setText(mCurrency.get(position).getName());
        holder.tvSymbol.setText(mCurrency.get(position).getSymbol());

    }

    @Override
    public int getItemCount() {
        return mCurrency.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSymbol;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSymbol = itemView.findViewById(R.id.tv_symbol);
        }
    }
}
