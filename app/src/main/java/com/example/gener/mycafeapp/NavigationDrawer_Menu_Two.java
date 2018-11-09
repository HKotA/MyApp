package com.example.gener.mycafeapp;


import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer_Menu_Two extends Fragment {

    private SQLiteDatabase sqLiteDatabase;
    private AdapterForCart adapterForCart;

    FloatingActionButton floatingActionButton;

    TextView totalPrice;

    String currency = " грн.  ";
    String amount = " шт.";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference2;

    public NavigationDrawer_Menu_Two() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_navigation_drawer__menu__two, container, false);

        floatingActionButton = view.findViewById(R.id.id_nd_menu_two_fab);

        final Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/12231.ttf");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Order");

        databaseReference2 = firebaseDatabase.getReference("OrderHistory");

        totalPrice = view.findViewById(R.id.id_nd_menu_two_totalprice);
        totalPrice.setTypeface(font);

        final CartDatabase cartDatabase = new CartDatabase(getActivity());
        sqLiteDatabase = cartDatabase.getWritableDatabase();

        RecyclerView recyclerView = view.findViewById(R.id.id_nd_menu_two_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterForCart = new AdapterForCart(getActivity(), getAllItems());
        recyclerView.setAdapter(adapterForCart);

        if (totalPrice() == 0) {
            totalPrice.setText("  Пусто  ");
            floatingActionButton.setEnabled(false);
//            view.setBackgroundResource(R.drawable.cart_logo);
        } else {
            totalPrice.setText("  Загальна ціна: " + String.valueOf(totalPrice()) + currency);
        }


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long) viewHolder.itemView.getTag());

                if (totalPrice() == 0) {
                    totalPrice.setText("  Пусто  ");
                    floatingActionButton.setEnabled(false);
//                    view.setBackgroundResource(R.drawable.cart_logo);
                } else {
                    totalPrice.setText("  Загальна ціна: " + String.valueOf(totalPrice()) + currency);
                }
            }
        }).attachToRecyclerView(recyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                View dialogView = getLayoutInflater().inflate(R.layout.dialog_for_order, null);

                final EditText address1 = dialogView.findViewById(R.id.id_dialog_for_order_address1);
                final EditText address2 = dialogView.findViewById(R.id.id_dialog_for_order_address2);
                final EditText address3 = dialogView.findViewById(R.id.id_dialog_for_order_address3);
                address1.setTypeface(font);
                address2.setTypeface(font);
                address3.setTypeface(font);

                address1.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                getActivity().getWindow().setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                TextView fullprice = dialogView.findViewById(R.id.id_dialog_for_order_fullprice);
                fullprice.setTypeface(font);
                fullprice.setText("  Загальна ціна: " + String.valueOf(totalPrice()) + currency);

                Button makeAnOrder = dialogView.findViewById(R.id.id_dialog_for_order_makeanorder);
                makeAnOrder.setTypeface(font);

                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                dialog.show();

                makeAnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Date currentTime = Calendar.getInstance().getTime();

                                //DB_Order
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("Name").setValue(CurrentUser.currentUser.getName());
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("Phone").setValue(CurrentUser.currentUser.getPhone());
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("Price").setValue(("Загальна ціна: " + String.valueOf(totalPrice()) + currency));

                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("Street").setValue(address1.getText().toString());
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("House").setValue(address2.getText().toString());
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("Apartment").setValue(address3.getText().toString());
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("timeoforder").setValue(String.valueOf(currentTime));
                                databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child("true").setValue("false");

                                //Order
                                databaseReference2.child(String.valueOf(currentTime)).child("Name").setValue(CurrentUser.currentUser.getName());
                                databaseReference2.child(String.valueOf(currentTime)).child("Phone").setValue(CurrentUser.currentUser.getPhone());
                                databaseReference2.child(String.valueOf(currentTime)).child("Price").setValue(("Загальна ціна: " + String.valueOf(totalPrice()) + currency));

                                databaseReference2.child(String.valueOf(currentTime)).child("Street").setValue(address1.getText().toString());
                                databaseReference2.child(String.valueOf(currentTime)).child("House").setValue(address2.getText().toString());
                                databaseReference2.child(String.valueOf(currentTime)).child("Apartment").setValue(address3.getText().toString());
                                databaseReference2.child(String.valueOf(currentTime)).child("TimeOfOrder").setValue(String.valueOf(currentTime));
                                databaseReference2.child(String.valueOf(currentTime)).child("true").setValue("false");

                                Cursor cursor = getAllItems();

                                if (!cursor.moveToPosition(0)) {
                                    Toast.makeText(getActivity(), "Cursor == null", Toast.LENGTH_LONG).show();
                                    return;
                                } else {
                                    cursor.moveToPosition(0);

                                    for (int i = 0; i < cursor.getCount(); i++) {
                                        String Name = cursor.getString(cursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_NAME));
                                        String Amount = cursor.getString(cursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_AMOUNT));
                                        String Price = cursor.getString(cursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_PRICE));

                                        //DB_Order
                                        databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child(i + 1 + Name).setValue(amount);
                                        databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child(i + 1 + Name).child("Ціна товару").setValue(Price + currency);
                                        databaseReference.child(CurrentUser.currentUser.getPhone()).child(String.valueOf(currentTime)).child(i + 1 + Name).child("Кількість").setValue(Amount + amount);

                                        //Order
                                        databaseReference2.child(String.valueOf(currentTime)).child(i + 1 + Name).setValue(amount);
                                        databaseReference2.child(String.valueOf(currentTime)).child(i + 1 + Name).child("Ціна товару").setValue(Price + currency);
                                        databaseReference2.child(String.valueOf(currentTime)).child(i + 1 + Name).child("Кількість").setValue(Amount + amount);

                                        cursor.moveToNext();
                                    }

                                    cartDatabase.deleteDatabase(sqLiteDatabase);
                                    adapterForCart.swapCursor(getAllItems());

                                    totalPrice.setText("  Пусто  ");
                                    floatingActionButton.setEnabled(false);
//                                    view.setBackgroundResource(R.drawable.cart_logo);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        Toast.makeText(getActivity(), "Замовлення відправлено", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
            }
        });

        return view;
    }

    private int totalPrice() {
        List<String> list = new ArrayList<>();
        Cursor cursor = getAllItems();

        int error = 0;
        int returnTotalPrice = 0;

        if (!cursor.moveToPosition(0)) {
            return error;
        } else {
            cursor.moveToPosition(0);
            for (int i = 0; i < cursor.getCount(); i++) {
                String name = cursor.getString(cursor.getColumnIndex(CartInfo.CartInfoDatabase.COLUMN_PRICE));
                list.add(name);
                cursor.moveToNext();

                int totalPrice = 0;

                for (String item : list) {
                    totalPrice = totalPrice + Integer.parseInt(item);
                }

                returnTotalPrice = totalPrice;
            }
        }

        return returnTotalPrice;
    }

    private Cursor getAllItems() {
        return sqLiteDatabase.query(CartInfo.CartInfoDatabase.TABLE_NAME,
                null, null, null, null, null,
                CartInfo.CartInfoDatabase.COLUMN_TIMESTAMP + " DESC ");
    }

    private void removeItem(long id) {
        sqLiteDatabase.delete(CartInfo.CartInfoDatabase.TABLE_NAME,
                CartInfo.CartInfoDatabase._ID + "=" + id, null);
        adapterForCart.swapCursor(getAllItems());
    }

}
