package com.example.gener.mycafeapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static Context context;

    TextView UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Встановлюю тулбар і добавляю тайтл+лого
        Toolbar toolbar = findViewById(R.id.id_menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);

        //Встановлюю і синхронізую роботу кнопки-бургера
        DrawerLayout menu = findViewById(R.id.id_menu_drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                menu,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        menu.addDrawerListener(toggle);
        toggle.syncState();

        //Отримую ссилку на меню-хедер і встановлюю ім'я користувача
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        UserName = headerView.findViewById(R.id.id_nav_header_name);
        UserName.setText("Привіт, " + CurrentUser.currentUser.getName());

        //Добавляє можливість вибору пунктів меню
        navigationView.setNavigationItemSelectedListener(this);

        //Дефолт - перший пункт меню
        Fragment fragment = new NavigationDrawer_Menu_One();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.id_menu_framelayout, fragment);
        ft.commit();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        Fragment fragment = null;

        switch(id){
            case R.id.id_nav_menu_1:
                fragment = new NavigationDrawer_Menu_One();
                break;
            case R.id.id_nav_menu_2:
                fragment = new NavigationDrawer_Menu_Two();
                break;
            case R.id.id_nav_menu_3:
                fragment = new NavigationDrawer_Menu_Three();
                break;
            case R.id.id_nav_menu_4:
                fragment = new NavigationDrawer_Menu_Four();
                break;
            default:
                fragment = new NavigationDrawer_Menu_One();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.id_menu_framelayout, fragment);
            ft.commit();
        }

        //Закриває бокове меню після вибору пункта
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.id_menu_drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        //При натисненні кнопки назад - закриває бокове меню
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.id_menu_drawerlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
