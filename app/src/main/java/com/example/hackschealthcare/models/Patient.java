package com.example.hackschealthcare.models;
import java.util.List;

public class Patient {
    private String id; // ID consisting of Name and DOB
    private String name;
    private String dateOfBirth;
    private List<MedicalHistory> medicalHistoryList;
    private List<VoiceTranscript> voiceTranscriptList;
    private String spokenLanguage;

    // Constructors, getters, and setters

    public Patient(String id, String name, String dateOfBirth, List<MedicalHistory> medicalHistoryList, List<VoiceTranscript> voiceTranscriptList, String spokenLanguage) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.medicalHistoryList = medicalHistoryList;
        this.voiceTranscriptList = voiceTranscriptList;
        this.spokenLanguage = spokenLanguage;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<MedicalHistory> getMedicalHistoryList() {
        return medicalHistoryList;
    }

    public void setMedicalHistoryList(List<MedicalHistory> medicalHistoryList) {
        this.medicalHistoryList = medicalHistoryList;
    }

    public List<VoiceTranscript> getVoiceTranscriptList() {
        return voiceTranscriptList;
    }

    public void setVoiceTranscriptList(List<VoiceTranscript> voiceTranscriptList) {
        this.voiceTranscriptList = voiceTranscriptList;
    }

    public String getSpokenLanguage() {
        return spokenLanguage;
    }

    public void setSpokenLanguage(String spokenLanguage) {
        this.spokenLanguage = spokenLanguage;
    }
}

