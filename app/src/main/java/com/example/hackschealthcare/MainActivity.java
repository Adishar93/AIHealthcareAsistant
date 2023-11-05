package com.example.hackschealthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_call_progress);

        progressBar = findViewById(R.id.progressBar);
    }

    public void onVerifyButtonClicked(View view) {
        // Get the transcript and prescription strings from your UI elements
        String transcript = "I have a fever and cough. My head hurts. I feel tired and weak.";
        String prescription = "fexise 120mg Tablet";

        // Start the AsyncTask for the API request
        new ApiRequestTask(progressBar).execute(transcript, prescription);
    }
}
