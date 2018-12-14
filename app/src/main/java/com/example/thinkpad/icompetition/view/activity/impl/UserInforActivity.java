package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinkpad.icompetition.IcompetitionApplication;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.user.UserInforBean;
import com.example.thinkpad.icompetition.presenter.impl.UserInforPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.activity.i.IUserInforActivity;
import com.example.thinkpad.icompetition.view.widget.AsyncImageView;

import java.util.List;

import greendao.gen.DaoSession;
import greendao.gen.UserInforBeanDao;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.ImageLoaderConfiguration;
import io.rong.imageloader.core.display.FadeInBitmapDisplayer;

/**
 * created by a’su's
 */
public class UserInforActivity extends BaseActivity<UserInforPresenter> implements IBaseActivity,IUserInforActivity,View.OnClickListener {
    private DisplayImageOptions options;
    private TextView mToolbarTitleTV;
    private TextView mUserNameTV;
    private TextView mUserSexTV;
    private TextView mUserBirthday;
    private AsyncImageView mUserHeadImageAIV;
    private Toolbar mToolbar;
    private DaoSession mDaoSession;
    private UserInforBean mUserBean;
    private ImageView mHeadImageToEditInforIV;
    private ImageView mNameToEditInforIV;
    private ImageView mSexToEditInforIV;
    private ImageView mBirthdayToEditInforIV;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);
        findView();
        setListener();
        initDisplayImageOptions();
        getUserInforFromDao();
    }

    public void initDisplayImageOptions() {
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        this.options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_news_listview_img_loading)
                .displayer(new FadeInBitmapDisplayer(300))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }


    private void getUserInforFromDao() {
        mDaoSession=((IcompetitionApplication)getApplication()).getDaoSession();
        UserInforBeanDao userInforBeanDao = mDaoSession.getUserInforBeanDao();
        List<UserInforBean> list = userInforBeanDao.loadAll();
        if(list.get(0)!=null) {
            mUserBean = list.get(0);
        }
        if(!TextUtils.isEmpty(mUserBean.getUser_name()))
            mUserNameTV.setText(mUserBean.getUser_name());
        if(!TextUtils.isEmpty(mUserBean.getUser_sex()))
            mUserSexTV.setText(mUserBean.getUser_sex());
        if(!TextUtils.isEmpty(mUserBean.getUser_birthday()))
            mUserBirthday.setText(mUserBean.getUser_birthday());
        if(!TextUtils.isEmpty(mUserBean.getUser_headimage())){
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            imageLoader.clearDiskCache();
            imageLoader.clearMemoryCache();
            imageLoader.displayImage(mUserBean.getUser_headimage(),mUserHeadImageAIV,options);
        }
    }

    private void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(200);
                finish();
            }
        });
        mHeadImageToEditInforIV.setOnClickListener(this);
        mNameToEditInforIV.setOnClickListener(this);
        mSexToEditInforIV.setOnClickListener(this);
        mBirthdayToEditInforIV.setOnClickListener(this);
    }

    private void findView() {
        mHeadImageToEditInforIV=findViewById(R.id.iv_headimage_toeditinfor);
        mNameToEditInforIV=findViewById(R.id.iv_name_toeditinfor);
        mSexToEditInforIV=findViewById(R.id.iv_sex_toeditinfor);
        mBirthdayToEditInforIV=findViewById(R.id.iv_birthday_toeditinfor);
        mUserNameTV=findViewById(R.id.tv_user_name);
        mUserSexTV=findViewById(R.id.tv_user_sex);
        mUserBirthday=findViewById(R.id.tv_user_birthday);
        mUserHeadImageAIV = findViewById(R.id.aiv_user_headimage);
        mToolbar=findViewById(R.id.toolbar_main);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbarTitleTV = findViewById(R.id.toolbar_title);
        mToolbarTitleTV.setText("个人信息");
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }

    @Override
    protected UserInforPresenter getPresenter() {
        return new UserInforPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && requestCode==50){
            getUserInforFromDao();//修改个人信息后重新从数据库里获取个人信息
            showSnackBar(mUserSexTV,getResources().getString(R.string.submit_success),getMainColor());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UserInforActivity.this,EditUserInforActivity.class);
        intent.putExtra("userinfor",mUserBean);
        switch (v.getId()){
            case R.id.iv_headimage_toeditinfor:
                startActivityForResult(intent,50);
                break;
            case R.id.iv_name_toeditinfor:
                startActivityForResult(intent,50);
                break;
            case R.id.iv_sex_toeditinfor:
                startActivityForResult(intent,50);
                break;
            case R.id.iv_birthday_toeditinfor:
                startActivityForResult(intent,50);
                break;
        }
    }
}
