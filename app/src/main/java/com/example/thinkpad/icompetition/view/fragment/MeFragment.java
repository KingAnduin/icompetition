package com.example.thinkpad.icompetition.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.view.activity.impl.MainActivity;
import com.example.thinkpad.icompetition.view.activity.impl.UserInforActivity;
import com.example.thinkpad.icompetition.view.activity.impl.UserSetActivity;

/**
 * Created by a'su's on 2018/7/12.
 * 我的Fragment
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TextView TV;
    private ImageView mUerInfoEditorIV;
    private ImageView mUerSetIV;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        findView();
        setListener();
        return view;
    }

    private void setListener() {
        mUerInfoEditorIV.setOnClickListener(this);
        mUerSetIV.setOnClickListener(this);
    }

    private void findView() {
        mUerInfoEditorIV=view.findViewById(R.id.iv_me_editor);
        mUerSetIV=view.findViewById(R.id.iv_me_set);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_me_editor:
                startActivity(new Intent(getActivity(),UserInforActivity.class));
                break;
            case R.id.iv_me_set:
                startActivity(new Intent(getActivity(),UserSetActivity.class));
                break;
        }
    }
}
