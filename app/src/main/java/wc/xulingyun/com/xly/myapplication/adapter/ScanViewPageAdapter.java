package wc.xulingyun.com.xly.myapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by 徐玲郓 on 2017/1/4.
 * 描述：
 */

public class ScanViewPageAdapter extends PagerAdapter {

    private Context context;
    private SparseArray<View> cacheView;

    public ScanViewPageAdapter(SparseArray<View> $CacheView, Context $Context) {
        cacheView = $CacheView;
        context = $Context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
