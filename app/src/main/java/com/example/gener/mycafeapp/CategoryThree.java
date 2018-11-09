package com.example.gener.mycafeapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryThree extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_three);

        recyclerView = findViewById(R.id.id_category_three_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Category_03");
    }

    @Override
    public void onStart() {
        super.onStart();

        context = this;

        FirebaseRecyclerAdapter<ModelForCategories, AdapterForCategories> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelForCategories, AdapterForCategories>(
                        ModelForCategories.class,
                        R.layout.cardview_item,
                        AdapterForCategories.class,
                        databaseReference
                ) {

                    @Override
                    protected void populateViewHolder(AdapterForCategories viewHolder, ModelForCategories model, int position) {
                        viewHolder.setDetails(context, model.getImage(), model.getName(), model.getId(), model.getPrice(), model.getDescription());
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
