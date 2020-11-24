package com.example.team2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateEventPage extends AppCompatActivity {

    private EditText eDate;
    private EditText eTitle;
    private EditText eDescription;
    private Button eCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_page);

        eDate = findViewById(R.id.etDate);
        eTitle = findViewById(R.id.etTitle);
        eDescription = findViewById(R.id.etDescription);
        eCreateEvent = findViewById(R.id.btnCreateEvent);

        eCreateEvent.setOnClickListener(v -> {
            String inputDate = eDate.getText().toString();
            String inputTitle = eTitle.getText().toString();
            String inputDescription = eDescription.getText().toString();

            // TODO: Put the above info in database and display on events page
        });
    }
}