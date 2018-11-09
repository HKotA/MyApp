package com.example.gener.mycafeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button id_signin;
    EditText id_phone;
    EditText id_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        id_signin = findViewById(R.id.id_signin_signin);
        id_phone = findViewById(R.id.id_signin_phone);
        id_password = findViewById(R.id.id_signin_password);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("User");

        id_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Ожидание...");
                progressDialog.show();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (id_phone.getText().toString().equals("") && id_password.getText().toString().equals("")){
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "Введите данные", Toast.LENGTH_SHORT).show();
                        }else{
                            if (dataSnapshot.child(id_phone.getText().toString()).exists()){
                                progressDialog.dismiss();
                                User user = dataSnapshot.child(id_phone.getText().toString()).getValue(User.class);
                                if (user.getPassword().equals(id_password.getText().toString())){

                                    CurrentUser.currentUser = user;

                                    Toast.makeText(SignIn.this, "Вход выполнен", Toast.LENGTH_SHORT).show();
                                    
                                    Intent intent = new Intent(SignIn.this, Menu.class);
                                    startActivity(intent);
                                }else{
                                    progressDialog.dismiss();
                                    Toast.makeText(SignIn.this, "Ошибка входа", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(SignIn.this, "Пользовтель не найден", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
