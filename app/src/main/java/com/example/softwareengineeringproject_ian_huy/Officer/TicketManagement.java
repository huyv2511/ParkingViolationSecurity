package com.example.softwareengineeringproject_ian_huy.Officer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.RecognizerResultsIntent;
import android.util.Log;
import android.view.View;

import com.example.softwareengineeringproject_ian_huy.Adapter.IMainActivity;
import com.example.softwareengineeringproject_ian_huy.Adapter.ViewTicketDialog;
import com.example.softwareengineeringproject_ian_huy.Adapter.ticketManagementAdapter;
import com.example.softwareengineeringproject_ian_huy.Object.Ticket;
import com.example.softwareengineeringproject_ian_huy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TicketManagement extends AppCompatActivity implements ticketManagementAdapter.OnItemClickListener, IMainActivity {
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageTask mTask;
    private FirebaseFirestore db;

    private ticketManagementAdapter adapter;
    private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
    private RecyclerView mRecyclerView;
    private DocumentSnapshot mLastQueriedDocument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_management);
        mRecyclerView = findViewById(R.id.view_recyclerView);
        storage = FirebaseStorage.getInstance();

        initRecyclerView();
        getNotes();
    }
    private void getTickets(){
        FirebaseFirestore db =FirebaseFirestore.getInstance();

        CollectionReference notesCollectionRef = db
                .collection("notes");
        Query ticketQuery = null;
    }
    private void initRecyclerView(){
        if(adapter == null){
            adapter = new ticketManagementAdapter(ticketList, getApplicationContext());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }
    private void getNotes(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference notesCollectionRef = db
                .collection("notes");

        db.collection("tickets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                Ticket t = documentSnapshot.toObject(Ticket.class);
                                ticketList.add(t);
                                System.out.print(ticketList.size());
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onDeleteClick(int position) {


    }

    @Override
    public void createNewTicket(String title, String content) {

    }

    @Override
    public void updateTicket(Ticket t) {

    }

    @Override
    public void onTicketSelected(Ticket t) {
        ViewTicketDialog dialog = ViewTicketDialog.newInstance(t);
        dialog.show(getSupportFragmentManager(),"View Ticket Dialog");
    }
}