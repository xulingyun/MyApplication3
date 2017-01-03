package wc.xulingyun.com.xly.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.media.CamcorderProfile.get;
import static wc.xulingyun.com.xly.myapplication.R.id.toolbar;

public class MainActivity extends AppCompatActivity implements OnShowOrHideListener{

    private int height;
    private int width;
    boolean isStartAnim;
    boolean isHideAnim;
    private int bottomHeight;
    Unbinder mUnBinder;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView lBottomNavigationView;
    @BindView(R.id.main_view_pager)
    NOScollViewPager mainViewPage;
    ArrayList<Fragment> mFragmentArrayList;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBottomHeight() {
        return bottomHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mUnBinder = ButterKnife.bind(this);
        getWidthAndHeight();

        getFirstBottomHeight();

        TotalMusicFragment lFragment1 = new TotalMusicFragment();
//        MusicFragment lFragment2 = MusicFragment.newInstance("11");
        PhotoFragment lFragment2 = new PhotoFragment();
        TotalContactsFragment lFragment3 = new TotalContactsFragment();
        KuaidiFragment lFragment4 = new KuaidiFragment();
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(lFragment1);
        mFragmentArrayList.add(lFragment2);
        mFragmentArrayList.add(lFragment3);
        mFragmentArrayList.add(lFragment4);
        mainViewPage.setAdapter(new Mypageradapter(getSupportFragmentManager(),mFragmentArrayList,new String[]{"音乐","照片","电话","快递"}));
        ContextCompat.getColor(this, R.color.black);
        lBottomNavigationView.setUpWithViewPager(mainViewPage,
                new int[]{R.color.red,R.color.colorPrimary,R.color.gray,R.color.default_indexBar_textColor},
                new int[]{R.drawable.ic_music_video_white_36dp,R.drawable.ic_photo_size_select_actual_white_36dp,
                        R.drawable.ic_local_phone_white_36dp,R.drawable.ic_bug_report_white_36dp}
        );
//        lBottomNavigationView.isColoredBackground(true);
        lBottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.red);
        lBottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                colorChange(index);
            }
        });
    }

    private void getWidthAndHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
        width = dm.widthPixels;
    }

    private void getFirstBottomHeight(){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        lBottomNavigationView.measure(w, h);
        bottomHeight = lBottomNavigationView.getMeasuredHeight();
    }


    /**
     * 界面颜色的更改
     */
    @SuppressLint("NewApi")
    private void colorChange(final int position) {
        // 用来提取颜色的Bitmap
        Bitmap bitmap;
        if(position==0){
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_pink_600_24dp);
        }else if(position==1){
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_cyan_700_24dp);
        }else if(position==2){
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_green_900_24dp);
        }else{
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_yellow_900_24dp);
        }
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if(vibrant==null){
                    vibrant = palette.getLightVibrantSwatch();
                }
                if(vibrant==null){
                    vibrant = palette.getMutedSwatch();
                }
                if(vibrant==null){
                    vibrant = palette.getLightMutedSwatch();
                }
                if(vibrant==null){
                    vibrant = palette.getDarkVibrantSwatch();
                }
                if(vibrant==null){
                    vibrant = palette.getDarkMutedSwatch();
                }
                Toolbar toolbar = (Toolbar) mFragmentArrayList.get(position).getView().findViewById(R.id.toolbar);
                System.out.println(position+",vibrant:"+vibrant);
                toolbar.setBackgroundColor(vibrant.getRgb());
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
//                    mTabLayout.setBackground(new ColorDrawable(vibrant.getRgb()));
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


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出")
                .setMessage("确定退出应用?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        System.exit(0);
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("否",null);
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Override
    public void showBottomMenu() {
        if(isStartAnim||!isHideAnim) return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(bottomHeight,0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", .0f, 1.0f);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lBottomNavigationView.setTranslationY((int)valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isStartAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStartAnim = false;
                isHideAnim = false;
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

    @Override
    public void hideBottomMenu(){
        if(isStartAnim||isHideAnim) return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,bottomHeight);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 1.0f, .0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lBottomNavigationView.setTranslationY((int)valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isStartAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStartAnim = false;
                isHideAnim = true;
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

}
