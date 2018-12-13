package com.example.thinkpad.icompetition.view.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SearchEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.presenter.impl.SearchActivityPresenter;
import com.example.thinkpad.icompetition.view.activity.i.ISearchActivity;

/**
 * Created by a'su's on 2018/7/12.
 */

public class SearchActivity extends BaseActivity<SearchActivityPresenter> implements View.OnClickListener,ISearchActivity {
    private Toolbar mToolbar;
    private ImageView mSearchBackIV;
    private EditText mSearchInputET;
    private TextView mSearchTV;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findView();
        setListener();
    }

    @Override
    protected SearchActivityPresenter getPresenter() {
        return new SearchActivityPresenter(this);
    }

    private void setListener() {
        mSearchBackIV.setOnClickListener(this);
        mSearchTV.setOnClickListener(this);
    }

    private void findView() {
        mSearchInputET=findViewById(R.id.et_search);
        mSearchTV=findViewById(R.id.tv_search);
        mSearchBackIV=findViewById(R.id.iv_search_back);
        mToolbar=findViewById(R.id.toolbar_search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.tv_search:
                break;
        }
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }
}
