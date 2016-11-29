package wc.xulingyun.com.xly.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static wc.xulingyun.com.xly.myapplication.BaseFragment.LAYOUT_MANAGER_TYPE.STAGGERED_GRID;

/**
 * Created by 徐玲郓 on 2016/11/16.
 * 描述：
 */

public abstract class BaseFragment extends Fragment {


    View view;
    RecyclerView mRecyclerView;
    private static final int off_y = 2;
    private LAYOUT_MANAGER_TYPE layoutManagerType;
    private int lastVisibleItemPosition;
    private int[] lastPositions;
    private boolean isAutoRefresh = true;
    SwipeRefreshLayout mSwipeRefreshLayout;




    public void setAutoRefresh(boolean $AutoRefresh) {
        isAutoRefresh = $AutoRefresh;
    }

    public boolean isAutoRefresh() {
        return isAutoRefresh;
    }

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    abstract void init();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArgs();
        getViewByLayout(inflater,container);
        setView();
        init();
        setRecyclerScrollListener();
        setSwipeRefreshLayoutListener();
        if(isAutoRefresh()){
            loadLastData();
        }
        return view;
    }

    protected abstract void getArgs();

    private void setSwipeRefreshLayoutListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                loadLastData();
            }
        });
    }

    private void setRecyclerScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(off_y<Math.abs(dy)){
                    if(dy>0){
                        ((MainActivity)getActivity()).hideBottomMenu();
                    }else if(dy<0){
                        ((MainActivity)getActivity()).showBottomMenu();
                    }
                }

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManagerType == null) {
                    if (layoutManager instanceof LinearLayoutManager) {
                        layoutManagerType = BaseFragment.LAYOUT_MANAGER_TYPE.LINEAR;
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        layoutManagerType = STAGGERED_GRID;
                    } else {
                        throw new RuntimeException(
                                "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
                    }
                }

                switch (layoutManagerType) {
                    case LINEAR:
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case GRID:
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case STAGGERED_GRID:
                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        if (lastPositions == null) {
                            lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                        }
                        staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                        lastVisibleItemPosition = findMax(lastPositions);
                        break;
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalItemCount - 1) {
                    loadMore();
                }
            }
        });
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * 设置view,主要是初始化recyclerview和SwipeRefreshLayout控件的属性
     */
    abstract void setView();

    /**
     * 设置fragment的布局
     * @param $Inflater
     * @param $Container
     */
    protected abstract void getViewByLayout(LayoutInflater $Inflater, ViewGroup $Container);

    /**
     * 刷新数据，获取最新的数据，即下拉
     */
    protected abstract void loadLastData();

    /**
     * 加载更多，上拉
     */
    protected abstract void loadMore();
}
