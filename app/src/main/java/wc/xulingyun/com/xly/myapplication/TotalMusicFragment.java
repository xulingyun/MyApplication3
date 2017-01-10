package wc.xulingyun.com.xly.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class TotalMusicFragment extends Fragment {

    ViewPager mViewPager;
    ArrayList<Fragment> mFragmentArrayList;
    TabLayout mTabLayout;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar_main,container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.song);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        MusicFragment lFragment1 = MusicFragment.newInstance("1");
        MusicFragment lFragment3 = MusicFragment.newInstance("8");
        MusicFragment lFragment4 = MusicFragment.newInstance("11");
        MusicFragment lFragment5 = MusicFragment.newInstance("14");
        MusicFragment lFragment6 = MusicFragment.newInstance("20");
        MusicFragment lFragment7 = MusicFragment.newInstance("21");
        MusicFragment lFragment8 = MusicFragment.newInstance("22");
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(lFragment1);
        mFragmentArrayList.add(lFragment3);
        mFragmentArrayList.add(lFragment4);
        mFragmentArrayList.add(lFragment5);
        mFragmentArrayList.add(lFragment6);
        mFragmentArrayList.add(lFragment7);
        mFragmentArrayList.add(lFragment8);
        mViewPager.setAdapter(new Mypageradapter(getChildFragmentManager(),mFragmentArrayList,new String[]{"新歌榜","Billboard","摇滚榜","影视金曲榜","华语金曲榜","欧美金曲榜","经典老歌榜"}));
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager,true);
        ((MainActivity)getActivity()).colorChange(0);
        return view;
    }


}
