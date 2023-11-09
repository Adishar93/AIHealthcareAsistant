package com.example.hackschealthcare;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequestTask extends AsyncTask<String, Void, String> {
    private final String apiEndpoint = "https://verify-prescription-service-ire7dc72sa-uc.a.run.app/verify-prescription";
    private final ProgressBar progressBar;
    private final TextView text;

    public ApiRequestTask(ProgressBar progressBar, TextView text) {
        this.progressBar = progressBar;
        this.text = text;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(apiEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(500000000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonInputString = "{\"transcript\": \"" + params[0] + "\", \"prescription\": \"" + params[1] + "\"}";
            System.out.println("JSON XXXX:"+jsonInputString);

            // Send the POST request with JSON data
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(jsonInputString.getBytes("UTF-8"));
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } else {
                return null; // Handle the error
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(ProgressBar.GONE);

        if (result != null) {
            // Process the API response here
            //Toast.makeText(progressBar.getContext(), "API Response: " + result, Toast.LENGTH_LONG).show();
            text.setText(result);
        } else {
            // Handle the error
            System.out.println("API error");
            Toast.makeText(progressBar.getContext(), "API Request Failed", Toast.LENGTH_SHORT).show();
        }
    }
}

