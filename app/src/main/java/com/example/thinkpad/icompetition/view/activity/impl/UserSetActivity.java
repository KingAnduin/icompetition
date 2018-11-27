package com.example.thinkpad.icompetition.view.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.thinkpad.icompetition.R;

public class UserSetActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mToolTitleTV;
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
    }

    private void findView() {
        mToolbar=findViewById(R.id.toolbar_main);
        mToolTitleTV=findViewById(R.id.toolbar_title);
        mToolTitleTV.setText("通用设置");
        mToolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mToolbar);
    }
}
