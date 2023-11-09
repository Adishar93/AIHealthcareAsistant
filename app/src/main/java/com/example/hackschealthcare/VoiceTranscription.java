package com.example.hackschealthcare;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;

public class VoiceTranscription extends AppCompatActivity {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;

    private Button recordButton;
    private TextView transcriptTextView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_voice_transcription);

        recordButton = findViewById(R.id.recordButton);
        transcriptTextView = findViewById(R.id.transcriptTextView);
        progressBar = findViewById(R.id.progressBar);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        }

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRecording) {
                    startRecording();
                    recordButton.setText("Stop");
                } else {
                    stopRecording();
                    recordButton.setText("Record");
                    String transcript = sendAudioForTranscription();
                    transcriptTextView.setText(transcript);
                }
            }
        });
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.OGG);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.OPUS);
        mediaRecorder.setAudioSamplingRate(16000);
        String filePath = getExternalCacheDir().getAbsolutePath() + "/audio.ogg";
        mediaRecorder.setOutputFile(filePath);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
        isRecording = false;
    }

    private String sendAudioForTranscription() {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(getResources().openRawResource(R.raw.service_account));
            CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);


            // Create SpeechSettings with the credentials
            SpeechSettings speechSettings = SpeechSettings.newBuilder()
                    .setCredentialsProvider(credentialsProvider)
                    .build();

            try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {
                String audioFilePath = getExternalCacheDir().getAbsolutePath() + "/audio.ogg";

                // Create the request for asynchronous speech recognition
                RecognitionConfig config = RecognitionConfig.newBuilder()
                        .setEncoding(RecognitionConfig.AudioEncoding.OGG_OPUS)
                        .setSampleRateHertz(16000)
                        .setLanguageCode("en-US")  // Language code (e.g., English)
                        .build();
                RecognitionAudio audio = RecognitionAudio.newBuilder()
                        .setContent(ByteString.readFrom(new FileInputStream(audioFilePath)))
                        .build();

                RecognizeRequest request = RecognizeRequest.newBuilder()
                        .setConfig(config)
                        .setAudio(audio)
                        .build();

                // Perform asynchronous speech recognition
                RecognizeResponse response = speechClient.recognize(request);
                StringBuilder transcriptBuilder = new StringBuilder();

                for (SpeechRecognitionResult result : response.getResultsList()) {
                    // Append the transcription to the result
                    transcriptBuilder.append(result.getAlternatives(0).getTranscript()).append(" ");
                }

                return transcriptBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Transcription error: " + e.getMessage();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
            }
        }
    }

    public void onVerifyButtonClicked(View view) {
        // Get the transcript and prescription strings from your UI elements
        String transcript = transcriptTextView.getText().toString();
        System.out.println("Transcript:"+transcript);
        TextInputEditText prescription = findViewById(R.id.prescriptionEditText);
        String prescriptionTxt = prescription.getText().toString();
        System.out.println("Prescription:"+prescriptionTxt);
        TextView resultText = findViewById(R.id.ResultTextView);

        // Start the AsyncTask for the API request
        new ApiRequestTask(progressBar,resultText).execute(transcript, prescriptionTxt);
    }
}
