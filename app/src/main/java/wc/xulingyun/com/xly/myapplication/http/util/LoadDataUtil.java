package wc.xulingyun.com.xly.myapplication.http.util;

import android.content.Context;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import wc.xulingyun.com.xly.myapplication.MusicFragment;
import wc.xulingyun.com.xly.myapplication.MyAppcation;
import wc.xulingyun.com.xly.myapplication.dao.SongInfo;
import wc.xulingyun.com.xly.myapplication.http.RequestServes;
import wc.xulingyun.com.xly.myapplication.http.bean.Music;
import wc.xulingyun.com.xly.myapplication.http.cache.CacheManager;
import wc.xulingyun.com.xly.myapplication.http.cache.NetworkCache;
import wc.xulingyun.com.xly.myapplication.service.PlayAudioService;

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
                            context.refreshData($Music);
                        }else{
                            context.moreData($Music);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable $Throwable) {
                        context.noData();
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                }
        );
    }

    public static void getSong(final String method, final String songid,final PlayAudioService service) {
        String url = String.format(Locale.CHINA, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=%s&songid=%s", method, songid);
        NetworkCache<SongInfo> networkCache = new NetworkCache<SongInfo>() {
            @Override
            public Observable<SongInfo> get(String key, Class<SongInfo> cls) {
                Retrofit mRetrofit = new Retrofit.Builder()
                        .baseUrl("http://tingapi.ting.baidu.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                RequestServes mRequestServes = mRetrofit.create(RequestServes.class);
                return mRequestServes.getSongInfo(method, songid)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        };

        Observable<SongInfo> observable = CacheManager.getInstance().load(url, SongInfo.class, networkCache);
        observable.subscribe(
                new Action1<SongInfo>() {
                    @Override
                    public void call(SongInfo $SongInfo) {
                        service.initMediaPlayer($SongInfo);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable $Throwable) {
//                        context.noData();
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                }
        );
    }


    public static void loadLrc(String url, Context context) {
        File file = MyAppcation.getInstance().getExternalCacheDirectory(context,"");
        System.out.println("路径:"+file.getAbsolutePath());
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://musicdata.baidu.com/data2/lrc/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestServes mRequestServes = mRetrofit.create(RequestServes.class);
        mRequestServes.download("ed5225acf833564a28ccc105dd529014/278924740/278924740.lrc")
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        System.out.println("路径:123");
                        return responseBody.byteStream();
                    }
                })
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream $InputStream) {
                        System.out.println("路径:789");
                    }
                })
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }
//        return Observable.create(new Observable.OnSubscribe<FileInfo>() {
//            @Override
//            public void call(Subscriber<? super FileInfo> subscriber) {
//                if (!subscriber.isUnsubscribed()) {
//                    try {
//                        new FileDownLoader().download(url, dest, subscriber);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        subscriber.onError(e);
//                    }
//                }
//            }
//        })
//                .compose(applySchedulers())
//                .onBackpressureBuffer()// 一定要调用onBackpressureBuffer()方法,防止下载过快,导致MissingBackpressureException异常
//                .subscribe(subscriber);
//    }



//    public static final Observable.Transformer schedulersTransformer =
//            new Observable.Transformer<Observable, Observable>() {
//                @Override public Observable<Observable> call(Observable<Observable> observable) {
//                    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//                }
//            };
//
//    /**
//     * 应用 Schedulers .方便 compose() 简化代码
//     */
//    @SuppressWarnings("unchecked") public static <T> Observable.Transformer<T, T> applySchedulers() {
//        return (Observable.Transformer<T, T>) schedulersTransformer;
//    }
}
