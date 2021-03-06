package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.thinkpad.icompetition.IcompetitionApplication;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.exam.ExamRecordItemBean;
import com.example.thinkpad.icompetition.model.entity.exam.ExamRecordRoot;
import com.example.thinkpad.icompetition.model.entity.user.UserInforBean;
import com.example.thinkpad.icompetition.presenter.impl.MyCollectionPresenter;
import com.example.thinkpad.icompetition.util.NetWorkHelper;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.activity.i.IMyCollectionActivity;
import com.example.thinkpad.icompetition.view.adapter.MyCollectionAdapter;

import java.util.ArrayList;
import java.util.List;

import greendao.gen.DaoSession;
import greendao.gen.UserInforBeanDao;

/**
 * Created By hjg on 2018/12/15
 * 我的-收藏
 */
public class MyCollectionActivity
        extends BaseActivity<MyCollectionPresenter>
        implements IMyCollectionActivity, IBaseActivity {

    private MyCollectionAdapter mAdapter = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewCurrentY = 0;
    private int mRecyclerViewCurrentX = 0;
    private Toolbar mToolbar;
    private TextView mTitleTV;

    private Handler mHandler = new Handler();
    List<ExamRecordItemBean> mInfo;                         //详细信息
    private int mCurrentPage = 1;                           //页数
    private final int page_size = 10;                       //每页信息数
    private boolean mNoMoreData = false;
    private DaoSession mDaoSession;
    private UserInforBean mUserBean;                        //用户信息

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_collection);
        init();
        initView();
        setViewListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshData();
    }

    public void init(){
        mDaoSession=((IcompetitionApplication)getApplication()).getDaoSession();
        UserInforBeanDao userInforBeanDao = mDaoSession.getUserInforBeanDao();
        List<UserInforBean> list = userInforBeanDao.loadAll();
        if(list.get(0)!=null) {
            mUserBean = list.get(0);
        }
    }

    public void initView(){
        mTitleTV = findViewById(R.id.tv_toolbar_title);
        mToolbar = findViewById(R.id.toolbar_main);
        mRecyclerView = findViewById(R.id.rc_me_collection_list);
        mSwipeRefreshLayout = findViewById(R.id.srl_me_collection_list);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mTitleTV.setText(getString(R.string.collection));
        mToolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mToolbar);
    }

    public void setViewListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                        >= recyclerView.computeVerticalScrollRange()) {
                    //滑动到了底部 避免重复请求
                    if (!mNoMoreData) {
                        mRecyclerViewCurrentY = dy;
                        mRecyclerViewCurrentX = dx;
                        //加载更多
                        getCollectionInfo(String.valueOf(mUserBean.getUser_num()), ++mCurrentPage, page_size);
                    }
                }
            }
        });
    }

    @Override
    protected MyCollectionPresenter getPresenter() {
        return new MyCollectionPresenter(this);
    }


    @Override
    public void failBecauseNotNetworkReturn(int code) {
        showSnackBar(mSwipeRefreshLayout,"服务器异常，稍后重试 " + code, getMainColor());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                if (mInfo == null || mInfo.size() == 0) {
                    mRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void getCollectionInfo(String userNum, int page_no, int page_size) {
        if(NetWorkHelper.isNetworkAvailable(this)) {
            mPresenter.queryByPage(userNum, page_no, page_size);
        }else{
            showSnackBar(mRecyclerView,getString(R.string.not_have_network),getMainColor());
        }

    }

    @Override
    public void queryByPageCollectionResponse(final ExamRecordRoot root) {
        if (root != null && root.getCode() == 200) {
            if (root.getData() != null) {
                if (mInfo == null) {
                    mInfo = new ArrayList<>();
                }
                mInfo.addAll(root.getData());

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        if (mAdapter == null) {
                            mAdapter = new MyCollectionAdapter(getApplicationContext(), mInfo);
                            if (root.getData().size() < page_size) {
                                //显示没有更多
                                mNoMoreData = true;
                                mAdapter.setNoMoreData(true);
                            }
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            //更新数据
                            mAdapter.updateData(mInfo);
                            if (mInfo.size() < page_size) {
                                //显示没有更多
                                mNoMoreData = true;
                                mAdapter.setNoMoreData(true);
                            }
                            mAdapter.notifyDataSetChanged();
                            mRecyclerView.scrollTo(mRecyclerViewCurrentX, mRecyclerViewCurrentY);
                        }
                        //设置点击事件
                        gotoDoc();
                    }
                });

            } else {
                noMoreData();
            }
        } else {
            showSnackBar(mSwipeRefreshLayout, getResources().getString(R.string.request_fail), getMainColor());
        }
    }


    /**
     * 刷新数据
     */
    public void refreshData() {
        mNoMoreData = false;
        mCurrentPage = 1;
        mInfo = null;
        if (mAdapter != null) {
            mAdapter.setNoMoreData(false);
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        getCollectionInfo(String.valueOf(mUserBean.getUser_num()), mCurrentPage, page_size);
    }

    /**
     * 设置没有更多数据
     */
    public void noMoreData() {
        mNoMoreData = true;
        mInfo = new ArrayList<>();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mAdapter = new MyCollectionAdapter(getApplicationContext(), mInfo);
                mAdapter.setNoMoreData(true);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.scrollTo(mRecyclerViewCurrentX, mRecyclerViewCurrentY);
            }
        });
    }


    /**
     * 查看竞赛详情
     */
    private void gotoDoc() {
        mAdapter.setItemClickListener(new MyCollectionAdapter.docItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), CompetitionInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", mInfo.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
