package com.example.thinkpad.icompetition.view.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.thinkpad.icompetition.R;

/**
 * Created by a'su's on 2018/7/12.
 */

public class CompetitionInforActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_infor);
        init();
    }

    private void init() {
        mToolbar=findViewById(R.id.toolbar_main);
        mToolbar.setTitle("竞赛信息");
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
