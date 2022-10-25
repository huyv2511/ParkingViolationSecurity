package com.example.softwareengineeringproject_ian_huy.Student;

import androidx.appcompat.app.AppCompatActivity;
import com.example.softwareengineeringproject_ian_huy.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentPage extends AppCompatActivity {
    Button callRA_btn, SOS_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        callRA_btn = findViewById(R.id.callRA_btn);
        SOS_btn = findViewById(R.id.SOS_btn);

        SOS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SOSCall();
            }
        });

        callRA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentPage.this,CallAnRA.class);
                startActivity(i);
            }
        });
    }
    public void SOSCall(){
        //makeAnSOSCall
    }
}