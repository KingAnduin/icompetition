package com.example.thinkpad.icompetition.view.fragment;

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
import com.example.thinkpad.icompetition.presenter.impl.HomeHotFragmentPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.adapter.HomeRecommendAdapter;
import com.example.thinkpad.icompetition.view.fragment.i.IHomeHotFragment;
import com.example.thinkpad.icompetition.view.fragment.impl.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a'su's on 2018/7/12.
 * 首页热门Fragment
 */

public class HomeHotFragment extends BaseFragment<HomeHotFragmentPresenter> implements IBaseActivity, IHomeHotFragment {
    private View rootView;
    private HomeRecommendAdapter mAdapter = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewCurrentY = 0;
    private int mRecyclerViewCurrentX = 0;
    List<ExamRecordItemBean> mInfo;                         //详细信息
    private int mCurrentPage = 1;                           //页数
    private final int page_size = 10;                       //每页信息数
    private Handler handler = new Handler();
    private boolean mNoMoreData = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_hot_fragment, container, false);
        if (mPresenter == null)
            mPresenter = getPresenter();

        initView();
        setViewListener();
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        refreshDate();
    }

    @Override
    protected HomeHotFragmentPresenter getPresenter() {
        return new HomeHotFragmentPresenter(this);
    }

    public void initView() {
        mRecyclerView = rootView.findViewById(R.id.rc_home_hot_list);
        mSwipeRefreshLayout = rootView.findViewById(R.id.srl_home_hot_list);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setViewListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDate();
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
                        getExamInfo(++mCurrentPage, page_size);
                    }
                }
            }
        });
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {
        showToast("服务器异常，稍后重试 " + code);
        handler.post(new Runnable() {
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
    public void refreshDate() {
        mNoMoreData = false;
        mCurrentPage = 1;
        mInfo = null;
        if (mAdapter != null) {
            mAdapter.setNoMoreData(false);
        }
        getExamInfo(mCurrentPage, page_size);
    }

    /**
     * 设置没有更多数据
     */
    public void noMoreData() {
        mNoMoreData = true;
        mInfo = new ArrayList<>();
        mAdapter = new HomeRecommendAdapter(getContext(), mInfo);
        mAdapter.setNoMoreData(true);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.scrollTo(mRecyclerViewCurrentX, mRecyclerViewCurrentY);
    }

    @Override
    public void getExamInfo(int page_no, int page_size) {
        mPresenter.getExamInfo(page_no, page_size);
    }

    //分页请求数据的返回
    @Override
    public void PagingQueryHomeHotResponse(ExamRecordRoot root) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (root != null && root.getCode() == 200) {
            if (root.getItemBean() != null) {
                if (mInfo == null) {
                    mInfo = new ArrayList<>();
                }
                mInfo.addAll(root.getItemBean());
                if (mAdapter == null) {
                    mAdapter = new HomeRecommendAdapter(getContext(), root.getItemBean());
                    if (root.getItemBean().size() < page_size) {
                        //显示没有更多
                        mNoMoreData = true;
                        mAdapter.setNoMoreData(true);
                    }
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    //更新数据
                    mAdapter.updateData(mInfo);
                    if (root.getItemBean().size() < page_size) {
                        //显示没有更多
                        mNoMoreData = true;
                        mAdapter.setNoMoreData(true);
                    }
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollTo(mRecyclerViewCurrentX, mRecyclerViewCurrentY);
                }
                //设置点击时间
                gotoDoc();
            } else {
                noMoreData();
            }
        } else {
            showSnackBar(mSwipeRefreshLayout, getResources().getString(R.string.request_fail));
        }
    }

    //查看详情 TODO
    private void gotoDoc() {
    }

    /**
     * 用于网络请求错误时打印信息
     *
     * @param root .
     */
    public void showErrowMsg(ExamRecordRoot root) {
        showSnackBar(mRecyclerView, root.getMessage());
    }
}
