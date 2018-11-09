package com.example.gener.mycafeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gener.mycafeapp.CartInfo.CartInfoDatabase;

public class CartDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Cart.db";
    public static final int DATABASE_VERSION = 1;

    public CartDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CreateCartTable = "CREATE TABLE " +
                CartInfoDatabase.TABLE_NAME + " (" +
                CartInfoDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CartInfoDatabase.COLUMN_NAME + " TEXT NOT NULL, " +
                CartInfoDatabase.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                CartInfoDatabase.COLUMN_IMAGE + " TEXT NOT NULL, " +
                CartInfoDatabase.COLUMN_PRICE + " INTEGER NOT NULL, " +
                CartInfoDatabase.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(CreateCartTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CartInfo.CartInfoDatabase.TABLE_NAME);
        onCreate(db);
    }

    public void deleteDatabase(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DELETE FROM " + CartInfo.CartInfoDatabase.TABLE_NAME);
    }
}
