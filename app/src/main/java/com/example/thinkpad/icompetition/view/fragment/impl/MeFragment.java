package com.example.thinkpad.icompetition.view.fragment.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinkpad.icompetition.IcompetitionApplication;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.user.UserInforBean;
import com.example.thinkpad.icompetition.view.activity.impl.UserInforActivity;
import com.example.thinkpad.icompetition.view.activity.impl.UserSetActivity;
import com.example.thinkpad.icompetition.view.widget.AsyncImageView;

import java.util.List;

import greendao.gen.DaoSession;
import greendao.gen.UserInforBeanDao;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.ImageLoaderConfiguration;
import io.rong.imageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by a'su's on 2018/7/12.
 * 我的Fragment
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private TextView mUserPhoneTV;
    private TextView mUserNameTV;
    private DaoSession mDaoSession;
    private UserInforBean mUserBean;
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private AsyncImageView mUserHeadImageAIV;
    private View view;
    private ImageView mUerInfoEditorIV;
    private ImageView mUerSetIV;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        findView();
        setListener();
        initDisplayImageOptions();
        loadUserInfor();
        return view;
    }

    //加载用户头像,名字以及电话
    private void loadUserInfor() {
        mDaoSession=((IcompetitionApplication)getActivity().getApplication()).getDaoSession();
        UserInforBeanDao userInforBeanDao = mDaoSession.getUserInforBeanDao();
        List<UserInforBean> list = userInforBeanDao.loadAll();
        if(list.get(0)!=null) {
            mUserBean = list.get(0);
        }
        if(!TextUtils.isEmpty(mUserBean.getUser_headimage())) {
            imageLoader.displayImage(mUserBean.getUser_headimage(), mUserHeadImageAIV, options);
        }
        if(!TextUtils.isEmpty(mUserBean.getUser_name())){
            mUserNameTV.setText(mUserBean.getUser_name());
        }
        if(!TextUtils.isEmpty(String.valueOf(mUserBean.getUser_num()))){
            mUserPhoneTV.setText(String.valueOf(mUserBean.getUser_num()));
        }
    }

    private void setListener() {
        mUerInfoEditorIV.setOnClickListener(this);
        mUerSetIV.setOnClickListener(this);
    }

    private void findView() {
        mUserPhoneTV = view.findViewById(R.id.tv_user_phone);
        mUerInfoEditorIV=view.findViewById(R.id.iv_me_editor);
        mUserHeadImageAIV=view.findViewById(R.id.aiv_me_headimage);
        mUerSetIV=view.findViewById(R.id.iv_me_set);
        mUserNameTV=view.findViewById(R.id.tv_me_name);
    }

    public void initDisplayImageOptions() {
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        this.options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_news_listview_img_loading)
                .displayer(new FadeInBitmapDisplayer(300))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if (resultCode==200){
                //为防止用户修改了个人信息，于是每次从用户信息界面返回都从本地数据库里读取一次头像
                loadUserInfor();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_me_editor:
                startActivityForResult(new Intent(getActivity(),UserInforActivity.class),100);
                break;
            case R.id.iv_me_set:
                startActivity(new Intent(getActivity(),UserSetActivity.class));
                break;
        }
    }
}
