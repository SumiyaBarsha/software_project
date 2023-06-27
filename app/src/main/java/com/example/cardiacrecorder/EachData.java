package com.example.cardiacrecorder;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class EachData implements Serializable {

    @Exclude
    private String pushId;

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

    public EachData(String pushId, long timestamp, @NonNull String date, @NonNull String time, int sysPressure,
                    int dysPressure, int heartRate, @Nullable String comment) {
        this.pushId = pushId;
        this.timestamp = timestamp;
        this.date = date;
        this.time = time;
        this.sysPressure = sysPressure;
        this.dysPressure = dysPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
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

    /**
     * check if two item ids are same
     * @param item object of new item
     * @return true if same id
     */
    public boolean isIdSame(EachData item){
        return timestamp == item.timestamp;
    }

    /**
     * checks if two objects are totally same
     * @param item object of new item
     * @return true if objects are totally same
     */
    public boolean isFullySame(EachData item){
        boolean commentCheck = (comment == null && item.comment == null);
        if(comment != null){
            commentCheck = comment.equals(item.comment);
        }

        return (timestamp == item.timestamp) && (date.equals(item.date)) && (time.equals(item.time)) &&
                (sysPressure == item.sysPressure) && (dysPressure == item.dysPressure) && (heartRate == item.heartRate) && commentCheck;

    }

}
