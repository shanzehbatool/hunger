package com.example.hunger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    Button callSignUp, callLogIn;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        callSignUp = findViewById(R.id.signUp);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        callLogIn = findViewById(R.id.go);

        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isUser();
            }
        });
    }

    public void isUser(){

        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        //String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        //String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        //String addressFromDB = snapshot.child(userEnteredUsername).child("address").getValue(String.class);

                        Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("userName", usernameFromDB);
                        startActivity(intent);

                    }
                    else{
                        password.setError("Wrong Password!");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No such user exists!");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
