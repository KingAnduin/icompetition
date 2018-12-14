package com.example.thinkpad.icompetition.view.fragment.impl;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.exam.ExamRecordItemBean;
import com.example.thinkpad.icompetition.model.entity.exam.ExamRecordRoot;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.adapter.HomeInterestAdapter;
import com.example.thinkpad.icompetition.view.fragment.i.IHomeInterestFragment;
import com.example.thinkpad.icompetition.presenter.impl.HomeInterestFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeInterestFragment
        extends BaseFragment<HomeInterestFragmentPresenter>
        implements IBaseActivity, IHomeInterestFragment {
    private View rootView;
    private HomeInterestAdapter mAdapter = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewCurrentY = 0;
    private int mRecyclerViewCurrentX = 0;

    private Handler mHandler = new Handler();
    List<ExamRecordItemBean> mInfo;                         //详细信息
    private int mCurrentPage = 1;                           //页数
    private final int page_size = 10;                       //每页信息数
    private boolean mNoMoreData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_recommend_fragment, container, false);
        if (mPresenter == null)
            mPresenter = getPresenter();

        initView();
        setViewListener();
        return rootView;
    }

    public void initView() {
        mRecyclerView = rootView.findViewById(R.id.rc_home_recommend_list);
        mSwipeRefreshLayout = rootView.findViewById(R.id.srl_home_recommend_list);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setViewListener() {
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
                        //加载更多 TODO
                        //getTypeInfo(++mCurrentPage, page_size);
                    }
                }
            }
        });
    }


    @Override
    protected HomeInterestFragmentPresenter getPresenter() {
        return new HomeInterestFragmentPresenter(this);
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {
        showSnackBar(rootView,"服务器异常，稍后重试 " + code);
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
        //TODO
        //getTypeInfo(mCurrentPage, page_size);
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
                mAdapter = new HomeInterestAdapter(getContext(), mInfo);
                mAdapter.setNoMoreData(true);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.scrollTo(mRecyclerViewCurrentX, mRecyclerViewCurrentY);
            }
        });
    }


    /**
     * 查看详情 TODO
     */
    private void gotoDoc() {

    }

    /**
     * 用于网络请求错误时打印信息
     *
     * @param root .
     */
    public void showErrorMsg(ExamRecordRoot root) {
        showSnackBar(mRecyclerView, root.getMsg());
    }


    //分页查询
    @Override
    public void getTypeInfo(int page_no, int page_size, String type) {
        mPresenter.getTypeInfo(page_no, page_size, type);
    }

    //分页查询的回调
    @Override
    public void PagingQueryHomeInterestResponse(ExamRecordRoot root) {

    }
}
