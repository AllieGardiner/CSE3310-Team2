package com.example.team2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventPage extends AppCompatActivity {

    private EditText eDate;
    private EditText eName;
    private EditText eInfo;
    private Button eCreateEvent;
    DatabaseReference reff;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_page);

        eDate = findViewById(R.id.etDate);
        eName = findViewById(R.id.etName);
        eInfo = findViewById(R.id.etInfo);
        eCreateEvent = findViewById(R.id.btnCreateEvent);
        event = new Event();
        reff = FirebaseDatabase.getInstance().getReference().child("Event");

        eCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString().trim();
                String inputDate = eDate.getText().toString().trim();
                String inputInfo = eInfo.getText().toString().trim();

                event.setName(inputName);
                event.setDate(inputDate);
                event.setInfo(inputInfo);
                reff.push().setValue(event);
                Toast.makeText(CreateEventPage.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                // TODO: Put the above info in database and display on events page
            }
        });
    }
}