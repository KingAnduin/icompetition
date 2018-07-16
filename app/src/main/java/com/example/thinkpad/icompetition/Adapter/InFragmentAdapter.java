package com.example.thinkpad.icompetition.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by a'su's on 2018/7/12.
 */

public class InFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragment;
    public InFragmentAdapter(FragmentManager fm, ArrayList<Fragment> mFragment) {
        super(fm);
        this.mFragment=mFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "推荐";
            default:
                return "热门";
        }
    }
}
