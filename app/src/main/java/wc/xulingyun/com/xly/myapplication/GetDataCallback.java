package wc.xulingyun.com.xly.myapplication;

/**
 * Created by 徐玲郓 on 2016/11/25.
 * 描述：
 */

public interface GetDataCallback<T> {

    void refreshData(T t);
    void moreData(T t);
    void error();
    void noData();

}
