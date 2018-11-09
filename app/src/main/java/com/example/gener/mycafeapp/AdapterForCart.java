package com.example.gener.mycafeapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterForCart extends RecyclerView.Adapter<AdapterForCart.GroceryViewHolder>{

    private Context adapterContext;
    private Cursor adapterCursor;

    String currency = " грн.";
    String amount = " шт.";



    public AdapterForCart(Context context, Cursor cursor){

        this.adapterContext = context;
        this.adapterCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView amount;
        public ImageView image;
        public TextView price;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.id_item_in_cart_name);
            amount = itemView.findViewById(R.id.id_item_in_cart_amount);
            image = itemView.findViewById(R.id.id_item_in_cart_image);
            price = itemView.findViewById(R.id.id_item_in_cart_price);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(adapterContext);
        View view = inflater.inflate(R.layout.item_in_cart, viewGroup, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder groceryViewHolder, int i) {

        Typeface font = Typeface.createFromAsset(adapterContext.getAssets(), "fonts/12231.ttf");

//        Курсор в 0 позиції. Метод ставить його в 1. (напевне). ! Ні, курсор запитує, якщо не в 1, то помилка.
        if (!adapterCursor.moveToPosition(i)){
            return;
        }

        String nameFromDB = adapterCursor.getString(adapterCursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_NAME));
        String amountFromDB = adapterCursor.getString(adapterCursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_AMOUNT));
        String imageFromDB = adapterCursor.getString(adapterCursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_IMAGE));
        String fullPriceFromDB = adapterCursor.getString(adapterCursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_PRICE));
        long idFromDB = adapterCursor.getLong(adapterCursor.getColumnIndex(CartInfo.CartInfoDatabase._ID));

        groceryViewHolder.name.setTypeface(font);
        groceryViewHolder.amount.setTypeface(font);
        groceryViewHolder.price.setTypeface(font);

        groceryViewHolder.name.setText("Назва: " + nameFromDB);
        groceryViewHolder.amount.setText("Кількість: " + amountFromDB + amount);
        Picasso.get().load(imageFromDB).into(groceryViewHolder.image);
        groceryViewHolder.price.setText("Ціна: " + fullPriceFromDB + currency);

        groceryViewHolder.itemView.setTag(idFromDB);



    }

    @Override
    public int getItemCount() {
        return adapterCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (adapterCursor != null){
            adapterCursor.close();
        }

        adapterCursor = newCursor;

        if (newCursor != null){
//          Обновити курсор.
            notifyDataSetChanged();
        }
    }
}