package wc.xulingyun.com.xly.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalContactsFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.contacts_view_pager)
    ViewPager mViewPager;
    @BindArray(R.array.telphone)
    String[] title;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;

    List<Fragment> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.total_contacts_fragment, container, false);
        ButterKnife.bind(this,view);
        CallPhoneFragment contactsFragment1 = new CallPhoneFragment();
        ContactsFragment contactsFragment2 = new ContactsFragment();
        mList = new ArrayList<>();
        mList.add(contactsFragment1);
        mList.add(contactsFragment2);
        mViewPager.setAdapter(new ContactsViewPagerAdapter(getChildFragmentManager(),mList,title));
        toolbar.setTitle("");
        left.setTextColor(Color.parseColor("#ffffff"));
        right.setTextColor(Color.parseColor("#e4e4e4"));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    left.setTextColor(Color.parseColor("#ffffff"));
                    right.setTextColor(Color.parseColor("#e4e4e4"));
                }else if(position==1){
                    left.setTextColor(Color.parseColor("#e4e4e4"));
                    right.setTextColor(Color.parseColor("#ffffff"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setTextColor(Color.parseColor("#ffffff"));
                right.setTextColor(Color.parseColor("#e4e4e4"));
                mViewPager.setCurrentItem(0,true);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setTextColor(Color.parseColor("#e4e4e4"));
                right.setTextColor(Color.parseColor("#ffffff"));
                mViewPager.setCurrentItem(1,true);
            }
        });
        colorChange();
        return view;
    }

    /**
     * 界面颜色的更改
     */
    @SuppressLint("NewApi")
    private void colorChange() {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_cyan_700_24dp);
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
                }
            }
        });
    }

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
