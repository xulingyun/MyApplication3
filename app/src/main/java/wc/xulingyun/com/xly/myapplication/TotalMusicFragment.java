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
        Fragment lFragment1 = new MusicFragment();
        Fragment lFragment2 = new PhotoFragment();
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(lFragment1);
        mFragmentArrayList.add(lFragment2);
        mViewPager.setAdapter(new Mypageradapter(getActivity().getSupportFragmentManager(),mFragmentArrayList,new String[]{"音乐","本地音乐"}));
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager,true);
        return view;
    }



}
