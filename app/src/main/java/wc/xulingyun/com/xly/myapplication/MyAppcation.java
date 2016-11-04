package wc.xulingyun.com.xly.myapplication;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 徐玲郓 on 2016/10/24.
 * 描述：
 */

public class MyAppcation extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
