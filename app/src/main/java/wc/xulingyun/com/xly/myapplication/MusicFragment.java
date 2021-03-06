package wc.xulingyun.com.xly.myapplication;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.io.Serializable;
import java.util.ArrayList;

import wc.xulingyun.com.xly.myapplication.activity.AudioActivity;
import wc.xulingyun.com.xly.myapplication.http.adapter.ImageAdapter;
import wc.xulingyun.com.xly.myapplication.http.adapter.OnItemListener;
import wc.xulingyun.com.xly.myapplication.http.adapter.OnMoreListener;
import wc.xulingyun.com.xly.myapplication.http.adapter.RecycleViewDivider;
import wc.xulingyun.com.xly.myapplication.http.bean.Music;
import wc.xulingyun.com.xly.myapplication.http.bean.SongListEntity;
import wc.xulingyun.com.xly.myapplication.http.util.LoadDataUtil;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class MusicFragment extends BaseFragment implements GetDataCallback<Music>{

    ArrayList<SongListEntity> mSongListEntities;
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
        adapter = new ImageAdapter(getContext());
        adapter.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onClick(View $View) {
                createPopupWindow();
            }
        });
        adapter.setOnItemListener(new OnItemListener() {
            @Override
            public void onClick(View $View,int postion) {
                Intent lIntent = new Intent(getActivity(), AudioActivity.class);
                lIntent.putExtra("postion",postion);
                Bundle bundle = new Bundle();
                bundle.putSerializable("audioList", (Serializable) adapter.getSongListEntities());
                lIntent.putExtras(bundle);
                startActivity(lIntent);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red,R.color.colorPrimary,R.color.default_indexBar_selectedTextColor,R.color.colorPrimaryDark);
    }

    public void createPopupWindow(){

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popup_layout, null,false);
        PopupWindow mPopupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setAnimationStyle(R.style.MyPopupWindow);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xaa00ff00));
        mPopupWindow.showAtLocation(((MainActivity) getActivity()).lBottomNavigationView, Gravity.BOTTOM,0,0);
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
        mRecyclerView.setAdapter(adapter);
        adapter.refreshData($Music.getSong_list());
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
        adapter.setFooterTextView("已经没有歌曲了！");
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
