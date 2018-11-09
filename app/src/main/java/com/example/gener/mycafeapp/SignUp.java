package com.example.gener.mycafeapp;

import android.app.ProgressDialog;
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

public class SignUp extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button id_create;
    EditText id_name;
    EditText id_email;
    EditText id_password;
    EditText id_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        id_create = findViewById(R.id.id_sihnup_create);
        id_name = findViewById(R.id.id_signup_name);
        id_email = findViewById(R.id.id_signup_email);
        id_password = findViewById(R.id.id_signup_password);
        id_phone = findViewById(R.id.id_signup_phone);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("User");

        id_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Ожидание...");
                progressDialog.show();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (id_name.getText().toString().equals("") || id_email.getText().toString().equals("") ||
                                id_password.getText().toString().equals("") || id_phone.getText().toString().equals("")){
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Заполните все данные", Toast.LENGTH_SHORT).show();
                        }else{
                            if (dataSnapshot.child(id_phone.getText().toString()).exists()){
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this, "Номер телефона занят", Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                User user = new User(id_name.getText().toString(), id_email.getText().toString(),
                                        id_password.getText().toString(), id_phone.getText().toString());
                                databaseReference.child(user.getPhone().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
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
