package wc.xulingyun.com.xly.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,OnShowOrHideListener{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mUnBinder = ButterKnife.bind(this);
        getWidthAndHeight();

        getBottomHeight();

        TotalMusicFragment lFragment1 = new TotalMusicFragment();
        MusicFragment lFragment3 = MusicFragment.newInstance("8");
        MusicFragment lFragment4 = MusicFragment.newInstance("11");
        KuaidiFragment lFragment5 = new KuaidiFragment();
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(lFragment1);
        mFragmentArrayList.add(lFragment3);
        mFragmentArrayList.add(lFragment4);
        mFragmentArrayList.add(lFragment5);
        mainViewPage.setAdapter(new Mypageradapter(getSupportFragmentManager(),mFragmentArrayList,new String[]{"音乐","照片","电话","快递"}));

        lBottomNavigationView.setUpWithViewPager(mainViewPage,
                new int[]{R.color.red,R.color.colorPrimary,R.color.gray,R.color.default_indexBar_textColor},
                new int[]{R.drawable.ic_music_video_white_36dp,R.drawable.ic_photo_size_select_actual_white_36dp,
                        R.drawable.ic_local_phone_white_36dp,R.drawable.ic_bug_report_white_36dp}
        );
        lBottomNavigationView.isColoredBackground(false);
        lBottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.red);
    }

    public void createPopupWindow(){
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_layout, null);
        PopupWindow mPopupWindow = new PopupWindow(contentView,width,height*1/2,true);
        mPopupWindow.setAnimationStyle(R.style.MyPopupWindow);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xaa00ff00));
//        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.content_main, null);
//        Button lButton = (Button) findViewById(R.id.ceshi);
//        mPopupWindow.showAsDropDown(toolbar,0,0,Gravity.BOTTOM);
    }

    private void getWidthAndHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
        width = dm.widthPixels;
    }

    private void getBottomHeight(){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        lBottomNavigationView.measure(w, h);
        bottomHeight = lBottomNavigationView.getMeasuredHeight();
//        int width = lBottomNavigationView.getMeasuredWidth();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        return true;
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
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 0, 1);

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
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 1, 0);
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
