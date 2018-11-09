package com.example.gener.mycafeapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdapterForNavigationDrawerMenuThree extends RecyclerView.ViewHolder {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference2;

    View MyView;

    public AdapterForNavigationDrawerMenuThree(View itemView) {
        super(itemView);
        MyView = itemView;
    }

    public void setDetails(final Context context, String timeoforder) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/12231.ttf");

        final TextView TimeOfOrder = MyView.findViewById(R.id.id_item_for_order_history_timeoforder);
        TimeOfOrder.setTypeface(font);
        TimeOfOrder.setText(timeoforder);

        final TextView TrueOrFalse = MyView.findViewById(R.id.id_item_for_order_history_trueorfalse);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("OrderHistory").child(timeoforder).child("true");
        databaseReference2 = firebaseDatabase.getReference("Order").child(CurrentUser.currentUser.getPhone()).child(timeoforder).child("true");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String TRUE = dataSnapshot.getValue().toString();
                if (TRUE.equals("false")){

                    TrueOrFalse.setText("В очікуванні...");
                }else if (TRUE.equals("true")){

                    databaseReference2.setValue("true");
                    TrueOrFalse.setText("Виконано");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}