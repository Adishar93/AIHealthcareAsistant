package com.example.hackschealthcare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackschealthcare.adapter.ProfileAdapter;
import com.example.hackschealthcare.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private List<Patient> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.profileRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        patientList = new ArrayList<>();
        Patient p = new Patient("1","Aditya Sharma", "27 October 1998", null,null,"English");
        Patient p2 = new Patient("2","Anneketh Vij", "20 July 1998", null,null,"English");
        Patient p3 = new Patient("3","Akash Singh", "10 May 2002", null,null,"English");
        patientList.add(p);
        patientList.add(p2);
        patientList.add(p3);
        // Populate the profileList with dynamic data
        adapter = new ProfileAdapter(this, patientList);
        recyclerView.setAdapter(adapter);
    }
}
