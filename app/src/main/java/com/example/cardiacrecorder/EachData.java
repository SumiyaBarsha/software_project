package com.example.cardiacrecorder;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class EachData implements Serializable {

    @Exclude
    private String pushId;

    private long timestamp;

    @NonNull
    private String date; //dd-mm-yyyy

    @NonNull
    private String time; // hh:mma

    private int sysPressure; // mm Hg - non-negative
    private int dysPressure; // mm Hg - non-negative
    private int heartRate; // beats per minute non-negative
    @Nullable
    private String comment;
    private String status;

    public EachData(String pushId, long timestamp, @NonNull String date, @NonNull String time, int sysPressure,
                    int dysPressure, int heartRate, @Nullable String comment, @Nullable String status) {
        this.pushId = pushId;
        this.timestamp = timestamp;
        this.date = date;
        this.time = time;
        this.sysPressure = sysPressure;
        this.dysPressure = dysPressure;
        this.heartRate = heartRate;
        this.comment = comment;
        this.status = status;
    }

    public EachData() {}

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }

    public void setSysPressure(int sysPressure) {
        this.sysPressure = sysPressure;
    }

    public void setDysPressure(int dysPressure) {
        this.dysPressure = dysPressure;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setComment(@Nullable String comment) {
        this.comment = comment;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
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

    public String getStatus() { return status; }

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

    /**
     * formats Dys Pressure
     * @return Dys pressure with unit
     */
    @Exclude
    public String getFormattedDysPressure(){
        return dysPressure+"mm Hg";
    }
    /**
     * formats Heart Rate
     * @return Heart Rate with unit
     */
    @Exclude
    public String getFormattedHeartRate(){
        return heartRate+"BPM";
    }

    /**
     * formats Sys Pressure
     * @return Sys pressure with unit
     */
    @Exclude
    public String getFormattedSysPressure(){
        return sysPressure+"mm Hg";
    }

}
