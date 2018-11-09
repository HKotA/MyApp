package com.example.gener.mycafeapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AdapterForNavigationDrawerMenuOne extends RecyclerView.ViewHolder {

    View MyView;

    public AdapterForNavigationDrawerMenuOne(View itemView) {
        super(itemView);
        MyView = itemView;
    }

    public void setDetails(final Context context, String image, String name, final String id) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/12231.ttf");

        ImageView pizzaImage = MyView.findViewById(R.id.id_item_image);
        Picasso.get().load(image).into(pizzaImage);
        TextView pizzaName = MyView.findViewById(R.id.id_item_name);
        pizzaName.setTypeface(font);
        pizzaName.setText(name);

        MyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.equals("01")){
                    Intent intent = new Intent(context, CategoryOne.class);
                    context.startActivity(intent);
                }else if (id.equals("02")){
                    Intent intent = new Intent(context, CategoryTwo.class);
                    context.startActivity(intent);
                }else if (id.equals("03")){
                    Intent intent = new Intent(context, CategoryThree.class);
                    context.startActivity(intent);
                }else if (id.equals("04")){
                    Intent intent = new Intent(context, CategoryFour.class);
                    context.startActivity(intent);
                }else if (id.equals("05")){
                    Intent intent = new Intent(context, CategoryFive.class);
                    context.startActivity(intent);
                }else if (id.equals("06")){
                    Intent intent = new Intent(context, CategorySix.class);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}