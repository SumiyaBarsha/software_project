package com.example.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardiacrecorder.classes.MyDatePicker;
import com.example.cardiacrecorder.classes.MyTimePicker;
import com.example.cardiacrecorder.databinding.ActivityAddUpdateBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class AddUpdateActivity extends AppCompatActivity {

    private ActivityAddUpdateBinding binding = null;
    private EachData passedData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setClickListener();

        Intent intent = getIntent();
        Object obj = intent.getSerializableExtra("data");
        if(obj instanceof EachData){
            passedData = (EachData)obj;
            setPassedData(passedData);
        }

    }

    private void setClickListener(){
        binding.buttonSave.setOnClickListener(view->{
            saveData();
        });

        MyDatePicker datePicker = new MyDatePicker(value ->{
            if(isDateValid(value)) {
                binding.tvDate.setText(value);
            }
            else{
                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            }
        });

        MyTimePicker timePicker = new MyTimePicker(time ->{
            String date = String.valueOf(binding.tvDate.getText()).trim();
            if(date.isEmpty()){
                Toast.makeText(this, "Select a date first", Toast.LENGTH_SHORT).show();
                return;
            }

            if(isTimeValid(date,time)) {
                binding.tvTime.setText(time);
            }
            else{
                Toast.makeText(this, "Invalid time", Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvDate.setOnClickListener(view -> datePicker.show(getSupportFragmentManager(),"Date picker"));
        binding.tvTime.setOnClickListener(view -> timePicker.show(getSupportFragmentManager(),"Time picker"));

    }


    /**
     * check if date is valid
     * @param date to be checked. Must be in dd/MM/yyyy format
     * @return true if date is today or earlier
     */
    private boolean isDateValid(String date){
        try{
            LocalDate today = LocalDate.now();

            String pattern = "dd/MM/yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
            TemporalAccessor ta = formatter.parse(date);

            LocalDate conDate = LocalDate.from(ta);

            return !conDate.isAfter(today);

        }catch (Exception e){
            e.printStackTrace();
            return true;
        }

    }

    /**
     * check if time is before current time
     * @param time to be checked. Must be in hh:mm:a
     * @return true if before current time else false
     */
    private boolean isTimeValid(String date, String time){
        try{
            LocalDateTime cur = LocalDateTime.now();

            String comb = date+" "+time;
            String pattern = "dd/MM/yyyy hh:mma";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern,Locale.US);

            TemporalAccessor ta = formatter.parse(comb);

            LocalDateTime ldt = LocalDateTime.from(ta);

            return ldt.isBefore(cur);

        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }


    private void setPassedData(@NonNull EachData data){
        binding.tvDate.setText(data.getDate());
        binding.tvTime.setText(data.getTime());
        binding.editTextSysPressure.setText(getString(R.string.int_ph,data.getSysPressure()));
        binding.editTextDysPressure.setText(getString(R.string.int_ph,data.getDysPressure()));
        binding.editTextHeartRate.setText(getString(R.string.int_ph,data.getHeartRate()));
        binding.editTextComment.setText(data.getComment());
        binding.buttonSave.setText(getString(R.string.update));
    }

    private void saveData(){
        if(binding.progressBar.getVisibility() == View.VISIBLE) return;

        binding.progressBar.setVisibility(View.VISIBLE);

        String date = String.valueOf(binding.tvDate.getText()).trim();
        String time = String.valueOf(binding.tvTime.getText()).trim();
        String sys = String.valueOf(binding.editTextSysPressure.getText()).trim();
        String dys = String.valueOf(binding.editTextDysPressure.getText()).trim();
        String hRate = String.valueOf(binding.editTextHeartRate.getText()).trim();
        String comment = String.valueOf(binding.editTextComment.getText()).trim();

        if(!isValid(date,time,sys,dys,hRate)){
            Toast.makeText(this, "Fill all forms", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        int sysP = 0, dysP = 0, rate = 0;

        try{
            sysP = Integer.parseInt(sys);
            dysP = Integer.parseInt(dys);
            rate = Integer.parseInt(hRate);
        }catch (Exception ignored){}



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            binding.progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("data").child(uid);
        String pushId = null;
        if(passedData == null){
            pushId = ref.push().getKey();
        }

        if(pushId == null) pushId = passedData.getPushId();

        EachData data = new EachData(pushId,System.currentTimeMillis(),date,time,sysP,dysP,rate,comment);

        ref.child(pushId).setValue(data).addOnCompleteListener(task -> {
            binding.progressBar.setVisibility(View.INVISIBLE);
            String message = (passedData == null) ? "Added successfully" : "Updated successfully";
            if(task.isSuccessful()){
                Toast.makeText(AddUpdateActivity.this,message, Toast.LENGTH_SHORT).show();
                clearAll();
            }
            else{
                Toast.makeText(AddUpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isValid(String ...values){
        for(String val : values){
            if(val.isEmpty()) return false;
        }
        return true;
    }

    private void clearAll(){
        binding.tvTime.setText(null);
        binding.editTextSysPressure.setText(null);
        binding.editTextDysPressure.setText(null);
        binding.editTextHeartRate.setText(null);
        binding.editTextComment.setText(null);
    }

}