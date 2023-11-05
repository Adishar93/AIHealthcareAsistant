package com.example.hackschealthcare.models;
public class VoiceTranscript {
    private String vtId;
    private String dateIssued;
    private String doctorId;

    // Constructors, getters, and setters

    public VoiceTranscript(String vtId, String dateIssued, String doctorId) {
        this.vtId = vtId;
        this.dateIssued = dateIssued;
        this.doctorId = doctorId;
    }

    // Getter and Setter methods
    public String getVtId() {
        return vtId;
    }

    public void setVtId(String vtId) {
        this.vtId = vtId;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}

