package com.example.cardiacrecorder;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

public class EachData {
    private final long timestamp;

    @NonNull
    private final String date; //dd-mm-yyyy

    @NonNull
    private final String time; // hh:mma

    private final int sysPressure; // mm Hg - non-negative
    private final int dysPressure; // mm Hg - non-negative
    private final int heartRate; // beats per minute non-negative
    @Nullable
    private final String comment;

    public EachData(long timestamp, @NonNull String date, @NonNull String time, int sysPressure, int dysPressure, int heartRate, @Nullable String comment) {
        this.timestamp = timestamp;
        this.date = date;
        this.time = time;
        this.sysPressure = sysPressure;
        this.dysPressure = dysPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public int getSysPressure() {
        return sysPressure;
    }

    public int getDysPressure() {
        return dysPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    @Nullable
    public String getComment() {
        return comment;
    }
}
