package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cardiacrecorder.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        downloadData();
        binding.fabAdd.setOnClickListener(view->{
            Intent intent = new Intent(HomeActivity.this,AddUpdateActivity.class);
            startActivity(intent);
        });

    }

    private void downloadData(){



    }

}