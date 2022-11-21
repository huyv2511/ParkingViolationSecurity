package com.example.softwareengineeringproject_ian_huy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softwareengineeringproject_ian_huy.Object.Student;
import com.example.softwareengineeringproject_ian_huy.Officer.OfficerActivity;
import com.example.softwareengineeringproject_ian_huy.Student.StudentPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    EditText username_et, password_et;
    Button loginBtn,signUpBtn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        username_et = findViewById(R.id.userName);
        password_et = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.signup_btn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

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

    private void userLogin() {
        String userName = username_et.getText().toString();
        String password = password_et.getText().toString();

        if(userName.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill in your info", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userName.compareTo("admin") ==0  && password.compareTo("fontbonne") == 0){
            Intent i = new Intent(LoginActivity.this, OfficerActivity.class);
            startActivity(i);
            finish();
            return;
        }

        mAuth.signInWithEmailAndPassword(userName,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //redirect to the student activity
                            Intent i = new Intent(LoginActivity.this, StudentPage.class);
                            startActivity(i);
                            finish();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}