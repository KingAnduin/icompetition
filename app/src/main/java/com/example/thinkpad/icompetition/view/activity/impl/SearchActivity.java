package com.example.thinkpad.icompetition.view.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.thinkpad.icompetition.R;

/**
 * Created by a'su's on 2018/7/12.
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private ImageView mSearchBackIV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findView();
        setListener();
    }

    private void setListener() {
        mSearchBackIV.setOnClickListener(this);
    }

    private void findView() {
        mSearchBackIV=findViewById(R.id.iv_search_back);
        mToolbar=findViewById(R.id.toolbar_search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_back:
                finish();
                break;
        }
    }
}
