package com.example.currencydemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Currency implements Parcelable {
    private String id;
    private String name;
    private String symbol;

    public Currency(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    protected Currency(Parcel in) {
        id = in.readString();
        name = in.readString();
        symbol = in.readString();
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            Currency currency = new Currency(in.readString(), in.readString(), in.readString());
            return currency;
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(symbol);
    }
}
