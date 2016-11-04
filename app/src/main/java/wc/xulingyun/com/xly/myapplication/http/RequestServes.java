package wc.xulingyun.com.xly.myapplication.http;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import wc.xulingyun.com.xly.myapplication.http.dao.Music;

/**
 * Created by 徐玲郓 on 2016/10/19.
 * 描述：
 */

public interface RequestServes {

    //method=baidu.ting.billboard.billList&type=1&size=10&offset=0
    @GET("v1/restserver/ting")
    Observable<Music> getString(@Query("method") String method,
                                @Query("type") int type,
                                @Query("size") int size,
                                @Query("offset") int offset);
}
