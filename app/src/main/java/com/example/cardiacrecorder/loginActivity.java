package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class loginActivity extends AppCompatActivity {

    private Button lbtn;
    private TextView txt,txt2;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText sEmail, sPass;
    ProgressDialog proDiag;
    FirebaseDatabase database;
    DatabaseReference reff;
    TextView forgot;
    private String parentDbName = "Users";
    String pattern = "[a-zA-Z0-9_-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sEmail = findViewById(R.id.admininputemail);
        sPass = findViewById(R.id.admininputuserpassword2);
        lbtn = (Button) findViewById(R.id.adminbtnlogin);
        txt = (TextView) findViewById(R.id.textviewsignup);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        proDiag = new ProgressDialog(this);
        forgot = findViewById(R.id.textViewforgotpass);


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,SignUp_activity.class));
            }
        });

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = sEmail.getText().toString().trim();
                String newPass = sPass.getText().toString().trim();


                if (newEmail.isEmpty()) {
                    sEmail.setError("Field can't be empty!");
                } else if (newPass.isEmpty()) {
                    sPass.setError("Field can't be empty!");
                } else if (newPass.length() < 8) {
                    sPass.setError("Enter at least 8 characters or digits!!");
                }
                else {
                    proDiag.setTitle("Login");
                    proDiag.setMessage("Please wait while processing..");
                    proDiag.setCanceledOnTouchOutside(false);
                    proDiag.show();

                    AccessAccount(newEmail, newPass);

                }
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(loginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(loginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(loginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }


    private void AccessAccount(String newEmail, String newPass) {

        database = FirebaseDatabase.getInstance();
        reff = database.getReference();
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(newEmail).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(newEmail).getValue(Users.class);

                    if (usersData.getEmail().equals(newEmail))
                    {
                        if (usersData.getPassword().equals(newPass)) {
                            if (parentDbName.equals("Users")) {
                                Toast.makeText(loginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                proDiag.dismiss();

                                Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                                // Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            proDiag.dismiss();
                            Toast.makeText(loginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(loginActivity.this, "Account does not exist!", Toast.LENGTH_SHORT).show();
                    proDiag.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void sendActivity() {
        Intent intent = new Intent(loginActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}