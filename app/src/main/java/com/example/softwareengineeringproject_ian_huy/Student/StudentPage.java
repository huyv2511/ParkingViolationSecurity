package com.example.softwareengineeringproject_ian_huy.Student;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softwareengineeringproject_ian_huy.Adapter.DialogAdapter.RegisterNewCarDialog;
import com.example.softwareengineeringproject_ian_huy.Object.Car;
import com.example.softwareengineeringproject_ian_huy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentPage extends AppCompatActivity implements RegisterNewCarDialog.registerNewCarListener {
    Button callRA_btn, SOS_btn,carRes_btn;
    private FirebaseFirestore db;
    private static final String TAG = "StudentPage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        db = FirebaseFirestore.getInstance();

        callRA_btn = findViewById(R.id.callRA_btn);


        callRA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentPage.this,CallAnRA.class);
                startActivity(i);
            }
        });
        carRes_btn = findViewById(R.id.carRes_btn);
        carRes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        RegisterNewCarDialog dialog = new RegisterNewCarDialog();
        dialog.show(getSupportFragmentManager(),"Register New Car");
    }

    public void SOSCall(){
        //makeAnSOSCall
    }

    @Override
    public void newData(String newCarState, String newCarLicensePlate, String newCarModel, String newCarColor) {
        //this handle new car data
        ArrayList<String> errorList = new ArrayList<>();
        if(newCarState == null){
            errorList.add("Car State is inaccurate");
        }
        if(newCarLicensePlate.length() <6 && newCarLicensePlate.length() >8) errorList.add("Invalid License Plate");
        if(newCarModel == null) errorList.add("Invalid Car Model");
        if(newCarColor == null) errorList.add("Invalid Car Color");

        if(errorList.size() > 0 ){
            for (int i=0;i<errorList.size();i++){
                Toast.makeText(this, errorList.get(i), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        DocumentReference docRef = db.collection("Cars")
                                        .document();

        Car car = new Car(docRef.getId(),email, newCarState,newCarLicensePlate,newCarModel, newCarColor);
        System.out.println(car.getuserEmail());
        docRef.set(car)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                        Log.e(TAG, "Successfully adding the car");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Log.e(TAG, "Failed adding the car" + e.getMessage());
                    }
                });

        Log.e(TAG,"hi " + newCarState + newCarLicensePlate + newCarModel  + email);
    }
}