package com.example.gener.mycafeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer_Menu_Five extends Fragment {

    TextView text;
    Button send;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    public NavigationDrawer_Menu_Five() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer__menu__five, container, false);

        text = view.findViewById(R.id.id_nd_menu_five_text);
        send = view.findViewById(R.id.id_nd_menu_five_send);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Response");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String responce = text.getText().toString();
                final Date currentTime = Calendar.getInstance().getTime();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        databaseReference.child(String.valueOf(currentTime)).setValue(responce);
                        text.setText("");
                        Toast.makeText(getActivity(), "Відгук надіслано", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }

}
