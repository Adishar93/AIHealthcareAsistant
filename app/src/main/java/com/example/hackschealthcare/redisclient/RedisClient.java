package com.example.hackschealthcare.redisclient;
import android.os.Handler;
import android.os.Looper;

import com.example.hackschealthcare.models.Patient;
import com.google.gson.Gson;

import java.util.ArrayList;

import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;

public class RedisClient {
        private static final Gson gson = new Gson();

        public static void test() {
            // Initialize Jedis and connect to your Redis server
                    Thread backgroundThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // Once the network operation is complete, update the UI on the main thread
                                Jedis jedis = new Jedis("redis-17805.c278.us-east-1-4.ec2.cloud.redislabs.com", 17805);
                                jedis.auth("jl6kewyubfOHRvzDPBNtNcXzJzZgI0FZ");
                                Handler mainHandler = new Handler(Looper.getMainLooper());
                                Patient patient = new Patient("1", "John Doe", "1990-01-15", new ArrayList<>(), new ArrayList<>(), "English");

                                // Serialize the Patient object to JSON or any other format you prefer
                                String patientJson = serializeToJSON(patient);

                                // Store the patient data in Redis with a key
                                jedis.set("patient:1", patientJson);

                                // Retrieve the patient data from Redis
                                String storedPatientJson = jedis.get("patient:1");

                                // Deserialize the stored data back into a Patient object
                                Patient storedPatient = deserializeFromJSON(storedPatientJson, Patient.class);
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Update the UI with the result
                                        // This code will run on the main thread
                                        // Create a new Patient object


                                        // Use the storedPatient object as needed
                                        System.out.println("Patient ID: " + storedPatient.getId());
                                        System.out.println("Patient Name: " + storedPatient.getName());
                                        System.out.println("Patient Age: " + storedPatient.getDateOfBirth());

                                        // Close the Jedis connection
                                        jedis.close();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                                // Handle any exceptions
                            }
                        }
                    });

                    // Start the background thread
                    backgroundThread.start();
        }

        // Implement methods to serialize/deserialize the Patient object to/from JSON
        private static String serializeToJSON(Patient patient) {
            return gson.toJson(patient);
        }

        private static <T> T deserializeFromJSON(String json, Class<T> classOfT) {
            return gson.fromJson(json, classOfT);
        }
}
