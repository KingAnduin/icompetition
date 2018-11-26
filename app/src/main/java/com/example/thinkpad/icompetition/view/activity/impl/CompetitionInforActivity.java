package com.example.thinkpad.icompetition.view.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.presenter.i.ICompetitionPresenter;
import com.example.thinkpad.icompetition.presenter.impl.CompetitionInforPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.activity.i.ICompetitionActivity;

/**
 * Created by a'su's on 2018/7/12.
 */

public class CompetitionInforActivity extends BaseActivity<CompetitionInforPresenter> implements IBaseActivity ,ICompetitionActivity {
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_infor);
        findView();
    }

    @Override
    protected CompetitionInforPresenter getPresenter() {
        return new CompetitionInforPresenter(this);
    }

    private void findView() {
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("");
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

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }
}
