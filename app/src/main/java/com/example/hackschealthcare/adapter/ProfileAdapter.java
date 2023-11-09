package com.example.hackschealthcare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackschealthcare.VoiceTranscription;
import com.example.hackschealthcare.R;
import com.example.hackschealthcare.models.Patient;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private final List<Patient> patientList;
    private static Context context;

    public ProfileAdapter(Context context, List<Patient> profileList) {
        this.patientList = profileList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.uniqueId.setText(patient.getName());
        //holder.profileImage.setImageResource(profile.getProfileImage());
        holder.nameAge.setText(patient.getDateOfBirth());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    static class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView uniqueId, nameAge;
        ImageView profileImage;


        ProfileViewHolder(View view) {
            super(view);
            uniqueId = view.findViewById(R.id.card_unique_id);
            profileImage = view.findViewById(R.id.card_profile_image);
            nameAge = view.findViewById(R.id.card_name_age);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent detailIntent = new Intent(context, VoiceTranscription.class);
            context.startActivity(detailIntent);
        }
    }

}
