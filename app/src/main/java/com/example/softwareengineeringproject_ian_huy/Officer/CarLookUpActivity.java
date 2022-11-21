package com.example.softwareengineeringproject_ian_huy.Officer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.softwareengineeringproject_ian_huy.Adapter.RecyclerViewAdapter.CarLookUpViewAdapter;
import com.example.softwareengineeringproject_ian_huy.Adapter.DialogAdapter.ViewCarInfoDialog;
import com.example.softwareengineeringproject_ian_huy.Object.Car;
import com.example.softwareengineeringproject_ian_huy.Object.Student;
import com.example.softwareengineeringproject_ian_huy.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CarLookUpActivity extends AppCompatActivity {
    private static final String TAG = "CarLookUpActivity";
    //keep in mind we only need to use license plate to search for cars
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private CollectionReference collectionReference;
    private CarLookUpViewAdapter carLookUpViewAdapter;
    private RecyclerView recyclerView;

    private FirestoreRecyclerOptions<Car> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_look_up);
        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Cars");
        setUpRecyclerView();
    }

    private void openDialog(String name,  String phoneNum, String email) {
        ViewCarInfoDialog dialog = new ViewCarInfoDialog(name,phoneNum,email);
        dialog.show(getSupportFragmentManager(),"View Car Owner Info");
    }


    private void setUpRecyclerView(){
        Query query = collectionReference.orderBy("licensePlate",Query.Direction.DESCENDING);
        options = new FirestoreRecyclerOptions.Builder<Car>()
                                                    .setQuery(query,Car.class)
                                                        .build();
        carLookUpViewAdapter = new CarLookUpViewAdapter(options);
        recyclerView = findViewById(R.id.carLookUp_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carLookUpViewAdapter);

        carLookUpViewAdapter.setOnItemClickListener(new CarLookUpViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Car car = documentSnapshot.toObject(Car.class);
                Toast.makeText(CarLookUpActivity.this, "Owner Email is " + car.getuserEmail(), Toast.LENGTH_SHORT).show();
                Log.e(TAG,"Owner Email is " + car.getuserEmail());
                if(car.getuserEmail() != null ){
                    //if there is an email
                    //query to find the user info
                     db.collection("Users").whereEqualTo("userEmail",car.getuserEmail())
                                                                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                                                                        Log.e(TAG, "List size is " +dsList.size());
                                                                        Student s = dsList.get(0).toObject(Student.class);
                                                                        openDialog(s.getFullName(), s.getPhoneNumber(),s.getuserEmail());
                                                                        for(DocumentSnapshot ds:dsList){
                                                                            Student s1 = ds.toObject(Student.class);
                                                                            Log.e(TAG,"Student is " + s.getuserEmail());
                                                                            Log.e(TAG,"Student is " + s.getFullName());
                                                                            Log.e(TAG,"Student is " + s.getPhoneNumber());
                                                                        }
                                                                    }
                                                                });
                }
                //.collection("Users")
                //.where("userEmail", "==", "hvan01611@fontbonne.edu")
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carLookUpViewAdapter.startListening();

    }
   

    @Override
    protected void onStop() {
        super.onStop();
        carLookUpViewAdapter.stopListening();
    }


}