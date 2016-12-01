package wc.xulingyun.com.xly.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;

import static android.R.attr.width;

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
//        Fragment lFragment2 = new PhotoFragment();
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
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                colorChange(i);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager,true);
        colorChange(0);
        return view;
    }


    /**
     * 界面颜色的更改
     */
    @SuppressLint("NewApi")
    private void colorChange(int position) {
        // 用来提取颜色的Bitmap
        Bitmap bitmap;
        if(position==0){
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_pink_600_24dp);
        }else{
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_cyan_700_24dp);
        }
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                toolbar.setBackgroundColor(vibrant.getRgb());
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                    mTabLayout.setBackground(new ColorDrawable(vibrant.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     * RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     * Android中我们一般使用它的16进制，
     * 例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     * red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     * 所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

}
