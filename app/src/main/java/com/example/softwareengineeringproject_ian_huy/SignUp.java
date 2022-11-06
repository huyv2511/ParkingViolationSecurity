package com.example.softwareengineeringproject_ian_huy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softwareengineeringproject_ian_huy.JSONhelper.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText userNameET, userPassword1_et, userPassword2_et, userEmail_et, userPhoneNumber_et,fullName_et;
    Button signUpBtn;
    JSONParser jsonParser;
    private String url = "https://10.102.162.136/phpServerFiles/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        jsonParser = new JSONParser();

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
        String userName = userNameET.getText().toString();
        String userPassword = userPassword1_et.getText().toString();
        String userPassword1 = userPassword2_et.getText().toString();
        String userEmail = userEmail_et.getText().toString();
        String phoneNumber = userPhoneNumber_et.getText().toString();
        String fullName = fullName_et.getText().toString();

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

            AttemptLogin login = new AttemptLogin();
            Toast.makeText(this, "Posting new data", Toast.LENGTH_SHORT).show();
            login.execute(userName,userPassword,fullName,phoneNumber,userEmail);
        }else{
            for(int i=0;i<errors.size();i++){
                Toast.makeText(this, errors.get(i), Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void signUp(){
        Toast.makeText(this, "Valid sign up", Toast.LENGTH_SHORT).show();
    }
    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            String userName = args[0];
            String password = args[1];
            String fullName = args[2];
            String phoneNumber = args[3];
            String email = args[4];

            ArrayList<NameValuePair> pair = new ArrayList<>();
            pair.add(new BasicNameValuePair("userName",userName));
            pair.add(new BasicNameValuePair("password",userName));
            pair.add(new BasicNameValuePair("fullName",userName));
            pair.add(new BasicNameValuePair("phoneNumber",userName));
            pair.add(new BasicNameValuePair("fontbonneEmail",userName));

            JSONObject json = new JSONParser().makeHttpRequest(url, "POST", pair);
//            Toast.makeText(SignUp.this, "JSON something", Toast.LENGTH_SHORT).show();
            return json;
        }

        protected void onPostExecute(JSONObject result){
            try{
                if(result!= null){
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            }catch(JSONException e ){
                e.printStackTrace();
            }
        }
    }
}