package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.thinkpad.icompetition.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity implements View.OnTouchListener{
    private Timer timer;
    private TimerTask timerTask;
    private ImageView imageView;
    private boolean isStartMainActivity=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=findViewById(R.id.splash_bg);
        imageView.setOnTouchListener(this);
        timerTask=new TimerTask() {
            @Override
            public void run() {
                if(!isStartMainActivity)
                {
                    isStartMainActivity=true;
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        };
        timer=new Timer();
        timer.schedule(timerTask,3000);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if(!isStartMainActivity)
//        {
//            timerTask.cancel();
//            isStartMainActivity=true;
//            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
//            finish();
//        }
        return super.onTouchEvent(event);
    }
}
