package com.example.hunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    Button callLogIn;

    TextInputLayout regName, regUsername, regEmail, regAddress, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main3);

        callLogIn = findViewById(R.id.reg_login_btn);

        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

            regName = findViewById(R.id.reg_name);
            regUsername = findViewById(R.id.reg_username);
            regEmail = findViewById(R.id.reg_email);
            regAddress = findViewById(R.id.reg_address);
            regPhoneNo = findViewById(R.id.reg_phoneNo);
            regPassword = findViewById(R.id.reg_password);
            regBtn = findViewById(R.id.reg_btn);
            //regToLoginBtn = findViewById(R.id.reg_login_btn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                //Get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String address = regAddress.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                UserHelperClass helperClass = new UserHelperClass(name, username, email, address, phoneNo, password);
                reference.child(username).setValue(helperClass);
                //reference.setValue(helperClass);
                //reference.setValue("shanzeh");
            }
        });

    }

}

