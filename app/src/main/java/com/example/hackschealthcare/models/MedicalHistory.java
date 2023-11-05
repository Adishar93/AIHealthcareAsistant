package com.example.hackschealthcare.models;

public class MedicalHistory {
    private String mhId;
    private String dateReported;
    private boolean positiveOutcome;
    private String notes;

    // Constructors, getters, and setters

    public MedicalHistory(String mhId, String dateReported, boolean positiveOutcome, String notes) {
        this.mhId = mhId;
        this.dateReported = dateReported;
        this.positiveOutcome = positiveOutcome;
        this.notes = notes;
    }

    // Getter and Setter methods
    public String getMhId() {
        return mhId;
    }

    public void setMhId(String mhId) {
        this.mhId = mhId;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public boolean isPositiveOutcome() {
        return positiveOutcome;
    }

    public void setPositiveOutcome(boolean positiveOutcome) {
        this.positiveOutcome = positiveOutcome;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

