package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cardiacrecorder.Adapter.RvAdapter;
import com.example.cardiacrecorder.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding = null;
    private RvAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new RvAdapter(this, new RvAdapter.RequestListener() {
            @Override
            public void onShowRequest(EachData data) {
                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }

            @Override
            public void onEditRequest(EachData data) {
                Intent intent = new Intent(HomeActivity.this, AddUpdateActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }

            @Override
            public void onDeleteRequest(EachData data) {
                deleteData(data.getPushId());
            }
        });
        binding.rvList.setAdapter(adapter);

        downloadData();
        binding.fabAdd.setOnClickListener(view->{
            Intent intent = new Intent(HomeActivity.this,AddUpdateActivity.class);
            startActivity(intent);
        });

    }

    private void deleteData(String pushId){
        if(pushId == null){
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) return;

        String uid = user.getUid();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("data").child(uid).child(pushId);

        ref.removeValue().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(HomeActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(HomeActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void downloadData(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Toast.makeText(this, "You are not signed in", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("data").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                List<EachData> list = new ArrayList<>();

                if(!snapshot.exists()){
                    binding.tvNoData.setVisibility(View.VISIBLE);
                    return;
                }

                binding.tvNoData.setVisibility(View.INVISIBLE);

                for(DataSnapshot ds : snapshot.getChildren()){
                    try{
                        EachData data = ds.getValue(EachData.class);
                        list.add(data);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                adapter.submitList(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(HomeActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
