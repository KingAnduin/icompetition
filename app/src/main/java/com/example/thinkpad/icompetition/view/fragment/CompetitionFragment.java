package com.example.thinkpad.icompetition.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpad.icompetition.view.adapter.InFragmentAdapter;
import com.example.thinkpad.icompetition.R;

import java.util.ArrayList;

/**
 * Created by a'su's on 2018/7/12.
 * 首页主Fragment
 */

public class CompetitionFragment extends android.support.v4.app.Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeRecommendFragment homeRecommendFragment;
    private HomeHotFragment homeHotFragment;
    private ArrayList<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_competition,container,false);
        viewPager=view.findViewById(R.id.vp_competition);
        tabLayout=view.findViewById(R.id.tab_competition);
        init();
        return view;
    }

    private void init() {
        tabLayout.setupWithViewPager(viewPager);
        fragments=new ArrayList<>();
        homeRecommendFragment =new HomeRecommendFragment();
        homeHotFragment =new HomeHotFragment();
        fragments.add(homeRecommendFragment);
        fragments.add(homeHotFragment);
        viewPager.setAdapter(new InFragmentAdapter(getFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
