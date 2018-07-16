package com.example.thinkpad.icompetition.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thinkpad.icompetition.R;

/**
 * Created by a'su's on 2018/7/12.
 */

public class MeFragment extends Fragment{
    private View view;
    private TextView TV;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        TV=view.findViewById(R.id.fragment_me);
        TV.setText("我的界面");
        return view;
    }
}
