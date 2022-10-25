package com.example.softwareengineeringproject_ian_huy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softwareengineeringproject_ian_huy.Officer.OfficerActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username_et, password_et;
    Button loginBtn,signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_et = findViewById(R.id.userName);
        password_et = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.signup_btn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username_et.getText().toString();
                String password = password_et.getText().toString();

                if(userName.compareTo("PParker1100") == 0 && password.compareTo("NoobSlayer69!")==0){
                    //then moving on to officer activity
                    Intent i = new Intent(LoginActivity.this, OfficerActivity.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(LoginActivity.this, "Incorrect password or username! Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signUpBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}