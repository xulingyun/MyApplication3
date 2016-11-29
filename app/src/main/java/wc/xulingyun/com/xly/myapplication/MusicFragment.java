package wc.xulingyun.com.xly.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wc.xulingyun.com.xly.myapplication.http.adapter.ImageAdapter;
import wc.xulingyun.com.xly.myapplication.http.adapter.RecycleViewDivider;
import wc.xulingyun.com.xly.myapplication.http.bean.Music;
import wc.xulingyun.com.xly.myapplication.http.bean.SongListEntity;
import wc.xulingyun.com.xly.myapplication.http.util.LoadDataUtil;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class MusicFragment extends BaseFragment implements GetDataCallback<Music>{

    List<SongListEntity> mSongListEntities;
    boolean isFirst = true;
    ImageAdapter adapter;
    int offValue = 0;
    int loadCount = 0;
    int size = 20;
    MainActivity mMainActivity;
    int kind;

    public static MusicFragment newInstance(String text) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putString("kind", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    void init() {
        isFirst = true;
        mSongListEntities = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
    }

    @Override
    protected void getArgs() {
        if (getArguments() != null) {
            kind = Integer.parseInt(getArguments().getString("kind"));
        }
    }

    @Override
    void setView() {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red,R.color.colorPrimary,R.color.default_indexBar_selectedTextColor,R.color.colorPrimaryDark);
    }

    @Override
    protected void getViewByLayout(LayoutInflater $Inflater, ViewGroup $Container) {
        view = $Inflater.inflate(R.layout.music_fragment,$Container,false);
    }

    @Override
    protected void loadLastData() {
        mSwipeRefreshLayout.setRefreshing(true);
        loadCount=0;
        offValue= loadCount*size;
        loadData("baidu.ting.billboard.billList",kind,size,offValue,true);
    }

    @Override
    protected void loadMore() {
//        mSwipeRefreshLayout.setRefreshing(true);
        loadCount++;
        offValue= loadCount*size;
        loadData("baidu.ting.billboard.billList",kind,size,offValue,false);
    }

    void loadData(String methed, int type, int size, int offset, boolean isRefresh){
        LoadDataUtil.loadDataUseCache(methed,type,size,offset,this,isRefresh);
    }

    @Override
    public void refreshData(Music $Music) {
        adapter = new ImageAdapter(getContext(), $Music.getSong_list());
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void moreData(Music $Music) {
        adapter.addData($Music.getSong_list());
        adapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void error() {
        adapter.setFooterTextView("已经没有歌曲了！");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void noData() {

    }
}
