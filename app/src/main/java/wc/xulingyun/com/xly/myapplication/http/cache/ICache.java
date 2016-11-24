package wc.xulingyun.com.xly.myapplication.http.cache;


import rx.Observable;
import wc.xulingyun.com.xly.myapplication.http.bean.TextBean;

/**
 * Created by wpj on 16/6/13下午4:32.
 */
public interface ICache {

    <T extends TextBean> Observable<T> get(String key, Class<T> cls);

    <T extends TextBean> void put(String key, T t);
}
