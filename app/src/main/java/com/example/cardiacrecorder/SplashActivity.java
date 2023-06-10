package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private  static int Splash= 5000;
    Animation top,bottom;
    ImageView img;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        top= AnimationUtils.loadAnimation(this,R.anim.top_one);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_one);

        img=findViewById(R.id.hearticon);
        txt=findViewById(R.id.firstview);

        img.setAnimation(top);
        txt.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        }, Splash);
    }
}