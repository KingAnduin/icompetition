package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.thinkpad.icompetition.R;

public class UserSetActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private TextView mToolTitleTV;
    private Button mExitBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordinary_set);
        findView();
        setListener();
    }

    private void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mExitBtn.setOnClickListener(this);
    }

    private void findView() {
        mExitBtn=findViewById(R.id.btn_set_exit);
        mToolbar=findViewById(R.id.toolbar_main);
        mToolTitleTV=findViewById(R.id.toolbar_title);
        mToolTitleTV.setText("通用设置");
        mToolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_set_exit:
                Intent intent = new Intent(UserSetActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
