package wc.xulingyun.com.xly.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class Mypageradapter extends FragmentPagerAdapter {

    FragmentManager mFragmentManager;
    List<Fragment> mList;
    String[] title;

    public Mypageradapter(FragmentManager $FragmentManager, List<Fragment> $list,String[] title) {
        super($FragmentManager);
        this.mFragmentManager = $FragmentManager;
        this.mList = $list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
