package wc.xulingyun.com.xly.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import wc.xulingyun.com.xly.myapplication.http.RequestServes;
import wc.xulingyun.com.xly.myapplication.http.adapter.ImageAdapter;
import wc.xulingyun.com.xly.myapplication.http.adapter.OnItemListener;
import wc.xulingyun.com.xly.myapplication.http.adapter.OnMoreListener;
import wc.xulingyun.com.xly.myapplication.http.adapter.RecycleViewDivider;
import wc.xulingyun.com.xly.myapplication.http.dao.Music;
import wc.xulingyun.com.xly.myapplication.http.dao.SongListEntity;

import static wc.xulingyun.com.xly.myapplication.R.id.image_recycler;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class MusicFragment extends Fragment{

    RecyclerView musice_recycler;
    List<SongListEntity> mSongListEntities;
    boolean isFirst = true;
    ImageAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    int offValue = 0;
    int loadCount;
    int size = 20;
    private LAYOUT_MANAGER_TYPE layoutManagerType;
    private int lastVisibleItemPosition;
    private int[] lastPositions;
    private static final int off_y = 1;
    MainActivity mMainActivity;

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment,container,false);
        isFirst = true;
        mSongListEntities = new ArrayList<>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red,R.color.colorPrimary,R.color.default_indexBar_selectedTextColor,R.color.colorPrimaryDark);
        mMainActivity = (MainActivity) getActivity();
        musice_recycler = (RecyclerView)view.findViewById(image_recycler);
        musice_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        musice_recycler.setItemAnimator(new DefaultItemAnimator());
        musice_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
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
                    Toast.makeText(mMainActivity, "已经到最底部了！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        musice_recycler.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                loadCount++;
                offValue= loadCount*size;
                initData(1,size,offValue);
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
        loadCount = 0;
        offValue = 0;
        size = 10;
        initData(1,size,offValue);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void initData(int type, int size, int offset){
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://tingapi.ting.baidu.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestServes mRequestServes = mRetrofit.create(RequestServes.class);
        mRequestServes.getString("baidu.ting.billboard.billList",type,size,offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Music, Observable<SongListEntity>>() {
                    @Override
                    public Observable<SongListEntity> call(Music $Music) {
                        if($Music.getSong_list()!=null) {
                            mSongListEntities.addAll($Music.getSong_list());
                            if (isFirst) {
                                isFirst = false;
                                adapter = new ImageAdapter(getContext(), $Music.getSong_list());
                                musice_recycler.setAdapter(adapter);
                                adapter.setOnMoreListener(new OnMoreListener() {
                                    @Override
                                    public void onClick(View $View) {
                                        (mMainActivity).createPopupWindow();
                                    }
                                });
                                adapter.setOnItemListener(new OnItemListener() {
                                    @Override
                                    public void onClick(View $View) {

                                    }
                                });
                            } else {
                                adapter.addData($Music.getSong_list());
                                adapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(mMainActivity, "已经没有歌曲了！", Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                        return Observable.from($Music.getSong_list());
                    }
                })
                .subscribe(new Subscriber<SongListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SongListEntity $SongListEntity) {

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


}
