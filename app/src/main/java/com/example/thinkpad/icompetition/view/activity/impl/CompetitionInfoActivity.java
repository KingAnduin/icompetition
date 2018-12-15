package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.collection.CollectionRoot;
import com.example.thinkpad.icompetition.model.entity.collection.IsCollectionRoot;
import com.example.thinkpad.icompetition.model.entity.exam.ExamRecordItemBean;
import com.example.thinkpad.icompetition.model.entity.user.UserInforBean;
import com.example.thinkpad.icompetition.presenter.impl.CompetitionInfoPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.activity.i.ICompetitionActivity;
import com.example.thinkpad.icompetition.view.widget.AsyncImageView;

import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.ImageLoaderConfiguration;
import io.rong.imageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by a'su's on 2018/7/12.
 * 竞赛详情界面
 */

public class CompetitionInfoActivity
        extends BaseActivity<CompetitionInfoPresenter>
        implements IBaseActivity ,ICompetitionActivity, View.OnClickListener {

    private Toolbar mToolbar;
    private DisplayImageOptions options;
    private UserInforBean mUserBean;                    //用户信息
    private ExamRecordItemBean mItemBean;               //竞赛信息
    private ImageView mExamPhotoIv;                    //比赛图片
    private TextView mTitleTv;                          //竞赛标题
    private TextView mOrganizerTv;                      //主办方
    private TextView mSignUpTimeTv;                     //报名时间
    private TextView mExamTimeTv;                       //比赛时间
    private TextView mUrlTv;                            //官方网址
    private TextView mPublishNameTv;                    //发布者昵称
    private AsyncImageView mPublishHeadIv;              //发布者头像
    private ImageView mCollectionIv;                    //收藏按钮
    private ImageView mAttentionIv;                     //关注按钮
    private ImageView mShareIv;                         //分享按钮
    private Button mGoToWebBtn;                         //前往官网报名按钮

    private Boolean mIsCollection = Boolean.FALSE;      //是否已经收藏
    private Boolean mIsAttention = Boolean.FALSE;       //是否已经关注

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_infor);
        Intent intent = getIntent();
        mItemBean = (ExamRecordItemBean) intent.getSerializableExtra("item");
        //TODO
        //mUserBean = (UserInforBean) intent.getSerializableExtra("user");
        initBar();
        findView();
        setViewListener();
        setDate();
    }




    private void initBar() {
        mToolbar=findViewById(R.id.toolbar_main);
        mToolbar.setTitle("竞赛信息");
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected CompetitionInfoPresenter getPresenter() {
        return new CompetitionInfoPresenter(this);
    }


    private void findView() {
        mExamPhotoIv = findViewById(R.id.com_info_photo);
        mTitleTv = findViewById(R.id.com_info_title);
        mOrganizerTv = findViewById(R.id.com_info_organizer);
        mSignUpTimeTv = findViewById(R.id.com_info_time_sign_up);
        mExamTimeTv = findViewById(R.id.com_info_time_exam);
        mUrlTv = findViewById(R.id.com_info_url);
        mPublishNameTv = findViewById(R.id.com_info_publish_name);
        mPublishHeadIv = findViewById(R.id.com_info_publish_head);
        mCollectionIv = findViewById(R.id.com_info_collection);
        mShareIv = findViewById(R.id.com_info_share);
        mAttentionIv = findViewById(R.id.com_info_attention);
        mGoToWebBtn = findViewById(R.id.com_info_webView);
        this.options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_me_app)
                .showImageOnFail(R.mipmap.ic_me_app)
                .showImageOnLoading(R.mipmap.ic_me_app)
                .displayer(new FadeInBitmapDisplayer(300))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }


    private void setViewListener() {
        mGoToWebBtn.setOnClickListener(this);
        mAttentionIv.setOnClickListener(this);
        mShareIv.setOnClickListener(this);
        mCollectionIv.setOnClickListener(this);
    }

    private void setDate() {
        if(mItemBean != null){

            String title = mItemBean.getCom_title();
            String organizer = mItemBean.getCom_sponsor();
            String signUpStart = mItemBean.getCom_signupstart();
            String signUpEnd = mItemBean.getCom_signupend();
            String signUpTime = signUpStart + " -- " + signUpEnd;
            String examStart = mItemBean.getCom_starttime();
            String examEnd = mItemBean.getCom_endtime();
            String examTime = examStart + " -- " + examEnd;
            String url = mItemBean.getCom_url();
            String publishName = mItemBean.getCom_publisher();


            //加载图片
            String imageUrl = mItemBean.getCom_picture();
            if (!imageUrl.equals("")){
                Glide.with(getApplication()).load(imageUrl).centerCrop().into(mExamPhotoIv);
                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.init(ImageLoaderConfiguration.createDefault(this));
                imageLoader.displayImage(imageUrl,mPublishHeadIv,options);
            }

            mTitleTv.setText(title);
            TextPaint tp1 = mTitleTv.getPaint();
            tp1.setFakeBoldText(true);
            mOrganizerTv.setText(organizer);
            mSignUpTimeTv.setText(signUpTime);
            mExamTimeTv.setText(examTime);
            mUrlTv.setText(url);
            mPublishNameTv.setText(publishName);

            mPresenter.getIsCollection(mItemBean.getCom_id());

        }else {
            finish();
            showSnackBar(mAttentionIv, "信息显示错误", getMainColor());
        }
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {
        showSnackBar(mAttentionIv,getResources().getString(R.string.not_network),getMainColor());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            //关注
            case R.id.com_info_attention:
                showSnackBar(mPublishHeadIv, "还没写", getMainColor());
                break;

            //收藏
            case R.id.com_info_collection:
                if(mIsCollection){
                    mPresenter.cancelCollection(mItemBean.getCom_id());
                }else {

                    mPresenter.addCollection("",mItemBean.getCom_id());
                }
                break;

            //分享
            case R.id.com_info_share:
                showSnackBar(mPublishHeadIv, "还没写", getMainColor());
                break;

            //WebView
            case R.id.com_info_webView:
                showSnackBar(mPublishHeadIv, "还没写", getMainColor());
                break;
        }
    }

    //获取是否收藏的回调
    @Override
    public void getIsCollectionResponse(IsCollectionRoot root) {
        if(root.getCode() == 200){
            if (root.getData().equals("True")){
                mIsCollection = Boolean.TRUE;
            }else {
                mIsCollection = Boolean.FALSE;
            }
            changeIcon(1, mIsCollection);
        }else {
            showSnackBar(mAttentionIv, getResources().getString(R.string.request_fail) + root.getMsg(), getMainColor());
        }
    }

    //收藏的回调
    @Override
    public void addCollectionResponse(CollectionRoot root) {
        if(root.getCode() == 200){
            mIsCollection = Boolean.TRUE;
            changeIcon(1, Boolean.TRUE);
        }else {
            showSnackBar(mAttentionIv, getResources().getString(R.string.request_fail) + root.getMsg(), getMainColor());
        }
    }

    //取消收藏的回调
    @Override
    public void cancelCollectionResponse(CollectionRoot root) {
        if(root.getCode() == 200){
            mIsCollection = Boolean.FALSE;
            changeIcon(1, Boolean.FALSE);
        }else if(root.getCode() == 1){
            showSnackBar(mAttentionIv, "信息错误", getMainColor());
        }else{
            showSnackBar(mAttentionIv, getResources().getString(R.string.request_fail) + root.getMsg(), getMainColor());
        }
    }

    @Override
    public void addAttentionResponse() {

    }

    @Override
    public void cancelAttentionResponse() {

    }

    //动态改变图标
    public void changeIcon(int type, boolean status){
        if(type == 1){
            if(status){
                mCollectionIv.setImageResource(R.mipmap.collectioned);
            }else {
                mCollectionIv.setImageResource(R.mipmap.collection);
            }
        }
        else if(type == 2){
            if (status){
                mAttentionIv.setImageResource(R.mipmap.attentioned);
            }else {
                mAttentionIv.setImageResource(R.mipmap.attention);
            }
        }
    }
}
