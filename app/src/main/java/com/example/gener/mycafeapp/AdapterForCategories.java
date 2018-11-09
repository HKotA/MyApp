package com.example.gener.mycafeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AdapterForCategories extends RecyclerView.ViewHolder {

    View MyView;

    public AdapterForCategories(View itemView) {
        super(itemView);
        MyView = itemView;
    }

    public void setDetails(final Context context, final String image, final String name, final String id, final String price, final String description) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/12231.ttf");

        ImageView pizzaImage = MyView.findViewById(R.id.id_item_image);
        Picasso.get().load(image).into(pizzaImage);
        TextView pizzaName = MyView.findViewById(R.id.id_item_name);
        pizzaName.setTypeface(font);
        pizzaName.setText(name);

        MyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailItem.class);
                intent.putExtra("NAME", name);
                intent.putExtra("IMAGE", image);
                intent.putExtra("PRICE", price);
                intent.putExtra("ID", id);
                intent.putExtra("DESCRIPTION", description);
                context.startActivity(intent);
            }
        });

    }
}
