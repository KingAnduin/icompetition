package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.thinkpad.icompetition.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by a'su's on 2018/7/12.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mLoginBT;
    private RelativeLayout relativeLayout;
    private ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setListener();
    }

    private void setListener() {
        mLoginBT.setOnClickListener(this);
    }

    private void init() {
        relativeLayout=findViewById(R.id.mRelativeLayout);
        mLoginBT =findViewById(R.id.btn_login);
        mImageView=findViewById(R.id.bg_login);
        loadBackground();
    }

    private void loadBackground() {
        //Glide高斯模糊加载背景图
        Glide.with(LoginActivity.this)
                .load(R.drawable.loginbackground)
                .dontAnimate()
                .error(R.drawable.loginbackground)

                .crossFade(0)
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(this, 5))
                .into(mImageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
        }
    }
}
