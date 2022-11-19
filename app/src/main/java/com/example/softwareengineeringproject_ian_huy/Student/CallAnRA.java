package com.example.softwareengineeringproject_ian_huy.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.softwareengineeringproject_ian_huy.Adapter.RecyclerViewAdapter.CallAnRARecyclerViewAdapter;
import com.example.softwareengineeringproject_ian_huy.Object.RA;
import com.example.softwareengineeringproject_ian_huy.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class CallAnRA extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference collectionReference;
    private CallAnRARecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_an_ra);
        db = FirebaseFirestore.getInstance();

        collectionReference = db.collection("RAs");
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = collectionReference.orderBy("name",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<RA> options = new FirestoreRecyclerOptions.Builder<RA>()
                                                            .setQuery(query,RA.class).build();

        adapter = new CallAnRARecyclerViewAdapter(options,getApplicationContext());

        recyclerView = findViewById(R.id.callRA_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}