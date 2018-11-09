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
public class NavigationDrawer_Menu_Three extends Fragment {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static Context context;

    public NavigationDrawer_Menu_Three() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation_drawer__menu__one, container, false);

        recyclerView = view.findViewById(R.id.id_nd_menu_one_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Order").child(CurrentUser.currentUser.getPhone());

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        context = getActivity();

        FirebaseRecyclerAdapter<ModelForOrderHistory, AdapterForNavigationDrawerMenuThree> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelForOrderHistory, AdapterForNavigationDrawerMenuThree>(
                        ModelForOrderHistory.class,
                        R.layout.cardview_item_for_order_history,
                        AdapterForNavigationDrawerMenuThree.class,
                        databaseReference
                ) {

                    @Override
                    protected void populateViewHolder(AdapterForNavigationDrawerMenuThree viewHolder, ModelForOrderHistory model, int position) {
                        viewHolder.setDetails(context, model.getTimeoforder());
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
