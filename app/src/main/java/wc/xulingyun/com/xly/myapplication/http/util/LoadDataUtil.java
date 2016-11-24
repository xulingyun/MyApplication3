package wc.xulingyun.com.xly.myapplication.http.util;

import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wc.xulingyun.com.xly.myapplication.MusicFragment;
import wc.xulingyun.com.xly.myapplication.http.RequestServes;
import wc.xulingyun.com.xly.myapplication.http.bean.Music;
import wc.xulingyun.com.xly.myapplication.http.cache.CacheManager;
import wc.xulingyun.com.xly.myapplication.http.cache.NetworkCache;

/**
 * http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0
 * Created by 徐玲郓 on 2016/11/24.
 * 描述：
 */

public class LoadDataUtil {

    public static void loadDataUseCache(final String method, final int type, final int size, final int offset, final MusicFragment context, final boolean isRefresh) {
        String url = String.format(Locale.CHINA,"http://tingapi.ting.baidu.com/v1/restserver/ting?method=%s&type=%d&size=%d&offset=%d",method,type,size,offset);
        NetworkCache<Music> networkCache = new NetworkCache<Music>() {
            @Override
            public Observable<Music> get(String key, Class<Music> cls) {
                Retrofit mRetrofit = new Retrofit.Builder()
                        .baseUrl("http://tingapi.ting.baidu.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                RequestServes mRequestServes = mRetrofit.create(RequestServes.class);
                return mRequestServes.getString(method, type, size, offset)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        };

        Observable<Music> observable = CacheManager.getInstance().load(url, Music.class, networkCache);
        observable.subscribe(
                new Action1<Music>() {
                    @Override
                    public void call(Music $Music) {
                        if(isRefresh){
                            context.hehe($Music);
                        }else{
                            context.heihei($Music);
                        }
                        System.out.println("------offset:"+offset);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable $Throwable) {
                        context.xixi();
                        System.out.println("------$Throwable"+$Throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                }
        );
    }
}
