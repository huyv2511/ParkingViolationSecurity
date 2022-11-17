package com.example.softwareengineeringproject_ian_huy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.softwareengineeringproject_ian_huy.Object.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText userNameET, userPassword1_et, userPassword2_et, userEmail_et, userPhoneNumber_et,fullName_et;
    Button signUpBtn;

    private FirebaseAuth mAuth;
    FirebaseFirestore db;

    private String url = "https://10.102.162.136/phpServerFiles/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userNameET = findViewById(R.id.userName_et);
        userPassword1_et = findViewById(R.id.password_et);
        userPassword2_et = findViewById(R.id.password2_et);
        userEmail_et = findViewById(R.id.fontbonneEmail_et);
        userPhoneNumber_et = findViewById(R.id.phoneNumber_et);
        fullName_et = findViewById(R.id.fullName_et);
        signUpBtn = findViewById(R.id.signup_btn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpNewUser();
            }
        });
    }

    private void signUpNewUser(){
        ArrayList<String> errors = new ArrayList<>();
        //username needs to be 8-30 and has letter and numbers in it
        //passwords to match each other. length is at least 8 characters
        //email needs to be fontbonne email
        //phone number needs to be at least 9 to 10 characters
        //full name no check
        String userName = userNameET.getText().toString().trim();
        String userPassword = userPassword1_et.getText().toString().trim();
        String userPassword1 = userPassword2_et.getText().toString().trim();
        String userEmail = userEmail_et.getText().toString().trim();
        String phoneNumber = userPhoneNumber_et.getText().toString().trim();
        String fullName = fullName_et.getText().toString().trim();

        if(userName.isEmpty() || userPassword.isEmpty() || userPassword1.isEmpty() || userEmail.isEmpty() ||
        phoneNumber.isEmpty() || fullName.isEmpty()){
            errors.add("Please fill up all the empty blank");
        }
        //checking username using regular expression
        String pattern = "^[A-Za-z][A-Za-z0-9_]{8,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(userName);
        if(!m.find()){
            errors.add("Your username needs to be between 8-30 characters with letters and numbers");
        }
        //check password
        if(userPassword.compareTo(userPassword1) != 0 || userPassword.length() <8){
            errors.add("Invalid password or password doesn't match. Your password needs to be at least 8 characters");
        }
        //checking email using regular expression
        pattern = "^[A-Za-z0-9._%+-]{7,30}+@fontbonne\\.edu$";
        r = Pattern.compile(pattern);
        m = r.matcher(userEmail);
        if(!m.find()){
            errors.add("Invalid email. Your email needs to have ");
        }
        if(phoneNumber.length()!=9 && phoneNumber.length()!=10 ){
            errors.add("Invalid phone number. It needs to 9 or 10 numbers");
        }
        //if there is no error we proceed to push the users into the database
        if(errors.size() == 0 ){
            Toast.makeText(this, "Posting new data", Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.i("Firebase","Successfully registered!");
                                UUID uuid = UUID.randomUUID();
                                FirebaseUser user = mAuth.getCurrentUser();
                                String userID = user.getUid();
                                Student s = new Student(
                                        userID,userName,userPassword,userEmail,phoneNumber,fullName
                                );
                                DocumentReference newStuRef = db.collection("Users").document();
                                newStuRef.set(s)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                Log.i("Firebase","Successfully adding users to database");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull @NotNull Exception e) {
                                                Log.i("Firebase","Failed adding users to database");
                                            }
                                        });
//                                FirebaseDatabase.getInstance().getReference("Users")
//                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                        .setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//
//                                    }
//                                });
//                                Map<String, String> map = new HashMap<>();
//                                map.put("userID", s.getUserId());
//                                map.put("userName",s.getUserName());
//                                map.put("userEmail",s.getFontbonneEmail());
//                                map.put("phoneNumber",s.getPhoneNumber());
//                                map.put("fullName",s.getFullName());
//                                map.put("userType","Student");
//                                db.collection("Users")
//                                        .add(map)
//                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                            @Override
//                                            public void onSuccess(DocumentReference documentReference) {
//                                                Log.i("Firebase","Successfully adding users to database");
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Log.i("Firebase","Successfully adding users to database");
//                                    }
//                                });
                            }
                            else{
                                Toast.makeText(SignUp.this, "Unsuccessfully register the users", Toast.LENGTH_SHORT).show();
                                Log.i("Firebase","Something is wrong with registering!");
                                Intent i = new Intent(SignUp.this,LoginActivity.class);
                                startActivity(i);
                            }
                        }
                    });
        }else{
            for(int i=0;i<errors.size();i++){
                Toast.makeText(this, errors.get(i), Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void signUp(){
        Toast.makeText(this, "Valid sign up", Toast.LENGTH_SHORT).show();
    }

}