package com.example.hackschealthcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        TextView textViewLarge = findViewById(R.id.textViewLarge);

        Button buttonPatient = findViewById(R.id.buttonPatient);
        buttonPatient.setOnClickListener(v -> {
            // Intent to start Patient Activity
        });

        Button buttonHealthcareWorker = findViewById(R.id.buttonHealthcareWorker);
        buttonHealthcareWorker.setOnClickListener(v -> {
            // Intent to start Healthcare Worker Activity
        });

        Button buttonDoctor = findViewById(R.id.buttonDoctor);
        buttonDoctor.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });


    }
}
