package wc.xulingyun.com.xly.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import static android.R.id.list;

/**
 * Created by 徐玲郓 on 2016/11/30.
 * 描述：
 */

public class ContactsViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    String[] title;

    public ContactsViewPagerAdapter(FragmentManager fm, List<Fragment> list,String[] title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
