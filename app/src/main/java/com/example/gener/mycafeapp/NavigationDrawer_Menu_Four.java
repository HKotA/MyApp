package com.example.gener.mycafeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer_Menu_Four extends Fragment implements OnMapReadyCallback{


    public NavigationDrawer_Menu_Four() {
        // Required empty public constructor
    }

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer__menu__four, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.id_nd_menu_four_map);
        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(49.55122241, 25.60106704);
        mMap.addMarker(new MarkerOptions().position(sydney).title("McDonald’s"));

        float zoomLevel = 15.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
