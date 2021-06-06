package com.example.currencydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencydemo.R;
import com.example.currencydemo.model.Currency;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private final ArrayList<Currency> mCurrency;
    private final LayoutInflater mInflater;
    private final String TAG = "CurrencyAdapter";
    private Context mContext;

    public CurrencyAdapter(ArrayList<Currency> mCurrency, Context mContext) {
        this.mContext = mContext;
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
    public void onBindViewHolder(@NonNull CurrencyAdapter.CurrencyViewHolder holder, final int position) {
        Log.d(TAG, String.valueOf(mCurrency.get(position).getId().toUpperCase().charAt(0)));
        holder.tvId.setText(String.valueOf(mCurrency.get(position).getId().toUpperCase().charAt(0)));
        holder.tvName.setText(mCurrency.get(position).getName());
        holder.tvSymbol.setText(mCurrency.get(position).getSymbol());
        holder.view_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "on item " + mCurrency.get(position).getName() + " clicked ", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCurrency.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvSymbol;
        View view_currency;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_currency = itemView.findViewById(R.id.view_item_currency);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSymbol = itemView.findViewById(R.id.tv_symbol);
        }
    }
}
