package com.example.gener.mycafeapp;

import android.provider.BaseColumns;

public class CartInfo {

    private CartInfo(){

    }

    public static final class CartInfoDatabase implements BaseColumns {
        public static final String TABLE_NAME = "Cart";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_AMOUNT = "Amount";
        public static final String COLUMN_IMAGE = "Image";
        public static final String COLUMN_PRICE = "Price";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
