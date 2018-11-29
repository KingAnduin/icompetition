package com.example.thinkpad.icompetition.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.view.fragment.i.IHomeInterestFragment;
import com.example.thinkpad.icompetition.presenter.impl.HomeInterestFragmentPrestener;

public class HomeInterestFragment extends BaseFragment<HomeInterestFragmentPrestener> implements IHomeInterestFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_competition,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected HomeInterestFragmentPrestener getPresenter() {
        return new HomeInterestFragmentPrestener(this);
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }
}
