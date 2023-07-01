package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cardiacrecorder.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Object obj = intent.getSerializableExtra("data");
        if(obj instanceof EachData){
            EachData data = (EachData)obj;
            binding.tvDate.setText(data.getDate());
            binding.tvTime.setText(data.getTime());
            binding.tvSysPressure.setText(data.getFormattedSysPressure());
            binding.tvDysPressure.setText(data.getFormattedDysPressure());
            binding.tvHeartRate.setText(data.getFormattedHeartRate());
            binding.tvComment.setText(data.getComment());
        }
    }

}
