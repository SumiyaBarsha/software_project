package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp_activity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText sEmail, sPass, sUser, sConfirmpass,sHeight,sWeight,sGender;
    private Button sBtn;
    private TextView loginText;
    ProgressDialog proDiag;
    FirebaseDatabase database;
    DatabaseReference reff;
    String pattern = "[a-zA-Z0-9_-]+@[a-z]+\\.+[a-z]+";
    private String TAG = "SignUp_activity";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        sEmail = findViewById(R.id.admininputuseremail);
        sUser = findViewById(R.id.admininputusername);
        sConfirmpass = findViewById(R.id.admininputconfirmuserpassword);
        sPass = findViewById(R.id.admininputuserpassword2);
        sHeight = findViewById(R.id.admininputuserheight);
        sWeight = findViewById(R.id.admininputuserweight);
        sGender = findViewById(R.id.admininputgender);
        sBtn = findViewById(R.id.buttonnewregisteradmin);
        loginText = findViewById(R.id.alreadyhaveaccount);
        proDiag = new ProgressDialog(this);

        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reff = database.getReference();

                String newUser = sUser.getText().toString().trim();
                String newEmail = sEmail.getText().toString().trim();
                String newPass = sPass.getText().toString().trim();
                String newConfirmPass = sConfirmpass.getText().toString().trim();
                String newHeight = sHeight.getText().toString().trim();
                String newWeight = sWeight.getText().toString().trim();
                String newGender =sGender.getText().toString().trim();

                if (newUser.isEmpty()) {
                    sUser.setError("Field can't be empty!");
                } else if (newEmail.isEmpty()) {
                    sEmail.setError("Field can't be empty!");
                } else if (!newEmail.matches(pattern)) {
                    sEmail.setError("Enter Correct Email");
                } else if (newPass.isEmpty()) {
                    sPass.setError("Field can't be empty!");
                } else if (newPass.length() < 6) {
                    sPass.setError("Enter at least 6 characters or digits!!");
                } else if (newConfirmPass.isEmpty()) {
                    sConfirmpass.setError("Field can't be empty!");
                } else if (newHeight.isEmpty()) {
                    sHeight.setError("Field can't be empty!");
                } else if (newWeight.isEmpty()) {
                    sWeight.setError("Field can't be empty!");
                } else if (newGender.isEmpty()) {
                   sGender.setError("Field can't be empty!");
                }
                else if (!newConfirmPass.equals(newPass)) {
                    sConfirmpass.setError("Password doesn't match!!");
                } else if (newEmail.isEmpty()) {
                    sEmail.setError("Field can't be empty!");
                }else {
                    proDiag.setTitle("Registration");
                    proDiag.setMessage("Please wait while processing..");
                    proDiag.setCanceledOnTouchOutside(false);
                    proDiag.show();
                    reff= FirebaseDatabase.getInstance().getReference("Users");

                    auth.createUserWithEmailAndPassword(newEmail,newPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {
                                    String userId = user.getUid();
                                    ValidateEmail(newUser, newEmail, newPass, newHeight, newWeight, newGender, userId);
                                } else {
                                    proDiag.dismiss();
                                    Toast.makeText(SignUp_activity.this, "Failed to create user.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUp_activity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //ValidateEmail(newUser,newEmail,newPass,newHeight,newWeight, newGender);

                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp_activity.this,loginActivity.class));
            }
        });
    }

    private void sendActivity() {
        Intent intent = new Intent(SignUp_activity.this,loginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void ValidateEmail(String newUser, String newEmail, String newPass, String newHeight,
                                     String newWeight, String newGender, String userId)
    {
        database = FirebaseDatabase.getInstance();
        reff = database.getReference();
        //FirebaseUser user=auth.getCurrentUser();
       // String userId = user.getUid();


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(userId).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Email", newEmail);
                    userdataMap.put("Username", newUser);
                    userdataMap.put("Password", newPass);
                    userdataMap.put("Height", newHeight);
                    userdataMap.put("Weight", newWeight);
                    userdataMap.put("Gender", newGender);


                    reff.child("Users").child(userId).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp_activity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        proDiag.dismiss();

                                        Intent intent = new Intent(SignUp_activity.this, loginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        proDiag.dismiss();
                                        Toast.makeText(SignUp_activity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                else
                {
                    proDiag.dismiss();
                    Toast.makeText(SignUp_activity.this, "This Email already exists!Try another one!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}