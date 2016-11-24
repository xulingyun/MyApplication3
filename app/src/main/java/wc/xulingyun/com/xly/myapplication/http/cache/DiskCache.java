package wc.xulingyun.com.xly.myapplication.http.cache;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wc.xulingyun.com.xly.myapplication.http.bean.TextBean;
import wc.xulingyun.com.xly.myapplication.http.util.FileUtils;

/**
 * Created by wpj on 16/6/13下午4:41.
 */
public class DiskCache implements ICache {

    private String CACHE_PATH;

    public DiskCache() {
        CACHE_PATH = FileUtils.getCacheDir();
    }

    @Override
    public <T extends TextBean> Observable<T> get(final String key, final Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                Log.v("cache", "load from disk: " + key);

                String filename = CACHE_PATH + key;
                String result = FileUtils.readTextFromSDcard(filename);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                if (TextUtils.isEmpty(result)) {

                    subscriber.onNext(null);
                } else {

                    T t = new Gson().fromJson(result, cls);
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T extends TextBean> void put(final String key, final T t) {

        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                Log.v("cache", "save to disk: " + key);

                String filename = CACHE_PATH + key;
                String result = t.toString();
                FileUtils.saveText2Sdcard(filename, result);
                if (!subscriber.isUnsubscribed()) {

                    subscriber.onNext(t);
                    subscriber.onCompleted();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}