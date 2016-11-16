package wc.xulingyun.com.xly.myapplication;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class MusicFragment extends BaseFragment{

    List<SongListEntity> mSongListEntities;
    boolean isFirst = true;
    ImageAdapter adapter;
    int offValue = 0;
    int loadCount = 0;
    int size = 20;
    MainActivity mMainActivity;

    @Override
    void init() {
        isFirst = true;
        mSongListEntities = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
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
        loadData(1,size,offValue,true);
    }

    @Override
    protected void loadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        loadCount++;
        offValue= loadCount*size;
        loadData(1,size,offValue,false);
    }

    void loadData(int type, int size, int offset, final boolean isRefresh){
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
                            if (isFirst) {
                                isFirst = false;
                                adapter = new ImageAdapter(getContext(), $Music.getSong_list());
                                mRecyclerView.setAdapter(adapter);
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
                                if(!isRefresh) {
                                    adapter.addData($Music.getSong_list());
                                }else{
                                    adapter.refreshData($Music.getSong_list());
                                }
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



}
