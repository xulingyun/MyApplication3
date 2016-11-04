package wc.xulingyun.com.xly.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class MusicFragment extends Fragment {

    RecyclerView musice_recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment,container,false);
        musice_recycler = (RecyclerView)view.findViewById(image_recycler);
        musice_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        musice_recycler.setItemAnimator(new DefaultItemAnimator());
        musice_recycler.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        initData();
        return view;
    }

    void initData(){
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://tingapi.ting.baidu.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestServes mRequestServes = mRetrofit.create(RequestServes.class);
        mRequestServes.getString("baidu.ting.billboard.billList",1,10,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Music, Observable<SongListEntity>>() {
                    @Override
                    public Observable<SongListEntity> call(Music $Music) {
                        System.out.println("---------");
                        ImageAdapter adapter = new ImageAdapter(getContext(), $Music.getSong_list());
                        musice_recycler.setAdapter(adapter);
                        adapter.setOnMoreListener(new OnMoreListener() {
                            @Override
                            public void onClick(View $View) {
                                ((MainActivity)getActivity()).createPopupWindow();
                            }
                        });
                        adapter.setOnItemListener(new OnItemListener() {
                            @Override
                            public void onClick(View $View) {

                            }
                        });
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
