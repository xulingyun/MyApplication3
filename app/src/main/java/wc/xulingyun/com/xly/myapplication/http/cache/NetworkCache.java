package wc.xulingyun.com.xly.myapplication.http.cache;


import rx.Observable;
import wc.xulingyun.com.xly.myapplication.http.bean.TextBean;

/**
 * Created by wpj on 16/6/13下午4:43.
 */
public abstract class NetworkCache<T extends TextBean> {

    public abstract Observable<T> get(String key, final Class<T> cls);
}