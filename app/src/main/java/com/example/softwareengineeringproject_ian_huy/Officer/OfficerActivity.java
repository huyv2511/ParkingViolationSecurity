package com.example.softwareengineeringproject_ian_huy.Officer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.softwareengineeringproject_ian_huy.R;

public class OfficerActivity extends AppCompatActivity {
    Button makeATicket, ticketManagement, carLookUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer);

        ticketManagement = findViewById(R.id.btn_ticketManagement);
        carLookUp = findViewById(R.id.btn_CarLookUp);
        makeATicket = findViewById(R.id.btn_makeATicket);

        ticketManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OfficerActivity.this, TicketManagement.class);
                startActivity(i);
            }
        });

        carLookUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OfficerActivity.this, CarLookUp.class);
                startActivity(i);
            }
        });

        makeATicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OfficerActivity.this, MakeATicket.class);
                startActivity(i);
            }
        });
    }
}