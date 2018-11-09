package com.example.gener.mycafeapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer_Menu_One extends Fragment {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static Context context;

    public NavigationDrawer_Menu_One() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation_drawer__menu__one, container, false);

        recyclerView = view.findViewById(R.id.id_nd_menu_one_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Menu");

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        context = getActivity();

        FirebaseRecyclerAdapter<ModelForMenu, AdapterForNavigationDrawerMenuOne> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelForMenu, AdapterForNavigationDrawerMenuOne>(
                        ModelForMenu.class,
                        R.layout.cardview_item,
                        AdapterForNavigationDrawerMenuOne.class,
                        databaseReference
                ) {

                    @Override
                    protected void populateViewHolder(AdapterForNavigationDrawerMenuOne viewHolder, ModelForMenu model, int position) {
                        viewHolder.setDetails(context, model.getImage(), model.getName(), model.getId());
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
