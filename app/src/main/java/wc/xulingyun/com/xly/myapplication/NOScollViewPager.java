package wc.xulingyun.com.xly.myapplication;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 徐玲郓 on 2016/11/28.
 * 描述：
 */

public class NOScollViewPager extends ViewPager {

    public NOScollViewPager(Context context) {
        super(context);
    }

    public NOScollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
