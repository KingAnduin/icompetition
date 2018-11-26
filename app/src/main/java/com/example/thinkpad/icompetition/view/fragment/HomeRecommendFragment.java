package com.example.thinkpad.icompetition.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thinkpad.icompetition.view.activity.impl.CompetitionInforActivity;
import com.example.thinkpad.icompetition.R;

/**
 * Created by a'su's on 2018/7/12.
 * 首页推荐Fragment
 */

public class HomeRecommendFragment extends Fragment {
    private View view;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_recommend_fragment,container,false);
        button=view.findViewById(R.id.btn_infor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CompetitionInforActivity.class));
            }
        });
        return view;
    }
}
