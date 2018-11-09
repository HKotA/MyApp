package com.example.gener.mycafeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetailItem extends AppCompatActivity {

    ImageView imageView;
    TextView desc;
    TextView pr;
    TextView oneitem;
    TextView oneitem_price;
    TextView fullprice;
    FloatingActionButton floatingActionButton;
    private SQLiteDatabase sqLiteDatabase;
    private AdapterForCart adapterForCart;
    String currency = " грн.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        CartDatabase cartDatabase = new CartDatabase(DetailItem.this);
        sqLiteDatabase = cartDatabase.getWritableDatabase();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/12231.ttf");

        final String name = getIntent().getStringExtra("NAME");
        final String image = getIntent().getStringExtra("IMAGE");
        String price = getIntent().getStringExtra("PRICE");
        String id = getIntent().getStringExtra("ID");
        String description = getIntent().getStringExtra("DESCRIPTION");

        Toolbar toolbar = findViewById(R.id.id_detail_item_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(name);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.id_detail_item_image);
        Picasso.get().load(image).into(imageView);

        desc = findViewById(R.id.id_detail_item_description);
        desc.setTypeface(font);
        desc.setText(description);

        oneitem = findViewById(R.id.id_detail_item_oneitem);
        oneitem.setText(price);

        oneitem_price = findViewById(R.id.id_detail_item_oneitemprice);
        oneitem_price.setTypeface(font);
        oneitem_price.setText("Ціна за 1 шт: " + oneitem.getText().toString() + currency);

        fullprice = findViewById(R.id.id_detail_item_fullprice);
        fullprice.setText(price);

        pr = findViewById(R.id.id_detail_item_price);
        pr.setTypeface(font);
        pr.setText(price + currency);

        final ElegantNumberButton elegantNumberButton = findViewById(R.id.id_detail_item_amount);
        elegantNumberButton.setNumber("1");
        elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                String Number = elegantNumberButton.getNumber();
                int number = Integer.parseInt(String.valueOf(Number));

                if (number == 0){
                    elegantNumberButton.setNumber("1");
                    String newNumber = elegantNumberButton.getNumber();
                    int amount = Integer.parseInt(String.valueOf(newNumber));
                    int one = Integer.parseInt(oneitem.getText().toString());
                    int full = one * amount;
                    pr.setText(String.valueOf(full) + currency);
                    fullprice.setText(String.valueOf(full));
                }else if (number >= 1){
                    int one = Integer.parseInt(oneitem.getText().toString());
                    int full = one * number;
                    pr.setText(String.valueOf(full) + currency);
                    fullprice.setText(String.valueOf(full));
                }
            }
        });



        floatingActionButton = findViewById(R.id.id_detail_item_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(CartInfo.CartInfoDatabase.COLUMN_NAME, name);
                contentValues.put(CartInfo.CartInfoDatabase.COLUMN_AMOUNT, elegantNumberButton.getNumber());
                contentValues.put(CartInfo.CartInfoDatabase.COLUMN_IMAGE, image);
                contentValues.put(CartInfo.CartInfoDatabase.COLUMN_PRICE, fullprice.getText().toString());

                sqLiteDatabase.insert(CartInfo.CartInfoDatabase.TABLE_NAME, null, contentValues);

                Toast.makeText(DetailItem.this, "Продукт добавлено в корзину", Toast.LENGTH_LONG).show();
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(DetailItem.this, R.drawable.fabafter));


            }
        });

    }
}
