package com.example.thinkpad.icompetition.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpad.icompetition.R;

/**
 * Created by a'su's on 2018/7/12.
 */

public class InFragment2 extends Fragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.in_fragment2,container,false);
        return view;
    }
}
