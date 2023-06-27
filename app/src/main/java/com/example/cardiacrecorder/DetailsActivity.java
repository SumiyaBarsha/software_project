package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cardiacrecorder.databinding.ActivityDetaislBinding;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetaislBinding binding = ActivityDetaislBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Object obj = intent.getSerializableExtra("data");
        if(obj instanceof EachData){
            EachData data = (EachData)obj;
            binding.tvDate.setText(data.getDate());
            binding.tvTime.setText(data.getTime());
            binding.tvSysPressure.setText(getString(R.string.int_ph,data.getSysPressure()));
            binding.tvDysPressure.setText(getString(R.string.int_ph,data.getDysPressure()));
            binding.tvHeartRate.setText(getString(R.string.int_ph,data.getHeartRate()));
            binding.tvComment.setText(data.getComment());
        }
    }

}
